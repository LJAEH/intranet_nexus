package com.ln.intranet.dept.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ln.intranet.dept.model.vo.BoardDetail;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.NoticeDetail;
import com.ln.intranet.work.model.vo.Project;

public interface DeptService {

	Map<String, Object> boardList(int cp, int deptNo);

	int insertBoard(BoardDetail detail, MultipartFile uploadFile, String filePath, String folderPath) throws IOException;

	BoardDetail boardDetail(int boardNo);

	Map<String, Object> selectDeptNoticeList(Integer cp, int deptNo);


	NoticeDetail noticeDetail(Map<String, Object> map);

	int writeNotice(String webPath, String folderPath, MultipartFile uploadFile, Map<String, Object> map) throws IOException;

	int boardDelete(int boardNo);

	/** 부서 공지사항 수정
	 * @param detail
	 * @param uploadFile
	 * @param webPath
	 * @param folderPath
	 * @param fileData 
	 * @return
	 */
	int updateDeptNotice(NoticeDetail detail, MultipartFile uploadFile, String webPath, String folderPath, boolean fileData) throws IOException;

	// 본인 부서 프로젝트 조회하기(kjw)
	List projectList(Member loginMember);
	
	// 본인 팀 연차 현황 조회(kjw)
	List vacationList(Member loginMember);

	// 본인 팀 출장 현황 조회(kjw)
	List businessList(Member loginMember);

	/** 부서 공지사항 삭제
	 * @param noticeNo
	 * @return result
	 */
	int deleteNotice(int noticeNo);

	/** 부서 게시판 수정
	 * @param detail
	 * @param uploadFile
	 * @param webPath
	 * @param folderPath
	 * @param fileData 
	 * @return result
	 */
	int updateBoardNotice(BoardDetail detail, MultipartFile uploadFile, String webPath, String folderPath, boolean fileData) throws IOException;



}
