package com.ln.intranet.dashBoard.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ln.intranet.dashBoard.model.dao.DashBoardDAO;
import com.ln.intranet.dashBoard.model.vo.AttnDoughnut;
import com.ln.intranet.dashBoard.model.vo.DeptTeam;
import com.ln.intranet.dashBoard.model.vo.HumanResourceManage;
import com.ln.intranet.dashBoard.model.vo.ProjectPolar;
import com.ln.intranet.dashBoard.model.vo.ProjectTotal;

@Service
public class DashBoardServiceImpl implements DashBoardService {

	@Autowired
	DashBoardDAO dao;

	// 근태 도넛 리스트 서비스
	@Override
	public List<AttnDoughnut> doughnutList(int deptNo) {

		return dao.doughnutList(deptNo);
	}
	
	
	// 인적 자원 총정보
	@Override
	public List<HumanResourceManage> hrList(int deptNo) {

		return dao.hrList(deptNo);
	}

	// 단순 팀코드
	@Override
	public List<DeptTeam> dtList(int deptNo) {
		
		return dao.dtList(deptNo);
	}

	// 부서 프로젝트 극좌표 그래프
	@Override
	public List<ProjectPolar> polarList(int deptNo) {
		return dao.polarList(deptNo);
	}

	// 부서 모든 프로젝트/과제
	@Override
	public List<ProjectTotal> prList(int deptNo) {
		return dao.prList(deptNo);
	}

	// 해당부서/전체의 근무일람 리스트
	@Override
	public List<HumanResourceManage> hrTotal(int deptNo, String searchDate) {
		return dao.hrTotal(deptNo,searchDate);
	}
}
