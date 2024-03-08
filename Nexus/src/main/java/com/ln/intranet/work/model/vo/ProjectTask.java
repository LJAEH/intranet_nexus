package com.ln.intranet.work.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProjectTask {
	
	private int taskNo;
	private int projectNo; // 고유번호
	
	private int memNo; // 작성자 사원번호
	private String memName; // 작성자
	
	private int deptNo;
	
	private String title; 
	private String content; 
	
	private String sendDate; // 작성일
	private String approvalDate; // 승인일자
	
	private int typeNo; // 5
	private String typeName; // 과제
	
	
	private String next; // 다음 승인자 사원번호
	private String approvalList; // 결재 진행 흐름
	
	private String tempSave; // 임시 저장 체크
	private String workState; // 결재 상태(진행, 승인, 반려)
	
	private String opinion; // 의견
	

	private String fileRename; // 파일 경로
	private String fileOrigin; // 파일 이름

}
