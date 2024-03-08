package com.ln.intranet.survey.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SurveyPreview {
	
	private int surveyNo;
	private String surveyTopic;
	private String start;
	private String end;
	private int participation;
	private int totalMember;
	private int respMember;
}
