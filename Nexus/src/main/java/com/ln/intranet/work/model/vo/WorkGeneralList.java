package com.ln.intranet.work.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class WorkGeneralList {
	
	private int typeNo; // 결재 종류
	private String typeName; // 결재 종류 이름(1=일반, 2=연차, 3=출장)
	private int workNo; // 고유번호
	private String title; // 제목(일반=입력, 연차=연차, 출장=출장)
	private String workState; // 결재 상태(진행, 승인, 반려)
	private String fileRename; // 파일 경로, null -> 0, not null -> 1
	private String sendDate; // 작성일
	private String opinion; // 의견, null -> 0, not null -> 개수
	private String memName; // 작성자 이름
	
}
