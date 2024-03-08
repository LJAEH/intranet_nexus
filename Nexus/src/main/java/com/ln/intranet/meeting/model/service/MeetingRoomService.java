package com.ln.intranet.meeting.model.service;

import java.util.List;
import java.util.Map;

import com.ln.intranet.meeting.model.vo.MeetingRoom;

public interface MeetingRoomService {

	int reservationRoom(MeetingRoom meetingRoom);

	List<MeetingRoom> allReservation();

	int deleteReservation(int reservationNo);
	
	// 해달 달의 미팅룸 리스트 불러오기(kjw)
	List<MeetingRoom> meetingRoomList(String inputDate);

}
