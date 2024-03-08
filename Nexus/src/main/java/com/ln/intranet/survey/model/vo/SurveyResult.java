package com.ln.intranet.survey.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SurveyResult {

	private int surveyNo;
	private int memNo;
	private String memName;
	private String question;
	private int optionNo;
	private String optionAnnotation;
	private int optionMemberCount;
}
