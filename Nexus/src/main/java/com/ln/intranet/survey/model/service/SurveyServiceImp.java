package com.ln.intranet.survey.model.service;

import java.lang.ProcessHandle.Info;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ln.intranet.common.model.vo.Pagination;
import com.ln.intranet.survey.model.dao.SurveyDAO;
import com.ln.intranet.survey.model.vo.Survey;
import com.ln.intranet.survey.model.vo.SurveyPreview;
import com.ln.intranet.survey.model.vo.SurveyResult;

@Service
public class SurveyServiceImp implements SurveyService {
	
	@Autowired
	private SurveyDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(SurveyServiceImp.class);
	
	// 설문 생성 후 생성 성공시 지문 생성
//	@Transactional(rollbackFor = {RuntimeException.class})
	@Override
	public int createSurvey(Survey survey, List<SurveyResult> resultList) {
		
		//설문생성
		int result = dao.createSurvey(survey);
		int resultCount = 0;
		
		
		//리스트를 이용해 지문추가 
	    if (result > 0) {
	        for (SurveyResult surveyResult : resultList) {
	        	surveyResult.setSurveyNo(result);
	            int successCount = dao.createResult(surveyResult);
	            if(successCount > 0) {
	            	resultCount++;
	            }
	        }
	    }
	    
	    // 리스트숫자와 지문추가숫자 비교해 결과 값 반환
	    if (resultCount == resultList.size()) {
	    	result = 1;
	    } else {
	    	result = 0;
//	    	throw new InsertFailException();					
	    }
		
		return result;
	}
	
	// 설문 조회
	@Override
	public Map<String, Object> surveyList(int cp, int memNo) {
		
		int totalCount = dao.totalCount();
	
		Pagination pagination = new Pagination(cp, totalCount);
		
		List<SurveyPreview> surveyList = dao.surveyList(pagination,memNo); 
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("pagination", pagination);
		map.put("surveyList", surveyList);
		
		return map;
	}
	
	// 설문 디테일 조회
	@Override
	public Map<String, Object> surveyDetail(int surveyNo) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		// 설문 정보 map에 담기 
		Survey survey = dao.surveyDetail(surveyNo);
		
		map.put("survey",survey);
		
		// 지문 리스트 map에 담기
		List<SurveyResult> surveyResultList = dao.surveyResultList(surveyNo);
		map.put("surveyResultList", surveyResultList);
		
		return map;
	}
	
	// 설문참여
	@Override
	public int surveyParticipate(SurveyResult surveyResult) {
		
		int result = dao.surveyParticipate(surveyResult);
		
		return result;
	}
	
	// 설문결과조회
	@Override
	public Map<String, Object> surveyTotal(int surveyNo) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		map = dao.surveyTotal(surveyNo);
		
		return map;
	}
	
	// 설문삭제
	@Override
	public int surveyDelete(int surveyNo) {
		
		return dao.surveyDelete(surveyNo);
	}

	
}
