package com.ln.intranet.work.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Project {
	
	private int projectNo; // 고유번호

	private int memNo; // 작성자 사원번호
	private String memName; // 작성자 이름

	private int deptNo;
	
	private String title; 
	private String content; 

	// 단순 분류를 위한 정보
	private int typeNo; // 4 
	private String typeName; // 프로젝트 
	
	private String sendDate; // 작성일
	private String approvalDate; // 승인일자
	
	private String start; // 시작일
	private String end; // 종료일
	
	private String next; // 다음 승인자 사원번호
	private String approvalList; // 결재 진행 흐름
	
	private String tempSave; // 임시 저장 체크
	private String workState; // 결재 상태(진행, 승인, 반려)

	private String opinion; // 의견

	private String fileRename; // 파일 경로
	private String fileOrigin; // 파일 이름
	
	
}
