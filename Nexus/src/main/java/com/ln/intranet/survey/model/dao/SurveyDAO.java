package com.ln.intranet.survey.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ln.intranet.common.model.vo.Pagination;
import com.ln.intranet.survey.model.vo.Survey;
import com.ln.intranet.survey.model.vo.SurveyPreview;
import com.ln.intranet.survey.model.vo.SurveyResult;

@Repository
public class SurveyDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	// 설문 생성후 surveyNo를 받아온다	
	public int createSurvey(Survey survey) {
		int result = sqlSession.insert("surveyMapper.createSurvey",survey);
		
		if(result > 0) result = survey.getSurveyNo();
		
		return result;
	}
	// 지문 생성 DAO
	public int createResult(SurveyResult surveyResult) {
		
		int result = sqlSession.insert("surveyMapper.createResult",surveyResult);
		
		return result; 
	}
	
	
	// 페이지네이션 생성용 전체 설문 수 조회
	public int totalCount() {
		return sqlSession.selectOne("surveyMapper.totalCount");
	}	
	// 전체 설문 리스트 조회
	public List<SurveyPreview> surveyList(Pagination pagination, int memNo) {

		int offset = (pagination.getCurrentPage()-1)*pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset,pagination.getLimit());
		
		return sqlSession.selectList("surveyMapper.surveyList",memNo,rowBounds);
	}
	
	
	// 설문 디테일 조회
	public Survey surveyDetail(int surveyNo) {

		return sqlSession.selectOne("surveyMapper.surveyDetail",surveyNo);
	}
	public List<SurveyResult> surveyResultList(int surveyNo) {

		return sqlSession.selectList("surveyMapper.surveyResultList",surveyNo);
	}

	
	// 설문 참여
	public int surveyParticipate(SurveyResult surveyResult) {

		return sqlSession.insert("surveyMapper.surveyParticipate",surveyResult);
	}
	public Map<String, Object> surveyTotal(int surveyNo) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		// 설문 정보 map에 담기 
		Survey survey = sqlSession.selectOne("surveyMapper.surveyDetail",surveyNo);
		map.put("survey",survey);
		
		// 지문 리스트 + 투표인원 map에 담기
		List<SurveyResult> surveyResultList = sqlSession.selectList("surveyMapper.surveyResultList",surveyNo);
		map.put("surveyResultList", surveyResultList);
		
		// 전체 멤버 수 map에 담기
 		int respMember = sqlSession.selectOne("surveyMapper.respMember",surveyNo);
 		map.put("respMember", respMember);
		
		return map;
	}
	
	// 설문삭제
	public int surveyDelete(int surveyNo) {
		int deleteSurvey = sqlSession.delete("surveyMapper.surveyDelete",surveyNo);
		int deleteResult = sqlSession.delete("surveyMapper.surveyResultDelete",surveyNo);
		return deleteSurvey + deleteResult;
	}

}
