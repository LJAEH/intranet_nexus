package com.ln.intranet.dashBoard.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ln.intranet.dashBoard.model.service.DashBoardService;
import com.ln.intranet.dashBoard.model.vo.AttnDoughnut;
import com.ln.intranet.dashBoard.model.vo.DeptTeam;
import com.ln.intranet.dashBoard.model.vo.HumanResourceManage;
import com.ln.intranet.dashBoard.model.vo.ProjectPolar;
import com.ln.intranet.dashBoard.model.vo.ProjectTotal;
import com.ln.intranet.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/dashBoard")
@Slf4j
public class DashBoardController {
	
	@Autowired
	DashBoardService service;

	// 대쉬보드 진입 컨트롤러 
	// ++ 프로그래스 동작 정보 
	@GetMapping("/dashBoardMain")
	public String dashBoardViewer(
			@ModelAttribute("loginMember") Member loginMember,
			Model model,
			RedirectAttributes ra
			) {
		
		int deptNo = loginMember.getDeptNo();
		
		List<HumanResourceManage> hrList = new ArrayList<HumanResourceManage>();
		
		hrList = service.hrList(deptNo);
		
		for(int i = 0; i < hrList.size(); i++) {
			HumanResourceManage hr = hrList.get(i);
			
			if(hr.getWorkMin() > 60) {
				int plusTime = hr.getWorkMin()/60;
				int newMin = hr.getWorkMin()%60;
				
				hr.setWorkMin(newMin);
				hr.setWorkTime(hr.getWorkTime()+plusTime);
		
			}
			
			if(hr.getExMin() > 60) {
				int plusTime = hr.getExMin()/60;
				int newMin = hr.getExMin()%60;
				
				hr.setExMin(newMin);
				hr.setExTime(hr.getExTime()+plusTime);
		
			}
		}
		
		List<DeptTeam> dtList = new ArrayList<DeptTeam>();
		dtList = service.dtList(deptNo);
		
		// 프로젝트 총 리스트
		List<ProjectTotal> prList = new ArrayList<ProjectTotal>();
		prList = service.prList(deptNo);
		
		
		model.addAttribute("hrList", hrList);
		model.addAttribute("dtList", dtList);
		model.addAttribute("prList", prList);
		
		String message = null;
		
		if(hrList.size()==0) {
			message = "대시보드 내용이 없습니다!";
			ra.addFlashAttribute("message", message);
			return "redirect:/main";
		} else {			
			return "/dashBoard/dashBoard";
		}
			
	}
	
	// 부서별 근태 도넛 리스트
	@ResponseBody
	@GetMapping("attnDoughnut")
	public List<AttnDoughnut> doughnutList(
			@ModelAttribute("loginMember") Member loginMember
			){
	
		List<AttnDoughnut> list = new ArrayList<AttnDoughnut>();
		

		list = service.doughnutList(loginMember.getDeptNo());
		
		return list;
	}
	
	@ResponseBody
	@GetMapping("projectPolar")
	public List<ProjectPolar> polarList(
			@ModelAttribute("loginMember") Member loginMember
			){
		
		List<ProjectPolar> list = new ArrayList<ProjectPolar>();
		
		list = service.polarList(loginMember.getDeptNo());
		
		return list;
	}
	
	
	@GetMapping("/humanResourceManage")
	public String humanResourceManage(
			@ModelAttribute("loginMember") Member loginMember,
			@RequestParam(value = "currentYear", required = false, defaultValue = "0") int currentYear,
			@RequestParam(value = "currentMonth", required = false, defaultValue = "0") int currentMonth,
			Model model
			) {
		
		
	 LocalDate now;
	    
	    // nowMonth와 nowYear가 둘 다 디폴트 값인 경우
	    if (currentMonth == 0 && currentYear == 0) {
	        now = LocalDate.now();
	    } else {
	        // nowMonth와 nowYear가 파라미터로 받은 값인 경우
	        now = LocalDate.of(currentYear, currentMonth, 1);
	    }
	    
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM");
	    String searchDate = now.format(formatter);
		
		int deptNo = loginMember.getDeptNo();
		
		List<HumanResourceManage> hrList = new ArrayList<HumanResourceManage>();
		
		hrList = service.hrTotal(deptNo,searchDate);
		
		for(int i = 0; i < hrList.size(); i++) {
			HumanResourceManage hr = hrList.get(i);
			
			if(hr.getWorkMin() > 60) {
				int plusTime = hr.getWorkMin()/60;
				int newMin = hr.getWorkMin()%60;
				
				hr.setWorkMin(newMin);
				hr.setWorkTime(hr.getWorkTime()+plusTime);
			}
			
			if(hr.getExMin() > 60) {
				int plusTime = hr.getExMin()/60;
				int newMin = hr.getExMin()%60;
				
				hr.setExMin(newMin);
				hr.setExTime(hr.getExTime()+plusTime);
			}
		}
				
		model.addAttribute("hrList", hrList);
		
		return "/dashBoard/humanResource-manage";
	}
	

