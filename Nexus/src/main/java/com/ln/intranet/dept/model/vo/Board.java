package com.ln.intranet.dept.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Board {
	
	private int boardNo;
	private String memName;
	private String boardTitle;
	private String boardDate;
}
