package com.ln.intranet.dashBoard.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ln.intranet.dashBoard.model.vo.AttnDoughnut;
import com.ln.intranet.dashBoard.model.vo.DeptTeam;
import com.ln.intranet.dashBoard.model.vo.HumanResourceManage;
import com.ln.intranet.dashBoard.model.vo.ProjectPolar;
import com.ln.intranet.dashBoard.model.vo.ProjectTotal;

@Repository
public class DashBoardDAO {
	
	@Autowired
	SqlSessionTemplate sqlSession;

	// 근태 도넛 리스트 DAO
	public List<AttnDoughnut> doughnutList(int deptNo) {
		return sqlSession.selectList("dashBoardMapper.doughnutList",deptNo);
	}

	// 인적 자원 정보 DAO
	public List<HumanResourceManage> hrList(int deptNo) {
		return sqlSession.selectList("dashBoardMapper.hrList",deptNo);
	}

	// 단순 팀부서 정보 DAO
	public List<DeptTeam> dtList(int deptNo) {
		return sqlSession.selectList("dashBoardMapper.dtList",deptNo);
	}

	// 부서 프로젝트 극좌표 그래프
	public List<ProjectPolar> polarList(int deptNo) {
		return sqlSession.selectList("dashBoardMapper.polarList",deptNo);
	}

	// 부서 모든 프로젝트/과제
	public List<ProjectTotal> prList(int deptNo) {
		return sqlSession.selectList("dashBoardMapper.projectTotal",deptNo);
	}
	
	// 총 인적자원 조회
	public List<HumanResourceManage> hrTotal(int deptNo, String searchDate) {
	    Map<String, Object> map = new HashMap<>();
	    map.put("deptNo", deptNo);
	    map.put("searchDate", searchDate);
		return sqlSession.selectList("dashBoardMapper.hrTotal",map);
	}

}
