package com.ln.intranet.chat.model.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {

	@JsonProperty("mNo")
	private int mNo;
	private int cmNo;
	private int memNo;
	@JsonProperty("mContent")
	private String mContent;
	@JsonProperty("mDate")
	private Date mDate;
	private String memberNick;
	
}
