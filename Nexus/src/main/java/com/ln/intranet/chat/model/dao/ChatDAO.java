package com.ln.intranet.chat.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ln.intranet.chat.model.vo.ChatRoom;
import com.ln.intranet.chat.model.vo.ChatRoomJoin;
import com.ln.intranet.chat.model.vo.Message;
import com.ln.intranet.member.model.vo.Member;

@Repository
public class ChatDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// 해당된 채팅방 조회
	public List<ChatRoom> selectChatRoomList(Map<String, Object> map) {
		
		return sqlSession.selectList("chatMapper.selectChatRoomList" , map);
	}

	public List<Message> selectChatMessageList(int cmNo) {
		
		return sqlSession.selectList("chatMapper.selectChatMessageList" , cmNo);
	}

	public int insertMessage(Message chatMessage) {
		
		return sqlSession.insert("chatMapper.insertMessage" , chatMessage);
	}

	public List<Member> ChatMemberList() {
		
		return sqlSession.selectList("chatMapper.chatMemberList");
	}

	public int inviteMember(ChatRoomJoin join) {
		
		
		return sqlSession.insert("chatMapper.inviteMember", join);
	}
	

	public int CreateChatRoomJoin(ChatRoomJoin join) {
	    return  sqlSession.insert("chatMapper.createChatRoomJoin", join);
	    
	
	    
	    
	}

	public int CreateChatRoom(Map<String, Object> paramMap) {
		
		sqlSession.insert("chatMapper.createChatRoom", paramMap);
		
		return (int) paramMap.get("cmNo");
		
	}

	public int selectInviteMember(Map<String, Object> paramMap) {
		return sqlSession.selectOne("chatMapper.selectInviteMember", paramMap);
	}

	public int exitChatRoom(int cmNo) {
		
		return sqlSession.delete("chatMapper.exitChatRoom", cmNo);
	}

	public int exitChatRoomJoin(int cmNo) {
		
		return sqlSession.delete("chatMapper.exitChatRoomJoin", cmNo);
	}


	
	

}
