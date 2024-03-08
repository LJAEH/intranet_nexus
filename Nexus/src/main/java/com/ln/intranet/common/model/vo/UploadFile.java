package com.ln.intranet.common.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UploadFile {
	private int fileNo;
	private int workNo;
	private int taskNo;
	private int projectNo;
	private int noticeNo;
	private int boardNo;
	private String fileReName;
	private String fileOrigin;
}
