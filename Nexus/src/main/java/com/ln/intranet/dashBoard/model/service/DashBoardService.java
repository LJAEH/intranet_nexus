package com.ln.intranet.dashBoard.model.service;

import java.util.List;

import com.ln.intranet.dashBoard.model.vo.AttnDoughnut;
import com.ln.intranet.dashBoard.model.vo.DeptTeam;
import com.ln.intranet.dashBoard.model.vo.HumanResourceManage;
import com.ln.intranet.dashBoard.model.vo.ProjectPolar;
import com.ln.intranet.dashBoard.model.vo.ProjectTotal;

public interface DashBoardService {

	List<AttnDoughnut> doughnutList(int deptNo);

	List<HumanResourceManage> hrList(int deptNo);

	List<DeptTeam> dtList(int deptNo);

	List<ProjectPolar> polarList(int deptNo);

	List<ProjectTotal> prList(int deptNo);

	List<HumanResourceManage> hrTotal(int deptNo, String searchDate);

}
