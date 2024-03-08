package com.ln.intranet.work.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProjectTaskList {
	
	private int projectNo;
	private String projectTitle;
	
	private int taskNo;
	private String taskTitle;
	
}
