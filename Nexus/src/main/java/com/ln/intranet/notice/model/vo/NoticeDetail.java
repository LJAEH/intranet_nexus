package com.ln.intranet.notice.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoticeDetail {

	private int noticeNo;
	private int memNo;
	private String memName;
	private String title;
	private String content;
	private String NoticeFileRename;
	private String NoticeFileOrigin;
	private String createDate;
	private int noticeType;
	
}
