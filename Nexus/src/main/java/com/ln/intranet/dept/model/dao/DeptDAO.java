package com.ln.intranet.dept.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ln.intranet.common.model.vo.Pagination;
import com.ln.intranet.common.model.vo.UploadFile;
import com.ln.intranet.dept.model.vo.Board;
import com.ln.intranet.dept.model.vo.BoardDetail;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.Notice;
import com.ln.intranet.notice.model.vo.NoticeDetail;
import com.ln.intranet.work.model.vo.Project;


@Repository
public class DeptDAO {

	private Logger logger = LoggerFactory.getLogger(DeptDAO.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	// 부서 게시판 글 개수 총합
	public int totalCount(int deptNo) {
		return sqlSession.selectOne("deptMapper.totalCount",deptNo);
	}
	
	// 부서 게시판 목록 조회
	public List<Board> boardList(Pagination pagination, int deptNo) {

		int offset = (pagination.getCurrentPage()-1)*pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset,pagination.getLimit());
		
		return sqlSession.selectList("deptMapper.boardList",deptNo,rowBounds);
	}
	
	// 부서게시판 글작성 DAO (파일업로드 전)
	public int insertBoard(BoardDetail detail) {
		
		int result =  sqlSession.insert("deptMapper.insertBoard",detail);
		
		if(result > 0) result = detail.getBoardNo();
		
		return result;
	}
	
	// 부서게시판 파일 업로드
	public int insertBoardFile(UploadFile file) {

		return sqlSession.insert("deptMapper.insertBoardFile",file);
	}
	
	// 부서게시판 게시글 상세조회
	public BoardDetail boardDetail(int boardNo) {
		
		return sqlSession.selectOne("deptMapper.boardDetail",boardNo);
	}

	// 부서 공지사항 카운트
	public int totalList(int deptNo) {
		
		return sqlSession.selectOne("noticeMapper.totalCount", deptNo);
	}

	// 부서 공지사항 목록 조회
	public List<Notice> selectDeptNoticeList(Pagination pagination, int deptNo) {
		
		int offset = (pagination.getCurrentPage()-1)*pagination.getLimit();
		RowBounds rowBounds = new RowBounds(offset,pagination.getLimit());
		
		return sqlSession.selectList("noticeMapper.selectDeptNoticeList",deptNo , rowBounds);
		
	}



	public NoticeDetail noticeDetail(Map<String, Object> map) {
		return sqlSession.selectOne("noticeMapper.deptNoticeDetail",map);
	}

	public int insertNotice(Map<String, Object> map) {
		int result = sqlSession.insert("noticeMapper.insertDeptNotice", map);
		
		if(result > 0) result = (int) map.get("noticeNo");
		
		return result;
	}

	public int insertNoticeFile(UploadFile file) {
		
		return sqlSession.insert("noticeMapper.insertDeptNoticeFile", file);
	}
	
	// 게시글 삭제 + 파일 삭제
	public int boardDelete(int boardNo) {
		
		int boardFileDelete = sqlSession.delete("deptMapper.boardFileDelete",boardNo);
		
		int boardDelete = sqlSession.delete("deptMapper.boardDelete",boardNo);
		
		return boardFileDelete + boardDelete;
	}

	// 부서 공지사항 수정
	public int updateDeptNotice(NoticeDetail detail) {

		return sqlSession.update("deptMapper.updateDeptNotice", detail);
	}

	// 파일 수정
	public int updateNoticeFile(UploadFile file) {
		
		return sqlSession.update("deptMapper.updateDeptFileNotice", file);
	}

	// 본인 부서 프로젝트 리스트 조회(kjw)
	public List projectList(Member loginMember) {
		return sqlSession.selectList("deptMapper.projectList", loginMember);

	}
	
	// 본인 팀 연차현황 조회(kjw)
	public List vacationList(Member loginMember) {
		return sqlSession.selectList("deptMapper.vacationList", loginMember);
	}

	// 본인 팀 출장 현황 조회(kjw)
	public List businessList(Member loginMember) {
		return sqlSession.selectList("deptMapper.businessList", loginMember);
	}

	// 부서 공지사항 삭제
	public int deleteNotice(int noticeNo) {

		int file = sqlSession.delete("deptMapper.deleteFileDeptNotice", noticeNo);

		int notice = sqlSession.delete("deptMapper.deleteDeptNotice", noticeNo);
		 
		return file + notice;
	}

	// 부서 게시판 수정
	public int updateBoardNotice(BoardDetail detail) {

		return sqlSession.update("deptMapper.updateBoardNotice", detail);
	}

	// 부서 게시판 파일 수정
	public int updateBoardNoticeFile(UploadFile file) {

		return sqlSession.update("deptMapper.updateBoardFileNotice", file);
	}

	// 공지사항 파일 지우기 클릭시 삭제
	public int fileDelete(int noticeNo) {
		
		return sqlSession.delete("deptMapper.deleteFileDeptNotice", noticeNo);
	}

	// 게시판 파일 지우기 클릭시 삭제
	public int fileDeleteBoard(int boardNo) {

		return sqlSession.delete("deptMapper.boardFileDelete",boardNo);
	}

	
	
}