	@GetMapping("/humanResourceManageC/{targetDate}")
	public String humanResourceManageControll(
			@ModelAttribute("loginMember") Member loginMember,
			@PathVariable("targetDate") String targetDate,
			Model model
			) {

	    
	    int deptNo = loginMember.getDeptNo();
	    
	    // 월 값 조정
	    String[] dateParts = targetDate.split("-");
	    int year = Integer.parseInt(dateParts[0]);
	    int month = Integer.parseInt(dateParts[1]);

	    String searchDate = String.format("%02d/%02d", year, month);
	    
		List<HumanResourceManage> hrList = new ArrayList<HumanResourceManage>();
		
		hrList = service.hrTotal(deptNo,searchDate);
		
		for(int i = 0; i < hrList.size(); i++) {
			HumanResourceManage hr = hrList.get(i);
			
			if(hr.getWorkMin() > 60) {
				int plusTime = hr.getWorkMin()/60;
				int newMin = hr.getWorkMin()%60;
				
				hr.setWorkMin(newMin);
				hr.setWorkTime(hr.getWorkTime()+plusTime);
			}
			
			if(hr.getExMin() > 60) {
				int plusTime = hr.getExMin()/60;
				int newMin = hr.getExMin()%60;
				
				hr.setExMin(newMin);
				hr.setExTime(hr.getExTime()+plusTime);
			}
		}
				
		model.addAttribute("hrList", hrList);
		
		return "/dashBoard/humanResource-manage";
	}
	
