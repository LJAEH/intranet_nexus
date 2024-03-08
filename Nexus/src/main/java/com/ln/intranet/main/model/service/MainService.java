package com.ln.intranet.main.model.service;

import java.util.List;

import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.NoticeDetail;

public interface MainService {

	// 공지사항 조회
	List noticeSelect(Member loginMember);

	// 메인 화면에서 공지사항 읽기(kjw)
	NoticeDetail mainDetail(int noticeNo);

	// 메인 화면 결재리스트
	List workMinList(Member loginMember);

}
