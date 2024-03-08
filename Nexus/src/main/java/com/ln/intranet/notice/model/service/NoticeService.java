package com.ln.intranet.notice.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ln.intranet.notice.model.vo.Notice;
import com.ln.intranet.notice.model.vo.NoticeDetail;

public interface NoticeService {


	Map<String, Object> selectPublicNoticeList(int cp);

	int writeNotice(String webPath, String folderPath, MultipartFile uploadFile, Notice notice) throws IOException;

	NoticeDetail noticeDetail(int noticeNo);

	/** 공지사항 수정
	 * @param noticeNo
	 * @param detail
	 * @param file 
	 * @param folderPath 
	 * @param webPath 
	 * @return result
	 */
	int updateNotice(NoticeDetail detail, MultipartFile uploadFile, String webPath, String folderPath) throws IOException;

	/** 공지사항 삭제
	 * @param noticeNo
	 * @param uploadFile 
	 * @return result
	 */
	int deleteNotice(int noticeNo);

}
