package com.ln.intranet.member.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {
	private int memNo;
	private int jobNo;
	private String jobName;
	private int teamNo;
	private String teamName;
	private int deptNo;
	private String deptName;
	private String memId;
	private String memPw;
	private String memName;
	private String memRNo;
	private String memTel;
	private String memAddress;
	private String memEmail;
	private String enrollDate;
	private String departureDate;
	private String secession;
	
	// ** 주민번호가 memberRNo 퇴사일 departureDate 퇴사표시가 secession 입니다 ** (추후 db에 명칭도 수정필요) 
}
