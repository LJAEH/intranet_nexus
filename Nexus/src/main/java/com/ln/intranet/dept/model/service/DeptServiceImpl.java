package com.ln.intranet.dept.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ln.intranet.common.Util;
import com.ln.intranet.common.model.exception.InsertFailException;
import com.ln.intranet.common.model.vo.Pagination;
import com.ln.intranet.common.model.vo.UploadFile;
import com.ln.intranet.dept.controller.DeptController;
import com.ln.intranet.dept.model.dao.DeptDAO;
import com.ln.intranet.dept.model.vo.Board;
import com.ln.intranet.dept.model.vo.BoardDetail;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.Notice;
import com.ln.intranet.notice.model.vo.NoticeDetail;
import com.ln.intranet.work.model.vo.Project;


@Service
public class DeptServiceImpl implements DeptService{
	
	@Autowired
	private DeptDAO dao;
	
	private Logger logger = LoggerFactory.getLogger(DeptController.class);
	

	
	/** 부서 게시판 글목록 조회 서비스
	 *
	 */
	@Override
	public Map<String, Object> boardList(int cp, int deptNo) {
		
		int totalCount = dao.totalCount(deptNo);
		
		Pagination pagination = new Pagination(cp, totalCount);
		
		List<Board> boardList = dao.boardList(pagination,deptNo);
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
	}


	/** 게시글 작성 서비스
	 * @throws IOException 
	 * @throws IllegalStateException 
	 *
	 */
	@Transactional(rollbackFor = {RuntimeException.class})
	@Override
	public int insertBoard(BoardDetail detail, MultipartFile uploadFile, String filePath, String folderPath) throws IOException {
		
		detail.setBoardTitle(Util.XSSHandling(detail.getBoardTitle()));
		detail.setBoardContent(Util.XSSHandling(detail.getBoardContent()));
		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));

		int boardNo = dao.insertBoard(detail);

		UploadFile file = new UploadFile();
		String reName = null;
		
		if ( boardNo > 0 ) {

			if(uploadFile.getSize()>0) {
				
				reName = Util.fileRename(uploadFile.getOriginalFilename());
				
				file.setBoardNo(boardNo);
				file.setFileOrigin(uploadFile.getOriginalFilename());
				file.setFileReName(filePath+reName);

				int result = dao.insertBoardFile(file);
				
				if (result > 0) {
					uploadFile.transferTo(new File(folderPath+reName));
				} else {
					throw new InsertFailException();	
				}
			}
			
		}
		
		return boardNo;
	}


	
	@Override
	public BoardDetail boardDetail(int boardNo) {
	
		return dao.boardDetail(boardNo);
	}


	// 부서 공지사항 list
	@Override
	public Map<String, Object> selectDeptNoticeList(Integer cp ,int deptNo) {
		
	    int totalCount = dao.totalList(deptNo);
	    
	    Pagination pagination = new Pagination(cp, totalCount);
	   
	    
	    List<Notice> boardList = dao.selectDeptNoticeList(pagination ,deptNo);
	    
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("pagination", pagination);
	    map.put("boardList", boardList);
	    
	    return map;
	}



	// 부서 공지사항 디테일 service
	@Override
	public NoticeDetail noticeDetail(Map<String, Object> map) {
		
		return dao.noticeDetail(map);
	}


	// 부서 공지사항 추가
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int writeNotice(String webPath, String folderPath, MultipartFile uploadFile, Map<String, Object> map)throws IOException {
		
		int noticeNo = dao.insertNotice(map);
		
		
		UploadFile file = new UploadFile();
		String reName = null;
		
		if(noticeNo > 0) {
			
			if(uploadFile.getSize() > 0 ) {
				
				reName = Util.fileRename(uploadFile.getOriginalFilename());
				
				file.setNoticeNo(noticeNo);
				file.setFileOrigin(uploadFile.getOriginalFilename());
				file.setFileReName(webPath+reName);
				
				int result = dao.insertNoticeFile(file);
				
				
				if(result > 0) {
					uploadFile.transferTo(new File(folderPath + reName));
				}else {
					throw new InsertFailException();
				}
			}
		}
	
		
		return noticeNo;
	}

	// 게시글삭제
	@Override
	public int boardDelete(int boardNo) {
		
		return dao.boardDelete(boardNo);
	}


	// 부서 공지사항 수정
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int updateDeptNotice(NoticeDetail detail, MultipartFile uploadFile, String webPath, String folderPath, boolean fileData)throws IOException {
		
		detail.setContent(Util.newLineHandling(detail.getContent()));
		
		int result =  dao.updateDeptNotice(detail);
		
		UploadFile file = new UploadFile();
		String reName = null;
		
		logger.debug("result" + result);
		
		if(result > 0) {
			if(uploadFile != null) {
				reName = Util.fileRename(uploadFile.getOriginalFilename());
				
				file.setNoticeNo(detail.getNoticeNo());
				file.setFileOrigin(uploadFile.getOriginalFilename());
				file.setFileReName(webPath + reName);
				
				int insertFile = dao.updateNoticeFile(file);
				
				if(insertFile > 0) {
					uploadFile.transferTo(new File(folderPath + reName));
				} else {
					throw new InsertFailException();
				}
			}
			
			if(fileData) {
				
				int noticeNo = detail.getNoticeNo();
				System.out.println("fileData는 트루입니다");
				int fileResult = dao.fileDelete(noticeNo);
				
			}
		}
		
		
		return result;
	}
	
	
	// 부서 공지사항 삭제
	@Override
	public int deleteNotice(int noticeNo) {

		return dao.deleteNotice(noticeNo);
	}


	// 본인 부서 프로젝트 리스트(kjw)
	@Override
	public List projectList(Member loginMember) {
		return dao.projectList(loginMember);
	}

	// 본인 팀 연차현황 조회(kjw)
	@Override
	public List vacationList(Member loginMember) {
		return dao.vacationList(loginMember);
	}

	// 본인 팀 출장 현황 조회(kjw)
	@Override
	public List businessList(Member loginMember) {
		return dao.businessList(loginMember);
	}


	// 부서 게시판 수정
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int updateBoardNotice(BoardDetail detail, MultipartFile uploadFile, String webPath, String folderPath, boolean fileData)throws IOException {

		detail.setBoardContent(Util.newLineHandling(detail.getBoardContent()));
		
		int result =  dao.updateBoardNotice(detail);
		
		UploadFile file = new UploadFile();
		String reName = null;
		
		if(result > 0) {
			if(uploadFile != null) {
				reName = Util.fileRename(uploadFile.getOriginalFilename());
				
				file.setBoardNo(detail.getBoardNo());
				file.setFileOrigin(uploadFile.getOriginalFilename());
				file.setFileReName(webPath + reName);
				
				int insertFile = dao.updateBoardNoticeFile(file);
				
				if(insertFile > 0) {
					uploadFile.transferTo(new File(folderPath + reName));
				} else {
					throw new InsertFailException();
				}
			}
			
			if(fileData) {
				
				int boardNo = detail.getBoardNo();
				int fileResult = dao.fileDeleteBoard(boardNo);
				
			}
		}
		
		
		return result;
	}


	
	




}
