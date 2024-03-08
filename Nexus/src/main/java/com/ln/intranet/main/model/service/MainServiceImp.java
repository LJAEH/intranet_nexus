package com.ln.intranet.main.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ln.intranet.common.Util;
import com.ln.intranet.main.model.dao.MainDAO;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.NoticeDetail;

@Service
public class MainServiceImp implements MainService{

	@Autowired
	MainDAO dao;
	
	// 공지사항 조회
	@Override
	public List noticeSelect(Member loginMember) {
		return dao.noticeSelect(loginMember);
	}

	// 메인 화면에서 공지사항 읽기(kjw)
	@Override
	public NoticeDetail mainDetail(int noticeNo) {
		NoticeDetail mainDetail = dao.mainDetail(noticeNo);
		
		mainDetail.setContent(Util.newLineClear(mainDetail.getContent()));
		
		return mainDetail;
	}

	// 메인 화면 결재리스트
	@Override
	public List workMinList(Member loginMember) {
		return dao.workMinList(loginMember);
	}
	
	
	
	
	
}
