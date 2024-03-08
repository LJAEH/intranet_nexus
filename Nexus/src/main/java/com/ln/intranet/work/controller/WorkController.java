package com.ln.intranet.work.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.work.model.service.WorkService;
import com.ln.intranet.work.model.vo.ApprovalMember;
import com.ln.intranet.work.model.vo.ProjectDetail;
import com.ln.intranet.work.model.vo.ProjectList;
import com.ln.intranet.work.model.vo.ProjectTaskList;
import com.ln.intranet.work.model.vo.WorkDetail;
import com.ln.intranet.work.model.vo.WorkGeneralList;

import lombok.extern.slf4j.Slf4j;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/work")
@Slf4j
public class WorkController {
	
	@Autowired
	WorkService service;
	
	private Logger logger = LoggerFactory.getLogger(WorkController.class);
	
	
	// 결재 상신 리스트 조회
	@GetMapping("/workSend")
	public String workSend(Model model,
			@ModelAttribute("loginMember") Member loginMember,
			RedirectAttributes ra) {
		
		List<WorkGeneralList> list = service.workSend(loginMember.getMemNo());
		List<WorkGeneralList> projectList = service.projectSendList(loginMember.getMemNo());
		List<WorkGeneralList> taskList = service.taskSendList(loginMember.getMemNo());
		List<ProjectList> pList = service.pList(loginMember.getDeptNo());
		List<ProjectTaskList> ptList = service.ptList(loginMember.getDeptNo());
		
		model.addAttribute("list", list);
		model.addAttribute("projectList", projectList);
		model.addAttribute("taskList", taskList);		
		// 프로젝트/과제 리스트
		model.addAttribute("pList", pList);
		model.addAttribute("ptList", ptList);
		
		String messageFlag = null;
		
		if(ra != null) {
			messageFlag = (String)model.getAttribute("message");
			model.addAttribute("messageFlag", messageFlag);
		}
		
		return "/work/work-send";
	}
	
	
	// 결재 상신 날짜 지정
	@ResponseBody
	@GetMapping("/workSendSelectDate")
	public String workSendSelectDate(Model model,
									@ModelAttribute("loginMember") Member loginMember,
									@RequestParam Map<String, Object> map) {
		
		
		map.put("memNo", loginMember.getMemNo());
		
		List<WorkGeneralList> list = service.workSendSelectDate(map);
		
		Gson gson = new Gson();
		
		gson.toJson(list);
		
		return gson.toJson(list);
	}
	
	// 결재 디테일 조회
	@ResponseBody
	@GetMapping("/detail")
	public String detailSelect(@RequestParam("workNo") int workNo) {
		
		WorkDetail detailSelect = service.detailSelect(workNo);
		
		Gson gson = new Gson();
		
		return gson.toJson(detailSelect);
	}
	
//	======================= 프로젝트/과제 ================================

	// 프로젝트 디테일 조회
	@ResponseBody
	@GetMapping("/projectDetail")
	public String projectDetailSelect(@RequestParam("workNo") int projectNo) {
		
		ProjectDetail detailSelect = service.projectDetailSelect(projectNo);
		
		Gson gson = new Gson();
		
		return gson.toJson(detailSelect);
	}
	
	// 과제 디테일 조회
	@ResponseBody
	@GetMapping("/taskDetail")
	public String taskDetailSelect(@RequestParam("workNo") int taskNo) {
		
		WorkDetail detailSelect = service.taskDetailSelect(taskNo);
		
		Gson gson = new Gson();
		
		return gson.toJson(detailSelect);
	}
	

	
	
	
	
	
//	=======================================================

	
	// 반려 / 승인
	@GetMapping("/clickApproval")
	@ResponseBody
	public int clickApproval(@RequestParam Map<String, Object> map,
								Model model) {
		
		int result = service.clickApproval(map);
		
		return result;
	}
	
	// 결재 취소
	@ResponseBody
	@GetMapping("/workCancle")
	public int workCancle(@RequestParam Map<String, Object> map) {
		
		int result = service.workCancle(map);
		
		return result;
	}
	
	// 결재 상신 작성 (일반 결재)
	@PostMapping("/write")
	public String workWrite(@ModelAttribute("loginMember") Member loginMember,
			@RequestParam("file-uploads") MultipartFile uploadFile,
			@RequestParam Map<String, Object> map,
			@RequestParam("tempSave") boolean tempSave,
			@RequestParam("taskTitle") List<String> taskTitleList,
			HttpServletRequest req,
			RedirectAttributes ra) {
		

		map.put("memNo", loginMember.getMemNo());
		map.put("deptNo",loginMember.getDeptNo());
		
		String workTypeWord = map.get("workTypeWord").toString();
		int typeNo = 0;

		if(workTypeWord.equals("normal-check")) typeNo = 1;
		else if(workTypeWord.equals("vacation")) typeNo = 2;
		else if(workTypeWord.equals("business-trip")) typeNo = 3;
		else if(workTypeWord.equals("project")) typeNo = 4;
		else if(workTypeWord.equals("project-task")) typeNo = 5;
		
		map.put("typeNo", typeNo);
	
		int result = service.workWrite(map, uploadFile, req, taskTitleList);
		
		String message = "";
		if(result != 0) message = "성공";
		else message = "실패";
		
		if(map.get("tempSave").toString().equals("true")) {
			if(result != 0) message = "임시 저장 성공";
			else message = "임시 저장 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:workSend";
	}
	
	// 결재자 모달창
	@ResponseBody
	@GetMapping("/approvalMember")
	public String approvalMember() {
		
		List <ApprovalMember> approvalMemberList = service.approvalMember();
		
		Gson gson = new Gson();
		
		return gson.toJson(approvalMemberList);
	}
	
	
	// 결재 수신함
	@GetMapping("/workInbox")
	public String workInbox(Model model,
			@ModelAttribute("loginMember") Member loginMember) {
			
		List <WorkGeneralList> list = service.workInbox(loginMember.getMemNo());
		List<WorkGeneralList> projectList = service.projectInbox(loginMember.getMemNo());
		List<WorkGeneralList> taskList = service.taskInbox(loginMember.getMemNo());
		List<ProjectList> pList = service.pList(loginMember.getDeptNo());
		List<ProjectTaskList> ptList = service.ptList(loginMember.getDeptNo());
				
		model.addAttribute("list", list);
		model.addAttribute("projectList", projectList);
		model.addAttribute("taskList", taskList);		
		// 프로젝트/과제 리스트
		model.addAttribute("pList", pList);
		model.addAttribute("ptList", ptList);
		
		return "/work/work-inbox(1)";
	}
	
	// 결재 임시 보관함
	@GetMapping("/workTemp")
	public String workTemp( Model model,
			@ModelAttribute("loginMember") Member loginMember) {
		
		List<WorkDetail> tempList = service.workTemp(loginMember);
		
		List<ProjectList> pList = service.pList(loginMember.getDeptNo());
		List<ProjectTaskList> ptList = service.ptList(loginMember.getDeptNo());
			
		model.addAttribute("tempList", tempList);
		// 프로젝트/과제 리스트
		model.addAttribute("pList", pList);
		model.addAttribute("ptList", ptList);
		
		return "/work/work-temp";
	}
	
	
	// 결재 임시저장 제출 완료 후 임시저장 지우기
	@GetMapping("/tempDelete")
	@ResponseBody
	public int tempDelete(@RequestParam("workNo") int workNo) {
		
		
		int result = service.tempDelete(workNo);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
