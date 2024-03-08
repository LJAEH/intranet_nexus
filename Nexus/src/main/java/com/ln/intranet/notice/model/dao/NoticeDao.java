package com.ln.intranet.notice.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ln.intranet.common.model.vo.Pagination;
import com.ln.intranet.common.model.vo.UploadFile;
import com.ln.intranet.notice.model.vo.Notice;
import com.ln.intranet.notice.model.vo.NoticeDetail;

@Repository
public class NoticeDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;


	public List<Notice> selectPublicNoticeList(Pagination pagination) {
		
		int offset = (pagination.getCurrentPage()-1)*pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset,pagination.getLimit());
		
		return sqlSession.selectList("noticeMapper.selectPublicNoticeList",rowBounds);
	}

	// 공지사항 글 개수 조회
	public int totalCount() {
		return sqlSession.selectOne("noticeMapper.totalList");
	}

	// 공지사항 작성
	public int insertNotice(Notice notice) {
		
		int result = sqlSession.insert("noticeMapper.insertNotice", notice);
		
		if(result > 0) result = (int) notice.getNoticeNo();
		
		return result;
	}

	// 파일 업로드
	public int insertNoticeFile(UploadFile file) {
		
		return sqlSession.insert("noticeMapper.insertNoticeFile", file);
	}

	public NoticeDetail noticeDetail(int noticeNo) {
		return sqlSession.selectOne("noticeMapper.noticeDetail", noticeNo);
	}

	// 공지사항 수정
	public int updateNotice(NoticeDetail detail) {

		return sqlSession.update("noticeMapper.updateNotice", detail);
	}

	// 공지사항 삭제
	public int deleteNotice(int noticeNo) {
		
		int file = sqlSession.delete("noticeMapper.deleteFileNotice", noticeNo);

		 int notice = sqlSession.delete("noticeMapper.deleteNotice", noticeNo);
		 
		return file + notice;
	}

	// 파일 삭제
	public void deleteFileNotice(Map<String, Object> paramMap) {
		sqlSession.delete("noticeMapper.deleteFileNotice", paramMap);
	}

	// 파일 수정
	public int updateNoticeFile(UploadFile file) {
		
		return sqlSession.update("noticeMapper.updateFileNotice", file);
	}


}
