package com.ln.intranet.attendance.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Attendance {
	
	private int attnNo;
	private int memNo;
	private int attdTypeNo;
	private String today;
	private String attnStart;
	private String attnEnd;
	private String workingHours;
	private String extendedWorkingHours;
	private String attdTypeName;
	
}
