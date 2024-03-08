package com.ln.intranet.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ln.intranet.common.model.vo.Pagination;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.Notice;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private Logger logger = LoggerFactory.getLogger(MemberDAO.class);
	
	// 로그인DAO
	public Member login(Member inputMember) {
		
		Member loginMember = sqlSession.selectOne("memberMapper.login",inputMember);
		
		return loginMember;
	}
	
	public List<Member> selectChatMemberList() {
		return sqlSession.selectList("memberMapper.selectChatMember");
	}

	// 현재 암호화된 비밀번호 조회
	public String selectEncPw(int memNo) {
		
		return sqlSession.selectOne("memberMapper.selectEncPw", memNo);
	}

	// 비밀번호 변경
	public int changePw(Map<String, Object> paramMap) {
		return sqlSession.update("memberMapper.changePw", paramMap);
	}

	
	// 직원 추가 
	public int signUp(Member member) {
		
		return sqlSession.insert("memberMapper.signUp", member);
	}

	// 직원 검색
	public Member searchMember(int memNo) {
		
		return sqlSession.selectOne("memberMapper.searchMember", memNo);
	}

	// 직원 정보 수정
	public int update(Map<String, Object> paramMap) {

		return sqlSession.update("memberMapper.update", paramMap);
	}

	// 직원 퇴사 처리
	public int delete(int memNo) {

		return sqlSession.update("memberMapper.delete", memNo);
	}
	
	// 퇴사할 직원 정보 조회
	public Member deleteSearch(int memNo) {

		return sqlSession.selectOne("memberMapper.deleteSearch", memNo);
	}

	// 직원 전체 조회
	public List<Member> selectAll() {
		

		return sqlSession.selectList("memberMapper.selectAll");
	}

	// 검색한 직원 조회
	public List<Member> selectOne(Map<String, Object> paramMap) {

		return sqlSession.selectList("memberMapper.searchMem", paramMap);
	}

	

}
