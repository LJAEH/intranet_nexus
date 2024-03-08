package com.ln.intranet.chat.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {

	private int cmNo;
	private int memNo;
	private String inviteName;
	private String status;
	private String createMemberName;
	private int result;
	
	
}
