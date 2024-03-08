package com.ln.intranet.dept.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDetail {

	private int boardNo;
	private int memNo;
	private String memName;
	private String boardTitle;
	private String boardContent;
	private String boardFileRename;
	private String boardFileOrigin;
	private String boardDate;
	private String boardSt;
	private int deptNo;
}
