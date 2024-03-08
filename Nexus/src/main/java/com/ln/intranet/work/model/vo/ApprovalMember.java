package com.ln.intranet.work.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApprovalMember {
	
	private int memNo;
	private int jobNo;
	private int teamNo;
	private int deptNo;
	private String memName;
	
}
