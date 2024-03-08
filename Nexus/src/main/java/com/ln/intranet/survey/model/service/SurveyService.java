package com.ln.intranet.survey.model.service;

import java.util.List;
import java.util.Map;

import com.ln.intranet.survey.model.vo.Survey;
import com.ln.intranet.survey.model.vo.SurveyResult;

public interface SurveyService {

	int createSurvey(Survey survey, List<SurveyResult> resultList);

	Map<String, Object> surveyList(int cp, int memNo);

	Map<String, Object> surveyDetail(int surveyNo);

	int surveyParticipate(SurveyResult surveyResult);

	Map<String, Object> surveyTotal(int surveyNo);

	int surveyDelete(int surveyNo);

}
