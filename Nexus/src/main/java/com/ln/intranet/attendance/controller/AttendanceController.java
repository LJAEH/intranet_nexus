package com.ln.intranet.attendance.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.Parameters;
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
import com.ln.intranet.attendance.model.service.AttendanceService;
import com.ln.intranet.attendance.model.vo.Attendance;
import com.ln.intranet.member.model.vo.Member;

@Controller
@RequestMapping("/attendance")
@SessionAttributes({"loginMember"})
public class AttendanceController {

	@Autowired
	AttendanceService service;
	
	@GetMapping("/list")
	public String selectAttendanceList(Model model,
									   @ModelAttribute("loginMember") Member loginMember,
									   @RequestParam Map<String, Object> map) {
		
		List<Attendance> attendanceList = new ArrayList<Attendance>();
		
		int memNo = loginMember.getMemNo();
		
		// 현재 날짜 구하기
		LocalDate now = LocalDate.now();
		
		String year = (now.getYear() + "").substring(2);
		String month = (now.getMonthValue() < 10)? "0" + now.getMonthValue() : now.getMonthValue() + "";
		
		// map에 요소 넣기
		map.put("memNo", memNo);
		map.put("year", year);
		map.put("month", month);
		
		attendanceList = service.selectAttendanceList(map);
		
		model.addAttribute("attendanceList", attendanceList);
		
		return "attendance/attendance"; 
		
	}

	@ResponseBody
	@GetMapping("/selectDate")
	public String selectDate(@ModelAttribute("loginMember") Member loginMember,
							@RequestParam Map<String, Object> map) {
		
		List<Attendance> attendanceList = new ArrayList<Attendance>();
		
		int memNo = loginMember.getMemNo();
		
		map.put("memNo", memNo);
		
		attendanceList = service.selectAttendanceList(map);
		
		Gson gson = new Gson();
		
		String attendanceListJson = gson.toJson(attendanceList);
		
		return attendanceListJson;
	}
	
	
	@ResponseBody
	@GetMapping("/attn_hours")
	public int attnHours(Model model,
			@ModelAttribute("loginMember") Member loginMember,
			@RequestParam Map<String, Object> map) {
		
		int result = 0;
		
		map.put("memNo", loginMember.getMemNo());
		
		if(map.get("type").equals("start")) {
			result = service.attnStrartHours(map); 
		} else {
			result = service.attnEndHours(map);
		}
		
		return result;
	}
	
}
