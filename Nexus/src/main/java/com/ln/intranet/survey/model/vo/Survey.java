package com.ln.intranet.survey.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Survey {
	
	private int surveyNo;
	private String surveyTopic;
	private String surveyContent;
	private String start;
	private String end;

}
