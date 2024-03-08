package com.ln.intranet.work.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ln.intranet.common.model.vo.UploadFile;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.work.model.vo.ApprovalMember;
import com.ln.intranet.work.model.vo.WorkDetail;
import com.ln.intranet.work.model.vo.WorkGeneralList;

@Repository
public class WorkDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 결재 상신 리스트 조회
	public List<WorkGeneralList> workSend(int memNo) {
		return sqlSession.selectList("workMapper.workSendList", memNo);
	}

	// 결재 상신 일자 지정 조회
	public List<WorkGeneralList> workSendSelectDate(Map<String, Object> map) {
		return sqlSession.selectList("workMapper.workSendSelectDate", map);
	}
	
	// 결재 디테일 조회
	public WorkDetail detailSelect(int workNo) {
		return sqlSession.selectOne("workMapper.detailSelect", workNo);
	}
	
	// 결재취소
	public int workCancle(int workNo) {
		return sqlSession.delete("workMapper.workCancle", workNo);
	}
	

	// 다음 결재자 정보 불러오기
	public Map<String, Object> nextMember(int next) {
		return sqlSession.selectOne("workMapper.nextMember", next);
	}
	
	// 결재 상신 작성(일반결재)
	public int workWrite(Map<String, Object> map) {
		// 결재 작성 후 제출
		int result = sqlSession.insert("workMapper.workWrite", map);
		
		// 임시 저장일 경우
		if(result != 0 && map.get("tempSave").toString().equals("true")) {
			result = sqlSession.update("workMapper.tempSave", map);
		}
		return result;
	}

	// 결재 파일 올리기
	public int insertWorkFile(UploadFile file) {
		return sqlSession.insert("workMapper.insertWorkFile", file);
	}

	// 결재 수신함 - 결재할 문서
	public List<WorkGeneralList> workInbox(int memNo) {
		return sqlSession.selectList("workMapper.workInbox", memNo);
	}

	// 결재자 모달창
	public List<ApprovalMember> approvalMember() {
		return sqlSession.selectList("workMapper.approvalMember");
	}

	// 반려 승인
	public int clickApproval(Map<String, Object> map) {
		return sqlSession.update("workMapper.clickApproval", map);
	}

	// 임시 저장 리스트 불러오기
	public List<WorkDetail> workTemp(Member loginMember) {
		return sqlSession.selectList("workMapper.workTemp", loginMember);
	}

	// 결재 임시저장 제출 완료 후 임시저장 지우기
	public int tempDelete(int workNo) {
		return sqlSession.delete("workMapper.tempDelete", workNo);
	}
	








	

	
}
