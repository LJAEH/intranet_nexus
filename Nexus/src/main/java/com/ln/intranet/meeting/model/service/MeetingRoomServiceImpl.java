package com.ln.intranet.meeting.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ln.intranet.meeting.model.dao.MeetingRoomDAO;
import com.ln.intranet.meeting.model.vo.MeetingRoom;

@Service
public class MeetingRoomServiceImpl implements MeetingRoomService{
	
	@Autowired
	private MeetingRoomDAO dao;

	// 모든 예약 조회
	@Override
	public List<MeetingRoom> allReservation() {
		return dao.allReservation();
	}
	

	// 회의실 예약 
	@Override
	public int reservationRoom(MeetingRoom meetingRoom) {
		
		return dao.reservationRoom(meetingRoom);
	}

	// 예약 취소
	@Override
	public int deleteReservation(int reservationNo) {
		
		return dao.deleteReservation(reservationNo);
	}


	// 해달 달의 미팅룸 리스트 불러오기(kjw)
	@Override
	public List<MeetingRoom> meetingRoomList(String inputDate) {
		return dao.meetingRoomList(inputDate);
	}
	
	
	


}
