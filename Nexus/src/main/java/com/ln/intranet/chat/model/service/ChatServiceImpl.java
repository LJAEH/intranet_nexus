package com.ln.intranet.chat.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ln.intranet.chat.model.dao.ChatDAO;
import com.ln.intranet.chat.model.vo.ChatRoom;
import com.ln.intranet.chat.model.vo.ChatRoomJoin;
import com.ln.intranet.chat.model.vo.Message;
import com.ln.intranet.common.Util;
import com.ln.intranet.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	private ChatDAO dao;

	// 해당된 채팅방 리스트 조회
	@Override
	public List<ChatRoom> selectChatRoomList(Map<String, Object> map) {
		
		return dao.selectChatRoomList(map);
	}

	// 해당된 채팅 메세지 리스트
	@Override
	public List<Message> selectChatMessageList(int cmNo) {
		// TODO Auto-generated method stub
		return dao.selectChatMessageList(cmNo);
	}


	// 채팅방 개설 & 선택된 사원 초대
	@Override
	public int inviteMember(Map<String, Object> paramMap, ChatRoomJoin join) {
		
		
		// 채팅방을 생성해야함
		// seq 사용해서 방의 cmNo를 생성함
		// loginMember에서 getMemNo 가져오기 = join에 들어있음
		// memberName = paramMap에서 가져올거임
		
		paramMap.put("memNo", join.getMemNo());
		
		
		System.out.println(paramMap);
		
		// SEQ_CR_NO 받아옴
		int result = dao.CreateChatRoom(paramMap);
		

		// insert에 성공한다면
		if(result > 0) {
			// join안에 memNo들어있음 
			// 채팅방 join시키기
			join.setCmNo(result);
			int success = dao.CreateChatRoomJoin(join);
			
			if(success > 0) {
				// 초대받은 사람의 memNo사용해야함
				
				log.debug(paramMap + "");
				
				
				System.out.println(paramMap);
				
				int inviteMemNo = dao.selectInviteMember(paramMap);
				
				join.setMemNo(inviteMemNo);
				
				dao.inviteMember(join);
				
			}
		}
		
		return result;

		
	}
	
	// websocket 채팅 저장
	@Override
	public int insertMessage(Message chatMessage) {
		chatMessage.setMContent(Util.newLineHandling(chatMessage.getMContent()));
		return dao.insertMessage(chatMessage);
	}

	
	// 채팅방 나가기
	@Override
	public int exitChatRoom(int cmNo) {
		
		int result = dao.exitChatRoomJoin(cmNo);
		
		if(result > 0) {
			
		 dao.exitChatRoom(cmNo);
		 
		}else {
			
			return 0;
		}
		
		return result;
	}


}
