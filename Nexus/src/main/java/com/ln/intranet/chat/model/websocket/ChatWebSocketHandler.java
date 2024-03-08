package com.ln.intranet.chat.model.websocket;

import java.sql.Date;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ln.intranet.chat.model.service.ChatService;
import com.ln.intranet.chat.model.vo.Message;



public class ChatWebSocketHandler extends TextWebSocketHandler {
	
	@Autowired
	private ChatService service;
	
	
	private Set<WebSocketSession> sessions
	= Collections.synchronizedSet(new HashSet<WebSocketSession>());
	
	
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
			
		System.out.println("Websocket MemberID : " + session.getId() + " Connected");		
			
		sessions.add(session);
		
	}
	

	// 클라이언트로부터 텍스트 메세지를 전달 받았을 때 수행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		System.out.println("전달된 메시지 : " + message.getPayload());
		
				
		ObjectMapper objectMapper = new ObjectMapper();
				
		Message chatMessage = objectMapper.readValue(message.getPayload(), Message.class);
				
				
		System.out.println(chatMessage);
		
		
		
		int result = service.insertMessage(chatMessage);
			
		if(result > 0) {
					
			// 같은 방에 접속 중인 클라이언트에게만 메세지 보내기
			// -> Set<WebSocketSession>에서 같은방 클라이언트만 골라내기
					
			for(WebSocketSession s : sessions) {
				
				
					
				// WebSocketSession == HttpSession(로그인정보, 채팅방번호)을 가로챈 것
					
				// WebSocketSession에 담겨있는 채팅방번호와
				// 메시지에 담겨있는 채팅방 번호가 같을경우
				// 같은방 클라이언트다.				
				System.out.println(chatMessage.getCmNo());
					
					// 같은방 클라이언트에게 JSON형식 메시지를 보냄
					s.sendMessage(new TextMessage(new Gson().toJson(chatMessage)));
					
				
						
			}
		}
	}


	// 클라이언트와 연결이 종료되는 수행
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
			
			sessions.remove(session);
			// 웹소켓 연결이 종료되는 경우
			// 종료된 WebSocketSession을 Set에서 제거
			
		}
	
	
}