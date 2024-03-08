package com.ln.intranet.work.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WorkGeneral {

	private int workNo; // 고유번호
	private int memNo; // 작성자 사원번호
	private int typeNo; // 결재 종류
	private String title; // 제목(일반=입력, 연차=연차, 출장=출장)
	private String sendDate; // 작성일
	private String approvalDate; // 승인일자
	private String content; // 내용(일반, 출장)
	private String start; // 연차, 출장 시작일
	private String end; // 연차, 출장 종료일
	private String next; // 다음 승인자 사원번호
	private String approvalList; // 결재 진행 흐름
	private String tempSave; // 임시 저장 체크
	private String finFlag; // 최종 승인 체크
	private String workState; // 결재 상태(진행, 승인, 반려)
	private String opinion; // 의견
	private String typeName; // 결재 종류 이름(1=일반, 2=연차, 3=출장)
	private String memName; // 작성자 이름
	private String fileRename; // 파일 경로
	private String fileOrigin; // 파일 이름
	
}
