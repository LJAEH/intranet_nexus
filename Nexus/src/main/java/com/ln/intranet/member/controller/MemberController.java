package com.ln.intranet.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.ln.intranet.member.model.service.MemberService;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.service.NoticeService;
import com.ln.intranet.notice.model.vo.Notice;
import com.ln.intranet.notice.model.vo.NoticeDetail;

@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
public class MemberController {
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private NoticeService nService;
	
	
	// 로그인 컨트롤러
	// 아이디 쿠키 or 자동로그인 쿠키 미구현
	@PostMapping("/login")
	public String login( @ModelAttribute Member inputMember,
			Model model,
			RedirectAttributes ra,
			HttpServletResponse resp,
			HttpServletRequest req
			) {
		
		Member loginMember = service.login(inputMember);

		model.addAttribute("loginMember",loginMember);
		
		if(loginMember != null) {

			if ( loginMember.getDeptNo() != 100 ) {
				
				return "redirect:/main";	
				
			} else if ( loginMember.getDeptNo() == 100 ){
				
				
				return "redirect:memberAdd";
			}
		}
		
		ra.addFlashAttribute("message","아이디 또는 비밀번호가 일치하지 않습니다.");
		return "redirect:/";
		
	}
	
	
	// 마이페이지 화면 전환
	@GetMapping("myPageProfile")
	public String myPageProfile() {
		return "/member/myPage-profile";
	}
	

	// 마이페이지 비밀번호 변경 화면 전환
	@GetMapping("myPagePwChange")
	public String myPagePwChange() {
		return "/member/myPage-pwChange";
	}
	
	
	// 마이페이지 비밀번호 변경하는 컨트롤러
	@PostMapping("myPagePwChangeFunction")
	public String changePw(@RequestParam Map<String, Object> paramMap,
							@ModelAttribute("loginMember") Member loginMember,
							RedirectAttributes ra) {
		
		// 로그인된 회원의 번호를 paramMap에 추가
		paramMap.put("memNo", loginMember.getMemNo());
		
		int result = service.changePw(paramMap);
		
		String message = null;
		String path = null;
		
		if (result > 0) {
			message = "비밀번호가 변경되었습니다.";
			path = "myPageProfile";
		} else {
			message = "비밀번호가 일치하지 않습니다.";
			path = "myPagePwChangeFunction";

		}
		return "redirect:/";
		
	}
	
	
	
	
	
	
	
	
//	-------------------------------------------------------

	// 직원 추가 화면 전환
	@RequestMapping("memberAdd")
	public String managerEmployAdd (RedirectAttributes ra,
									Model model) {
		String resultMessage = null;
		
		if(ra != null) {
			resultMessage = (String)model.getAttribute("message");
			model.addAttribute("resultMessage", resultMessage);
		}
		
		return "manager/manager-employeeAdd";
	}
	
	
	// 직원 정보 수정 화면 전환
	@GetMapping("memberUpdate")
	public String managerEmployeeUpdate() {
		
		
		return "manager/manager-employeeUpdate";
	}
	
	// 직원 삭제 화면 전환
	@GetMapping("memberDelete")
	public String managerEmployeeDelete() {
		
		return "manager/manager-employeeDelete";
	}
	
	
	// 공지사항 화면 전환
	@GetMapping("notice")
	public String managerNotice(@RequestParam(value="cp",required=false, defaultValue="1") Integer cp,
			Model model) {
		

		Map<String,Object> map = null;	
		
		map = nService.selectPublicNoticeList(cp);
		
		
		model.addAttribute("map", map);


		return  "manager/manager-notice";

	}
	
	// 공지사항 디테일 폼
	@ResponseBody
	@GetMapping("noticeDetail")
	public String noticeDetail(int noticeNo) {
		  
		NoticeDetail detail = nService.noticeDetail(noticeNo);
		  
		detail.setNoticeNo(noticeNo);
		  
		return new Gson().toJson(detail);
				 
    }
	
	
	 // 공지사항 추가
	  @PostMapping("writeNotice")
	  public String writeNotice(@RequestParam("uploadFile") MultipartFile uploadFile,
			  					@ModelAttribute("loginMember") Member loginMember,
			  					@RequestParam("content") String content,
			  					@RequestParam("title")String title,
			  					HttpServletRequest req,
			  					RedirectAttributes ra
			  					
			  )throws IOException {
		  	
		  
		  Notice notice = new Notice();
		  
		  notice.setContent(content);
		  notice.setTitle(title);
		  notice.setNoticeType(loginMember.getDeptNo());
		  
		
		  
		  String webPath = "/resources/file/";
		  String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		  
		  int result = nService.writeNotice(webPath, folderPath, uploadFile ,notice);
		  
		  String message = null;
		  
		  if(result > 0) {
			  
			  message = "공지사항 등록됨";
		  }else {
			  message = "공지사항 등록 실패";
		  }
		  ra.addFlashAttribute("message", message);
		  
		  return "redirect:/member/notice";
	  }
	  
	  
	
