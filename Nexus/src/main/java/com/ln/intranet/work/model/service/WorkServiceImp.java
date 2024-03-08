package com.ln.intranet.work.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ln.intranet.attendance.model.dao.AttendanceDAO;
import com.ln.intranet.common.Util;
import com.ln.intranet.common.model.vo.UploadFile;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.work.model.dao.ProjectDAO;
import com.ln.intranet.work.model.dao.WorkDAO;
import com.ln.intranet.work.model.vo.ApprovalMember;
import com.ln.intranet.work.model.vo.ProjectDetail;
import com.ln.intranet.work.model.vo.ProjectList;
import com.ln.intranet.work.model.vo.ProjectTask;
import com.ln.intranet.work.model.vo.ProjectTaskList;
import com.ln.intranet.work.model.vo.WorkDetail;
import com.ln.intranet.work.model.vo.WorkGeneralList;

@Service
public class WorkServiceImp implements WorkService {
	
	@Autowired
	WorkDAO dao;
	@Autowired
	ProjectDAO pDao;
	@Autowired
	AttendanceDAO attnDao;
	
	private Logger logger = LoggerFactory.getLogger(WorkServiceImp.class);
	
	// 결재 상신 리스트 조회
	@Override
	public List<WorkGeneralList> workSend(int memNo) {
		return dao.workSend(memNo);
	}

	// 결재 상신 날짜 지정
	@Override
	public List<WorkGeneralList> workSendSelectDate(Map<String, Object> map) {
		return dao.workSendSelectDate(map);
	}
	
	// 결재 디테일 조회
	@Override
	public WorkDetail detailSelect(int workNo) {
		
		WorkDetail detailSelect = dao.detailSelect(workNo);
		
		if(detailSelect.getContent() != null) {			
			detailSelect.setContent(Util.newLineClear(detailSelect.getContent()));
			detailSelect.setContent(Util.XSSClear(detailSelect.getContent()));
		}
		
		return detailSelect;
	}
	
	// 결재취소 (int workNo 에서 typeNo 포함한 map 으로 바뀌었습니다)
	@Override
	public int workCancle(Map<String, Object> map) {
		
		int workNo = Integer.parseInt(map.get("workNo").toString());	
		int typeNo = Integer.parseInt(map.get("typeNo").toString());	
		
		
		if( typeNo == 4 ) {
			return pDao.projectCancle(workNo);
		} else if ( typeNo == 5 ) {
			return pDao.taskCancle(workNo);
		} else {
			return dao.workCancle(workNo);			
		}
		
	}

