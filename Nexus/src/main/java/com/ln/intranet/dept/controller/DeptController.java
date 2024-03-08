package com.ln.intranet.dept.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.ln.intranet.dept.model.service.DeptService;
import com.ln.intranet.dept.model.vo.BoardDetail;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.NoticeDetail;
import com.ln.intranet.work.model.vo.Project;

@Controller
@SessionAttributes({"loginMember"})
public class DeptController {

	private Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	private DeptService service;
	
	
	// 부서 공지사항 페이지 조회
	@GetMapping("dept/deptNotice")
	public String deptNotice(@RequestParam(value="cp",required=false, defaultValue="1") Integer cp,
				Model model,
				HttpSession session
			) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		int deptNo = loginMember.getDeptNo();
		
		Map<String,Object> map = null;	
		
		
		map = service.selectDeptNoticeList(cp , deptNo);
		
		
		model.addAttribute("map", map);
		
		return "/dept/dept-notice";
	}
	
	  // 공지사항 추가
	  @PostMapping("dept/writeNotice")
	  public String writeNotice(@RequestParam("uploadFile") MultipartFile uploadFile,
			  					@ModelAttribute("loginMember") Member loginMember,
			  					@RequestParam Map<String, Object> map,
			  					HttpServletRequest req,
			  					RedirectAttributes ra
			  					
			  )throws IOException {
		  	
		  
		  String webPath = "/resources/file/";
		  String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		  
		  int deptNo = loginMember.getDeptNo();
		  
		  map.put("deptNo", deptNo);
		  
		  int result = service.writeNotice(webPath, folderPath, uploadFile ,map);
		  
		  String message = null;
		  
		  if(result > 0) {
			  
			  message = "공지사항 등록됨";
		  }else {
			  message = "공지사항 등록 실패";
		  }
		  ra.addFlashAttribute("message", message);
		  
		  return "redirect:/dept/deptNotice";
	  }
	
	
	// 부서 공지사항 디테일
	  @ResponseBody
	  @GetMapping("dept/deptNotice/noticeDetail")
	  public String noticeDetail(int noticeNo,
			  @ModelAttribute("loginMember") Member loginMember) {
		  
		  int deptNo = loginMember.getDeptNo();
		  
		  
		  Map<String,Object> map = new HashMap<>();
		  
		  map.put("deptNo", deptNo);
		  map.put("noticeNo", noticeNo);
		  
		  NoticeDetail detail = service.noticeDetail(map);
		  
		  detail.setNoticeNo(noticeNo);
		  
		  logger.debug(detail + "");
		  
		  return new Gson().toJson(detail);
				 
	  }
	  
	  	// 부서 공지사항 수정
	  	@PostMapping("dept/updateDeptNotice")
		@ResponseBody
		public String updateDeptNotice(
								@RequestParam("noticeNo") int noticeNo,
								@RequestParam("title") String title,
								@RequestParam("content") String content,
								@RequestParam("fileData") boolean fileData,
								@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile,
								HttpServletRequest req
								)throws IOException {
	  		
	  		System.out.println("fileData :" + fileData);
			
			NoticeDetail detail = new NoticeDetail();
			
			
			
			detail.setNoticeNo(noticeNo);
			detail.setTitle(title);
			detail.setContent(content);
		
			String webPath = "/resources/file/";
			String folderPath = req.getSession().getServletContext().getRealPath(webPath);
			
			int result = service.updateDeptNotice(detail, uploadFile, webPath, folderPath, fileData);
			
			
			
			
	
			
			return new Gson().toJson(result);
			
		}
	  	
	  	// 부서 공지사항 삭제
	  	@PostMapping("dept/deleteDeptNotice")
		@ResponseBody
		public String deleteNotice(@RequestParam("noticeNo") int noticeNo
								) {
					
			int result = service.deleteNotice(noticeNo);
			
			return new Gson().toJson(result);
		}

	
	
	
	// 부서 게시판 접속
	@GetMapping("dept/deptBoard")
	public String deptBoard(
			@RequestParam(value="cp",required=false, defaultValue="1") int cp, 
			Model model,
			HttpSession session
			) {
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		Map<String,Object> map = null;
		
		int deptNo = loginMember.getDeptNo();
		
		map = service.boardList(cp,deptNo);
		
		model.addAttribute("map",map);
		
		return "/dept/dept-board";
	}
	
	
	// 부서 게시판 디테일
	@ResponseBody
	@GetMapping("dept/deptBoard/boardDetail")
	public String boardDetail(int boardNo) {
		
		BoardDetail detail = service.boardDetail(boardNo);
		
		detail.setBoardNo(boardNo);
		
		return new Gson().toJson(detail);
	}
	
	// 부서 일정 접속(kjw)
	@GetMapping("dept/deptSchedule")
	public String deptSchedule(@ModelAttribute("loginMember") Member loginMember,
								Model model) {
		Gson gson = new Gson();
		
		// 부서별 승인된 프로젝트의 일정
		// (제목, 시작일, 종료일)
		List projectList = service.projectList(loginMember);
		String pList = gson.toJson(projectList);
		model.addAttribute("pList", pList);
		
		// 부서원 - 팀 연차, 출장
		// (연차 : 팀별 , 출장 : 팀별)
		// 연차
		List vacationList = service.vacationList(loginMember);
		String vList = gson.toJson(vacationList);
		model.addAttribute("vList", vList);
		
		// 출장
		List businessList = service.businessList(loginMember);
		String bList = gson.toJson(businessList);
		model.addAttribute("bList", bList);
		
		return "/dept/dept-schedule";
	}
	
	// 부서 게시판 작성
	@PostMapping("dept/write")
	public String insertBoard(
			@RequestParam("noticeTitle") String boardTitle,
			@RequestParam("noticeContent") String boardContent,
			@RequestParam("uploadFile") MultipartFile uploadFile,
			@ModelAttribute("loginMember") Member loginMember,
			HttpServletRequest req,
			RedirectAttributes ra
			) throws IOException{
		BoardDetail detail = new BoardDetail();
		
		detail.setBoardTitle(boardTitle);
		detail.setBoardContent(boardContent);
		detail.setMemNo(loginMember.getMemNo());
		detail.setDeptNo(loginMember.getDeptNo());
		
		String filePath = "/resources/file/";
		String folderPath = req.getSession().getServletContext().getRealPath(filePath);
		
		int boardNo = service.insertBoard(detail,uploadFile,filePath,folderPath);
		
		
		
		return "redirect:/dept/deptBoard";
	}
	
	
	// 부서 게시판 수정
	@PostMapping("dept/updateDeptBoardNotice")
	@ResponseBody
	public String updateDeptBoardNotice(
									@RequestParam("boardNo") int boardNo,
									@RequestParam("boardTitle") String boardTitle,
									@RequestParam("boardContent") String boardContent,
									@RequestParam("fileData") boolean fileData,
									@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile,
									@ModelAttribute("loginMember") Member loginMember,
									HttpServletRequest req
									)throws IOException {
		
		BoardDetail detail = new BoardDetail();
		
		
		detail.setBoardNo(boardNo);
		detail.setBoardTitle(boardTitle);
		detail.setBoardContent(boardContent);
		detail.setMemNo(loginMember.getMemNo());
		detail.setDeptNo(loginMember.getDeptNo());
	
		String webPath = "/resources/file/";
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		
		int result = service.updateBoardNotice(detail, uploadFile, webPath, folderPath, fileData);
		
		
		

		return new Gson().toJson(result);
		
	}
	
	
	
	
	
	
	// 게시글 삭제
	@GetMapping("dept//boardDelete/{boardNo}")
	public String boardDelete(
			@PathVariable("boardNo") int boardNo
			) {
		
		int result = service.boardDelete(boardNo);
		
		return "redirect:/dept/deptBoard";
	}
	
}
