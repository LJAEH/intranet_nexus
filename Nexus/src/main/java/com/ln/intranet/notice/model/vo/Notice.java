package com.ln.intranet.notice.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Notice {

	private int noticeNo;
	private String title;
	private String content;
	private String file;
	private String createDate;
	private int noticeType;
	
	
	
}