	// 결재 상신 작성(일반결재)
	@Override
	@Transactional
	public int workWrite(Map<String, Object> map, 
			MultipartFile uploadFile, HttpServletRequest req,
			List<String> taskTitleList) {
		
		int result = 0;
		
		int typeNo = Integer.parseInt(map.get("typeNo").toString());
		
		
		if(map.get("next").toString().equals("undefined")) {
			map.put("next", "");
		}
		
		if(map.get("next").toString().length() != 0) {
			// 다음 결재자 정보 불러오기
			Map<String, Object> nextMember = dao.nextMember(Integer.parseInt(map.get("next").toString()));
			String approvalList = nextMember.get("MEM_NAME") + "," + "진행중" + "," + nextMember.get("DEPT_NAME") + " / " + nextMember.get("JOB_NAME") + ",,";
			map.put("approvalList", approvalList);	
		}
		
		// 프로젝트과제 객체 생성
		List<ProjectTask> taskList = new ArrayList<>();

		
		// 결재 타입에 따른 결재 테이블에 값 넣기
		if(typeNo == 1) {
			map.put("title", Util.XSSHandling(map.get("title").toString()));
			map.put("content", Util.XSSHandling(map.get("content").toString()));
			map.put("content", Util.newLineHandling(map.get("content").toString()));
			
		} else if(typeNo == 3) {
			
			map.put("content", Util.XSSHandling(map.get("content").toString()));
			map.put("content", Util.newLineHandling(map.get("content").toString()));
			
		} else if(typeNo == 4 ) {
			
			map.put("title", Util.XSSHandling(map.get("title").toString()));
			map.put("content", Util.XSSHandling(map.get("content").toString()));
			map.put("content", Util.newLineHandling(map.get("content").toString()));
			
	
			for(int i = 0; i < taskTitleList.size(); i++) {
				ProjectTask task = new ProjectTask();
				task.setTitle(taskTitleList.get(i));				
				taskList.add(task);
			}

		} else if(typeNo == 5 ) {

			map.put("title", Util.XSSHandling(map.get("title").toString()));
			map.put("content", Util.XSSHandling(map.get("content").toString()));
			map.put("content", Util.newLineHandling(map.get("content").toString()));
			
		}
		
		// 결재타입 1 2 3 일 때 
		if (typeNo == 1 ||  typeNo == 2 || typeNo == 3) {

			// 파일 올리기
			int insertResult = dao.workWrite(map);
			int workNo = Integer.parseInt(map.get("workNo").toString());
			int memNo = Integer.parseInt(map.get("memNo").toString());
			
			if(insertResult != 0) {
				
				if(uploadFile.getSize() != 0) {

					UploadFile file = new UploadFile();
					String reName = "";
					
					String webPath = "/resources/file/";
					String folderPath = req.getSession().getServletContext().getRealPath(webPath);
					
					reName = Util.fileRename(uploadFile.getOriginalFilename());
					
					file.setWorkNo(workNo);
					file.setFileOrigin(uploadFile.getOriginalFilename());
					file.setFileReName(webPath + reName);
					
					result = dao.insertWorkFile(file);
						
					if(result != 0) {
						try {
							uploadFile.transferTo(new File(folderPath + reName));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					result = insertResult;
				}
				
			}


			// 프로젝트 생성 + 프로젝트 과제 생성
		} else if (typeNo == 4 ){
			
			// 파일 올리기
			int resultNo = pDao.createProject(map);
			int projectNo = Integer.parseInt(map.get("projectNo").toString());
			int memNo = Integer.parseInt(map.get("memNo").toString());
			int taskCount = 0;
			
			if(resultNo > 0 ) {
				for (ProjectTask task : taskList) {
				    task.setProjectNo(projectNo);
				    task.setDeptNo((Integer)map.get("deptNo"));
				    int successCount = pDao.createTask(task);
				    if(successCount > 0)  taskCount++;	    
				}	
			}
			
			if(resultNo != 0) {
				
				if(uploadFile.getSize() != 0) {

					UploadFile file = new UploadFile();
					String reName = "";
					
					String webPath = "/resources/file/";
					String folderPath = req.getSession().getServletContext().getRealPath(webPath);
					
					reName = Util.fileRename(uploadFile.getOriginalFilename());
					
					file.setProjectNo(projectNo);
					file.setFileOrigin(uploadFile.getOriginalFilename());
					file.setFileReName(webPath + reName);
					
					result = pDao.insertProjectFile(file);
						
					if(result != 0) {
						try {
							uploadFile.transferTo(new File(folderPath + reName));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					result = resultNo;
				}
				
			}
			
		} else {
			
			// 파일 올리기
			int resultNo = pDao.updateTask(map);
			int taskNo = Integer.parseInt(map.get("taskNo").toString());
			int memNo = Integer.parseInt(map.get("memNo").toString());
			
			if(resultNo != 0) {
				
				if(uploadFile.getSize() != 0) {

					UploadFile file = new UploadFile();
					String reName = "";
					
					String webPath = "/resources/file/";
					String folderPath = req.getSession().getServletContext().getRealPath(webPath);
					
					reName = Util.fileRename(uploadFile.getOriginalFilename());
					
					file.setTaskNo(taskNo);
					file.setFileOrigin(uploadFile.getOriginalFilename());
					file.setFileReName(webPath + reName);
					
					result = pDao.insertTaskFile(file);
						
					if(result != 0) {
						try {
							uploadFile.transferTo(new File(folderPath + reName));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					result = resultNo;
				}
				
			}
			
		}
		
		return result;
	}
	
	// 결재자 모달창
	@Override
	public List<ApprovalMember> approvalMember() {
		return dao.approvalMember();
	}

	// 결재 수신함 - 결재할 문서
	@Override
	public List<WorkGeneralList> workInbox(int memNo) {
		return dao.workInbox(memNo);
	}
	
	
	// 반려 승인
	@Override
	public int clickApproval(Map<String, Object> map) {
		
		// 결재자 경로 변경 및 추가
		String approvalList = "";
		String[] strArrOne = map.get("approvalList").toString().split(",,");

		if(map.get("btnName").toString().equals("reject")) {
			String[] strArrTwo = strArrOne[strArrOne.length - 1].split(",");
			strArrTwo[1] = "반려";
			
			strArrOne[strArrOne.length - 1] = strArrTwo[0] + "," + strArrTwo[1] + "," + strArrTwo[2];

		} else {
			String[] strArrTwo = strArrOne[strArrOne.length - 1].split(",");
			strArrTwo[1] = "승인";
			
			strArrOne[strArrOne.length - 1] = strArrTwo[0] + "," + strArrTwo[1] + "," + strArrTwo[2];
		}
		
		for(int i = 0; i < strArrOne.length; i++) {
			approvalList += strArrOne[i] + ",,";
		}
		map.put("approvalList", approvalList);
		
		
		
		String checkbox_flag = map.get("checkbox_flag").toString();
		if(checkbox_flag.equals("false")) {
			Map<String, Object> nextMember = dao.nextMember(Integer.parseInt(map.get("next").toString()));
			approvalList += nextMember.get("MEM_NAME") + "," + "진행중" + "," + nextMember.get("DEPT_NAME") + " / " + nextMember.get("JOB_NAME") + ",,";
			map.put("approvalList", approvalList);
		}
		
		// 반려시 의견이 있다면 보안적용
		if(map.get("btnName").toString().equals("reject") && map.get("opinion") != null) {
			map.put("opinion", Util.XSSHandling(map.get("opinion").toString()));
			map.put("opinion", Util.newLineHandling(map.get("opinion").toString()));
		}
		
		// 연차 또는 출장 관련 결재 최종 승인 시 근태 테이블에 값 넣기
		int typeNo = Integer.parseInt(map.get("typeNo").toString());
			
		if((typeNo == 2 || typeNo == 3) && checkbox_flag.equals("true") && map.get("btnName").toString().equals("approve")) {
			int attnResult = attnDao.insertAttn(map);
		}
		
		if(typeNo == 4) {
			return pDao.projectApproval(map);
		} else if (typeNo == 5) {
			return pDao.taskApproval(map);
		} else {
			return dao.clickApproval(map);
		}
		
		
	}

	// 프로젝트과제 리스트 뽑기용
	@Override
	public List<ProjectTaskList> ptList(int deptNo) {
		return pDao.ptList(deptNo);
	}

	// 프로젝트 리스트 뽑기용
	@Override
	public List<ProjectList> pList(int deptNo) {
		return pDao.pList(deptNo);
	}

	// 임시저장 리스트 불러오기
	@Override
	public List<WorkDetail> workTemp(Member loginMember) {
		return dao.workTemp(loginMember);
	}
	
	// 결재 임시저장 제출 완료 후 임시저장 지우기
	@Override
	public int tempDelete(int workNo) {
		return dao.tempDelete(workNo);
	}
	
	
	//====================== 프로젝트/과제 ========================
	
	//프로젝트 디테일
	@Override
	public ProjectDetail projectDetailSelect(int projectNo) {
		
		ProjectDetail detailSelect = pDao.projectDetailSelect(projectNo);
		
		if(detailSelect.getContent() != null) {			
			detailSelect.setContent(Util.newLineClear(detailSelect.getContent()));
			detailSelect.setContent(Util.XSSClear(detailSelect.getContent()));
		}
		
		return detailSelect;
	}

	@Override
	public WorkDetail taskDetailSelect(int taskNo) {
		WorkDetail detailSelect = pDao.taskDetailSelect(taskNo);
		
		if(detailSelect.getContent() != null) {			
			detailSelect.setContent(Util.newLineClear(detailSelect.getContent()));
		}
		
		return detailSelect;
	}
	
	
	

	// 상신함 - 프로젝트리스트
	@Override
	public List<WorkGeneralList> projectSendList(int memNo) {		
		return pDao.projectSendList(memNo);
	}

	// 상신함 - 과제리스트
	@Override
	public List<WorkGeneralList> taskSendList(int memNo) {
		return pDao.taskSendList(memNo);
	}

	// 수신함 - 프로젝트리스트
	@Override
	public List<WorkGeneralList> projectInbox(int memNo) {
		return pDao.projectInbox(memNo);
	}
	
	// 수신함 - 과제리스트
	@Override
	public List<WorkGeneralList> taskInbox(int memNo) {
		return pDao.taskInbox(memNo);
	}




	
	
	
	
	
	
	
	
}
