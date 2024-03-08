package com.ln.intranet.dashBoard.model.vo;

import java.util.List;

import com.ln.intranet.work.model.vo.ProjectTask;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProjectTotal {

	private int projectNo; // 고유번호

	private int memNo; // 작성자 사원번호
	private String memName; // 작성자 이름
	
	private String teamName;
	private String jobName;
	
	private String title; 
	
	private String start; // 시작일
	private String end; // 종료일
	
	private String workState; // 결재 상태(진행, 승인, 반려)

	private int percent;
	
	private List<TaskTotal> taskList;	
}