	// 공지사항 수정
	@PostMapping("updateNotice")
	@ResponseBody
	public String updateNotice(
							@RequestParam("noticeNo") int noticeNo,
							@RequestParam("title") String title,
							@RequestParam("content") String content,
							@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile,
							HttpServletRequest req
							)throws IOException {
		
		NoticeDetail detail = new NoticeDetail();
		
		
		detail.setNoticeNo(noticeNo);
		detail.setTitle(title);
		detail.setContent(content);
	
		String webPath = "/resources/file/";
		String folderPath = req.getSession().getServletContext().getRealPath(webPath);
		
		int result = nService.updateNotice(detail, uploadFile, webPath, folderPath);
		
		
		

		return new Gson().toJson(result);
		
	}
	
	// 공지사항 삭제
	@PostMapping("deleteNotice")
	@ResponseBody
	public String deleteNotice(@RequestParam("noticeNo") int noticeNo
							) {
				
		int result = nService.deleteNotice(noticeNo);
		
		return new Gson().toJson(result);
	}
	
	
	
	

	
	// 직원 조회 화면 전환
		@GetMapping("memberCheck")
		public String managerCheck() {
		
			return "manager/manager-employeeCheck";
		}
	
	
	
	
	
	// 직원 추가 
	@PostMapping("signUp")
	public String signUp(Member member,
						String[] memberAddress,
						RedirectAttributes ra
						) {
		
		String message = service.signUp(member);		
		
		ra.addFlashAttribute("message", message);

		return  "redirect:/member/memberAdd";
	}
	
	
	// 직원 검색
	@GetMapping("searchMember")
	@ResponseBody
	public String searchMember(@RequestParam("search") int memNo) {
		
		Member searchedMem = service.searchMember(memNo);
		
	
		
		Gson gson = new Gson();
		
		return gson.toJson(searchedMem);
	}
	
	
	// 직원 정보 수정
	@PostMapping("update")
	public String update(@RequestParam("memNo") int memNo,
						@RequestParam Map<String, Object> paramMap,
						@RequestParam("memAddress") String[] updateAddress,
						RedirectAttributes ra) {
	    
		String address = updateAddress[0]; // 주소 필드 값
	    String detailAddress = updateAddress[1]; // 상세주소 필드 값

	    String memAddress = address + "," + detailAddress; // 주소와 상세주소 조합

	    paramMap.put("memAddress", memAddress); // paramMap에 memAddress 추가
	    paramMap.put("memNo", memNo); // paramMap에 memNo 추가
		
	    int result = service.update(paramMap);
		
		
		String message = null;
		
		if(result > 0) {
			message = "직원정보가 수정 되었습니다.";
		} else {
			message = "정보 수정 실패";

		}
		
		
		ra.addFlashAttribute("message", message);
		return "redirect:/member/memberUpdate";
	}
	
	
	// 직원 퇴사 처리
	@PostMapping("delete")
	@ResponseBody
	public String delete(@RequestParam("memNo") int memNo) {
		
		int result = service.delete(memNo);
		
		
		Gson gson = new Gson();
		
		return gson.toJson(result);
	}
	
	// 퇴사할 직원 조회
	@PostMapping("deleteSearch")
	@ResponseBody
	public String deleteSearch(@RequestParam("memNo") int memNo) {
		
		Member memSearch = service.deleteSearch(memNo);
		
		Gson gson = new Gson();
		
		return gson.toJson(memSearch);
	}
	
	

	// 직원 전체 조회
	@GetMapping("selectAll")
	@ResponseBody
	public String managerEmployeeCheck() {
	
		List<Member> memList = service.selectAll();
		
		
		
		return new Gson().toJson(memList);
	}
	
	// 검색한 직원 조회
	@GetMapping("selectMem")
	@ResponseBody
	public String selectOne(@RequestParam Map<String, Object> paramMap,
							RedirectAttributes ra) {
		
		List<Member> selectMem = service.selectOne(paramMap);
		
		String message = null;
		if (selectMem.isEmpty()) {
	        message = "검색 결과가 없습니다.";
	        ra.addFlashAttribute("message", message);
		}
		
		
		
		return new Gson().toJson(selectMem);
	}
	
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(SessionStatus status) {

		status.setComplete(); 
		
		return "redirect:/login"; 
	}
	
	
	
	
	 
	
}
