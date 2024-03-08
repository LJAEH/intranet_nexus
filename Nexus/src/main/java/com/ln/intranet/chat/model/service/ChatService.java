package com.ln.intranet.chat.model.service;

import java.util.List;
import java.util.Map;

import com.ln.intranet.chat.model.vo.ChatRoom;
import com.ln.intranet.chat.model.vo.ChatRoomJoin;
import com.ln.intranet.chat.model.vo.Message;
import com.ln.intranet.member.model.vo.Member;

public interface ChatService {

	int insertMessage(Message chatMessage);

	List<ChatRoom> selectChatRoomList(Map<String, Object> map);

	List<Message> selectChatMessageList(int cmNo);

	int inviteMember(Map<String, Object> paramMap, ChatRoomJoin join);

	int exitChatRoom(int cmNo);



}
