package com.ln.intranet.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 최초 로그인화면 진입
@Controller
public class LoginController {

	@RequestMapping("/login")
	public String loginforward() {
		
		
		return "/member/login";
	}
	
}
