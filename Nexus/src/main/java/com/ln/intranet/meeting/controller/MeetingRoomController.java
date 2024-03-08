package com.ln.intranet.meeting.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.ln.intranet.meeting.model.service.MeetingRoomService;
import com.ln.intranet.meeting.model.vo.MeetingRoom;
import com.ln.intranet.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/meetingRoom")
public class MeetingRoomController {
	
	@Autowired
	private MeetingRoomService service;
	
	private Logger logger = LoggerFactory.getLogger(MeetingRoomController.class);
	
	
	@GetMapping("reservation")
	public String meetingRoomEntry(
			@ModelAttribute("loginMember") Member loginMember,
			Model model
			) {

		model.addAttribute("loginMember",loginMember);

		return "/meetingRoom/meetingRoom";
	}
	
   @GetMapping("/reservationRoom")
   @ResponseBody
   public int reservationRoom(
		   @RequestParam("room") int roomNo, 
		   @RequestParam("time") String time,
		   @RequestParam("reservationDate") String reservationDate,
		   @ModelAttribute("loginMember") Member loginMember
		    ) {
	   
	   MeetingRoom meetingRoom = new MeetingRoom();
	 
	   meetingRoom.setRoomNo(roomNo);
	   meetingRoom.setReservationTime(time);
	   meetingRoom.setMemNo(loginMember.getMemNo());
	   meetingRoom.setReservationDate(reservationDate);
	  

	   
	   int result = service.reservationRoom(meetingRoom);
	   
	   
       
	   return result;
   }
   
   @ResponseBody
   @GetMapping("allReservation")
   public List<MeetingRoom> allReservation(){
	   
	   List<MeetingRoom> resvList = new ArrayList<MeetingRoom>();
	   
	   resvList = service.allReservation();
	   
	   
	   return resvList;
   }
   
   // 예약 취소
   @ResponseBody
   @GetMapping("deleteResv")
   public int deleteReservation(
		   @RequestParam("reservationNo") int reservationNo
		   ){
	   
	   int result = service.deleteReservation(reservationNo);
	   
	   // 예외처리 , 트랜잭션 처리 (?)
	   
	   return result;
   }
   
   // 해달 달의 미팅룸 리스트 불러오기(kjw)
   @ResponseBody
   @GetMapping("/meetingRoomList")
   public String meetingRoomList(@RequestParam("inputDate") String inputDate) {
	   List<MeetingRoom> meetingRoomList = service.meetingRoomList(inputDate);
	   
	   System.out.println(meetingRoomList);
	   
	   Gson gson = new Gson();
	   
	   return gson.toJson(meetingRoomList);
   }
   
}


