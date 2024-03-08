package com.ln.intranet.work.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WorkDetail {
	
	private int workNo; // 문서번호
	private int typeNo; // 타입
	private int memNo; // 초기 작성자 사원번호
	private String title; // 제목
	private String sendDate; // 작성일
	private String content; // 내용(일반, 출장)
	private String start; // 시작일
	private String end; // 종료일
	private String next; // 다음 승인자 사원번호
	private String approvalList; // 결재 진행 흐름
	private String opinion; // 의견
	private String fileRename; // 파일 경로
	private String fileOrigin; // 파일 이름
	private String memName; // 작성자 이름
	private String email; // 작성자 이메일
	private String nextMemName; // 결재자 이름
	private String nextMemEmail; // 결재자 이메일
	
}	
