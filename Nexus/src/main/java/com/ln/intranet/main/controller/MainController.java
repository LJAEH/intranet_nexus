package com.ln.intranet.main.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ln.intranet.dept.model.service.DeptService;
import com.ln.intranet.main.model.service.MainService;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.NoticeDetail;

@Controller
@SessionAttributes({"loginMember"})
public class MainController {
	
	@Autowired
	AttendanceService attnService;
	@Autowired
	DeptService deptService;
	@Autowired
	MainService service;

	@RequestMapping("/main")
	public String mainforward(Model model,
			@ModelAttribute("loginMember") Member loginMember
			) {
		
		// 오늘 날짜 DB 검색(kjw)
		LocalDate now = LocalDate.now();
		
		String year = (now.getYear() + "").substring(2);
		String month = (now.getMonthValue() < 10)? "0" + now.getMonthValue() : now.getMonthValue() + "";
		String day = (now.getDayOfMonth() < 10)? "0" + now.getDayOfMonth() : now.getDayOfMonth() + ""; 
		
		String today = year + "/" + month + "/" + day;
		
		Map<String, Object> map = new HashMap<String, Object>();  
		
		map.put("today", today);
		map.put("memNo", loginMember.getMemNo());
		
		Attendance todayAttn = attnService.selectOne(map);
		
		model.addAttribute("todayAttn" , todayAttn);
		
		// 메인화면 프로젝트 추가(kjw)
		Gson gson = new Gson();
		
		// 부서별 승인된 프로젝트의 일정
		// (제목, 시작일, 종료일)
		List projectList = deptService.projectList(loginMember);
		String pList = gson.toJson(projectList);
		model.addAttribute("pList", pList);
		
		// 회사 + 부서 공지사항(kjw)
		List notice = service.noticeSelect(loginMember);
		model.addAttribute("notice", notice);
		
		List workMinList = service.workMinList(loginMember);
		model.addAttribute("workMinList", workMinList);

		
		return "/main";
	}
	


	// 메인 화면에서 공지사항 읽기(kjw)
	@GetMapping("/mainDetail")
	@ResponseBody
	public String mainDetail(@RequestParam("noticeNo") int noticeNo) {
		
		NoticeDetail mainDetail = service.mainDetail(noticeNo);
		
		Gson gson = new Gson();
		
		return gson.toJson(mainDetail);
    
  }
  
	// 팝업
	@RequestMapping("popup")
	public String popup() {
		
		return "popup/popup";
	}
	

}
