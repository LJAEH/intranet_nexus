package com.ln.intranet.main.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.NoticeDetail;

@Repository
public class MainDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// 공지사항 조회
	public List noticeSelect(Member loginMember) {
		List notice = new ArrayList();
		
		notice = sqlSession.selectList("mainMapper.noticeSelect", loginMember);
				
		return notice;
	}

	// 메인 화면에서 공지사항 읽기(kjw)
	public NoticeDetail mainDetail(int noticeNo) {
		return sqlSession.selectOne("mainMapper.mainDetail", noticeNo);
	}
	
	// 메인 화면 결재 리스트
	public List workMinList(Member loginMember) {
		return sqlSession.selectList("mainMapper.workMinList", loginMember);
	}

}
