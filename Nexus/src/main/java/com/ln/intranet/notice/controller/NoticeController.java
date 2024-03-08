package com.ln.intranet.notice.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.service.NoticeService;
import com.ln.intranet.notice.model.vo.Notice;
import com.ln.intranet.notice.model.vo.NoticeDetail;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/notice")
@Slf4j
@Controller
public class NoticeController {
	
	  @Autowired 
	  private NoticeService service;
	  

	
	  // 공지사항 게시글 출력
	  @GetMapping("/list")
	  public String selectPublicNoticeList(@RequestParam(value="cp",required=false, defaultValue="1") Integer cp,
			  							Model model
			  ) {
		  	
			Map<String,Object> map = null;	
			
			map = service.selectPublicNoticeList(cp);
			
			
			model.addAttribute("map", map);
		  
		  
		  return "/notice/notice";
	  }
	  
	  

	  
	  
	  // 공지사항 디테일 폼
	  @ResponseBody
	  @GetMapping("/list/NoticeDetail")
	  public String noticeDetail(int noticeNo) {
		  
		  NoticeDetail detail = service.noticeDetail(noticeNo);
		  
		  detail.setNoticeNo(noticeNo);
		  
		  return new Gson().toJson(detail);
				 
	  }
	  
	  
}
