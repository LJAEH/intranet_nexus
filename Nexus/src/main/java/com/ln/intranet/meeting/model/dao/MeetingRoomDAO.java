package com.ln.intranet.meeting.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ln.intranet.meeting.model.vo.MeetingRoom;

@Repository
public class MeetingRoomDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(MeetingRoomDAO.class);
	
	// 페이지 진입시 모든 예약 정보 조회 + AJAX 송신
	public List<MeetingRoom> allReservation() {
		
		return sqlSession.selectList("meetingRoomMapper.reservationList");
	}
	
	
	// 회의실 예약 후 예약 정보 반환
	public int reservationRoom(MeetingRoom meetingRoom) {
		
		int result = sqlSession.insert("meetingRoomMapper.reservationRoom",meetingRoom);

		return result;
	}

	// 예약 취소
	public int deleteReservation(int reservationNo) {
		
		return sqlSession.delete("meetingRoomMapper.deleteReservation",reservationNo);
	}

	// 해달 달의 미팅룸 리스트 불러오기(kjw)
	public List<MeetingRoom> meetingRoomList(String inputDate) {
		return sqlSession.selectList("meetingRoomMapper.meetingRoomList", inputDate);
	}



}
