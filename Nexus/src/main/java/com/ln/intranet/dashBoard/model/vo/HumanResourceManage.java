package com.ln.intranet.dashBoard.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HumanResourceManage {
	
	private int memNo;
	private String memName;
	
	private int deptNo;
	private String deptName;
	
	private int jobNo;
	private String jobName;
	
	private int teamNo;
	private String teamName;
	
	private int workDay;
	
	private int workTime;
	private int workMin;
	
	private int exTime;
	private int exMin;
	
	private String targetDate;
}