	@PostMapping("excelDownload")
	public void hrExelCreate(
			@RequestParam("excelDate") String excelDate,
			@ModelAttribute("loginMember") Member loginMember,
			HttpServletResponse response
			) {
		
		// 엑셀화 할 인적자원 데이터 리스트
		
		int deptNo = loginMember.getDeptNo();
		
		List<HumanResourceManage> hrList = new ArrayList<HumanResourceManage>();
		
		hrList = service.hrTotal(deptNo,excelDate);
		
		for(int i = 0; i < hrList.size(); i++) {
			HumanResourceManage hr = hrList.get(i);
			
			if(hr.getWorkMin() > 60) {
				int plusTime = hr.getWorkMin()/60;
				int newMin = hr.getWorkMin()%60;
				
				hr.setWorkMin(newMin);
				hr.setWorkTime(hr.getWorkTime()+plusTime);
			}
			
			if(hr.getExMin() > 60) {
				int plusTime = hr.getExMin()/60;
				int newMin = hr.getExMin()%60;
				
				hr.setExMin(newMin);
				hr.setExTime(hr.getExTime()+plusTime);
			}
		}
		
		Workbook wb = new HSSFWorkbook(); // 엑셀파일 객체 생성
		Sheet sheet = wb.createSheet("테스트 시트"); //시트 생성 ( 첫번째 시트이며, 시트명은 테스트 시트 )
		
		sheet.setColumnWidth(2, 3000); // 부서
		
		sheet.setColumnWidth(5, 4000); // 근로시간
		sheet.setColumnWidth(6, 4000); // 연장근로시간
		
		CellStyle style = wb.createCellStyle(); // 셀 스타일 생성
		Font font = wb.createFont(); // 폰트 스타일 생성
		
        font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 글자 진하게
        font.setFontHeight((short)(16*18)); // 글자 크기
        font.setFontName("맑은 고딕"); // 글씨체
        // font.setColor(IndexedColors.WHITE.getIndex());
        
        // 자바의 배열처럼 첫번째 인덱스가 0 부터 시작한다.  첫번째는 0 , 두번째는 1 , 세번째는 2
        Row titleRow = sheet.createRow(0); // 타이틀행을 생성한다. 첫번째줄이기때문에 createRow(0)
        int titleColNum = 1; // 첫번째 열이기 때문에 0 
		Cell titleCell = titleRow.createCell(titleColNum); // 첫번째행의 첫번째열을 지정한다. 
		titleCell.setCellValue(hrList.get(1).getDeptName()+" 근태관리용 EXCEL"); // setCellValue 셀에 값넣기.
		titleRow.setHeight((short)420); // Row에서 setHeight를 하면 행 높이가 조정된다. 
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,9)); // 셀 병합  첫번째줄~아홉번째 열까지 병합 
		sheet.addMergedRegion(new CellRangeAddress(1,1,0,2));
		// new CellRangeAddress(시작 row, 끝 row, 시작 col, 끝 col) 
        
		style.setWrapText(true); //문자열을 입력할때 \n 같은 개행을 인식해준다.
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 수직 가운데 정렬
		style.setAlignment(CellStyle.ALIGN_CENTER); // 수평 가운데 정렬
		style.setFont(font); // 스타일에 font 스타일 적용하기
		//style.setFillForegroundColor(IndexedColors.PINK.getIndex());
		titleCell.setCellStyle(style); // 정리한 스타일들을 titleCell에 적용해주자 !
		
		//입력받은 날짜 출력하기
		Row dayRow = sheet.createRow(1);
		dayRow.setHeight((short)300); 
		int dayCol = 0;
		Cell dayCell = dayRow.createCell(dayCol); // 두번째줄의 첫번째열을 셀로 지정. 즉 두번째줄 첫째칸
		dayCell.setCellValue("조회한 달 : " + 20 + excelDate); // 두번째 행은 입력받은 날짜를 출력
		
		
		Font headFont = wb.createFont();
		headFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		CellStyle headStyle = wb.createCellStyle(); // 셀 스타일 생성
		headStyle.setWrapText(true); //문자열을 입력할때 \n 같은 개행을 인식해준다.
		headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 수직 가운데 정렬
		headStyle.setAlignment(CellStyle.ALIGN_CENTER); // 수평 가운데 정렬
		//headStyle.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		headStyle.setFont(headFont); // 스타일에 font 스타일 적용하기
		
		dayCell.setCellStyle(headStyle);
		
		//헤더 만들기
		Row headerRow = sheet.createRow(3); // 네번째줄 생성
		headerRow.setHeight((short)400); 
		
		Cell headerCell = null;
		headerCell = headerRow.createCell(0);
		headerCell.setCellValue("이름");
		headerCell.setCellStyle(headStyle);
		
		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("직급");
		headerCell.setCellStyle(headStyle);
		
		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("부서");
		headerCell.setCellStyle(headStyle);
		
		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("팀명");
		headerCell.setCellStyle(headStyle);
		
		headerCell = headerRow.createCell(4);
		headerCell.setCellValue("근로일");
		headerCell.setCellStyle(headStyle);
		
		headerCell = headerRow.createCell(5);
		headerCell.setCellValue("근무시간");
		headerCell.setCellStyle(headStyle);
		
		headerCell = headerRow.createCell(6);
		headerCell.setCellValue("연장근로시간");
		headerCell.setCellStyle(headStyle); // 헤드스타일 적용
		
		
		CellStyle dataStyle = wb.createCellStyle(); // 데이터스타일은 테두리를 만들어보자
		
		dataStyle.setWrapText(true); //문자열을 입력할때 \n 같은 개행을 인식해준다.
		dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 수직 가운데 정렬
		dataStyle.setAlignment(CellStyle.ALIGN_CENTER); // 수평 가운데 정렬

		int rowNum = 4; // 네번째줄이 헤더니까 그 밑에서부터 데이터 삽입
		// int cellNum = 0;
		Row dataRow = null; // for문을 돌려주기위해.
		Cell dataCell = null;
		
		for(int i = 0; i<hrList.size(); i++) {
			dataRow = sheet.createRow(rowNum++); // for문 돌면서 행 1줄씩 추가
			dataRow.setHeight((short)400); 
			
			dataCell = dataRow.createCell(0);
			dataCell.setCellValue(hrList.get(i).getMemName()); 
			dataCell.setCellStyle(dataStyle);
			
			dataCell = dataRow.createCell(1);
			dataCell.setCellValue(hrList.get(i).getJobName()); 
			dataCell.setCellStyle(dataStyle);
			
			dataCell = dataRow.createCell(2);
			dataCell.setCellValue(hrList.get(i).getDeptName()); 
			dataCell.setCellStyle(dataStyle);
			
			dataCell = dataRow.createCell(3);
			dataCell.setCellValue(hrList.get(i).getTeamName()); 
			dataCell.setCellStyle(dataStyle);
			
			dataCell = dataRow.createCell(4);
			dataCell.setCellValue(hrList.get(i).getWorkDay()+"일"); 
			dataCell.setCellStyle(dataStyle);
			
			dataCell = dataRow.createCell(5);
			dataCell.setCellValue(hrList.get(i).getWorkTime()+"시간"+hrList.get(i).getWorkMin()+"분"); 
			dataCell.setCellStyle(dataStyle);
			
			dataCell = dataRow.createCell(6);
			dataCell.setCellValue(hrList.get(i).getExTime()+"시간"+hrList.get(i).getExMin()+"분"); 
			dataCell.setCellStyle(dataStyle); // 데이터스타일 적용
		}

		 /* 엑셀 파일 생성 */
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=humanResource.xls");
	    try {
			wb.write(response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	    

 
		
	}
}
