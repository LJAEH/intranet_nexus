package com.ln.intranet.dashBoard.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TaskTotal {
	
	private int taskNo;	
	private int projectNo;
	
	private String title; 

	private String memName; // 작성자 이름
	private String teamName;
	private String jobName;
	
	private String sendDate;
	private String approvalDate;
	
	private String workState; // 결재 상태(진행, 승인, 반려)

}
