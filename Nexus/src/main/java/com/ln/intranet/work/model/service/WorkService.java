package com.ln.intranet.work.model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.work.model.vo.ApprovalMember;
import com.ln.intranet.work.model.vo.ProjectDetail;
import com.ln.intranet.work.model.vo.ProjectList;
import com.ln.intranet.work.model.vo.ProjectTaskList;
import com.ln.intranet.work.model.vo.WorkDetail;
import com.ln.intranet.work.model.vo.WorkGeneral;
import com.ln.intranet.work.model.vo.WorkGeneralList;


public interface WorkService {

	// 결재 상신 리스트 조회
	List<WorkGeneralList> workSend(int memNo);

	// 결재 상신 날짜 지정
	List<WorkGeneralList> workSendSelectDate(Map<String, Object> map);
	
	// 결재 디테일 조회
	WorkDetail detailSelect(int workNo);

	// 결재 상신 작성(일반결재)
	int workWrite(Map<String, Object> map, MultipartFile uploadFile, HttpServletRequest req, List<String> taskTitleList);

	// 결재자 모달창
	List<ApprovalMember> approvalMember();
	
	// 결재 수신함 - 결재할 문서
	List<WorkGeneralList> workInbox(int memNo);

	// 결재 취소 (int workNo 에서 typeNo 포함한 map 으로 바뀌었습니다)
	int workCancle(Map<String, Object> map);
	
	// 프로젝트상신함리스트
	List<WorkGeneralList> projectSendList(int memNo);
	// 프로젝트과제 상신함 리스트
	List<WorkGeneralList> taskSendList(int memNo);

	// 반려 또는 승인	
	int clickApproval(Map<String, Object> map);

	List<ProjectTaskList> ptList(int deptNo);

	List<ProjectList> pList(int deptNo);
	
	// 임시저장 리스트 불러오기
	List<WorkDetail> workTemp(Member loginMember);

	ProjectDetail projectDetailSelect(int projectNo);

	WorkDetail taskDetailSelect(int taskNo);

	List<WorkGeneralList> projectInbox(int memNo);

	List<WorkGeneralList> taskInbox(int memNo);

	// 결재 임시저장 제출 완료 후 임시저장 지우기
	int tempDelete(int workNo);


}
