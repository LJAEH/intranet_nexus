package com.ln.intranet.meeting.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MeetingRoom {
	
	private int reservationNo;
	private int memNo;
	private String memName;
	private String jobName;
	private int roomNo;
	private String reservationDate;
	private String reservationTime;

}
