package com.ln.intranet.attendance.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ln.intranet.attendance.model.dao.AttendanceDAO;
import com.ln.intranet.attendance.model.vo.Attendance;

@Service
public class AttendanceServiceImp implements AttendanceService {

	@Autowired
	AttendanceDAO dao;
	
	// 근태 리스트 조회
	@Override
	public List<Attendance> selectAttendanceList(Map<String, Object> map) {
		
		return dao.selectAttendanceList(map);
	}
	
	// 출근 시간 입력
	@Override
	public int attnStrartHours(Map<String, Object> map) {
		
		int startHours = Integer.parseInt(map.get("hours").toString());
		int startMinutes = Integer.parseInt(map.get("minutes").toString());
		
		if(startHours > 9) {
			map.put("attdTypeNo", 2);
		} else if(startHours == 9 && startMinutes > 0){
			map.put("attdTypeNo", 2);
		} else {
			map.put("attdTypeNo", 1);
		}
		
		String attnStartH = "";
		if(startHours < 10) {
			attnStartH = "0" + startHours;
		} else {
			attnStartH = "" + startHours;
		}
		
		String attnStartM = "";
		if(startMinutes < 10) {
			attnStartM = "0" + startMinutes;
		} else {
			attnStartM = "" + startMinutes;
		}
		
		String attnStart = attnStartH + " : " + attnStartM;
		
		map.put("attnStart", attnStart);
		
		return dao.attnStrartHours(map);
	}

	// 퇴근 시간 입력
	@Override
	public int attnEndHours(Map<String, Object> map) {
		
		// 퇴근 시간 조정하여 넣기
		int endHours = Integer.parseInt(map.get("hours").toString());
		int endMinutes = Integer.parseInt(map.get("minutes").toString());
		
		String attnEndH = "";
		if(endHours < 10) {
			attnEndH = "0" + endHours;
		} else {
			attnEndH = "" + endHours;
		}
		
		String attnEndM = "";
		if(endMinutes < 10) {
			attnEndM = "0" + endMinutes;
		} else {
			attnEndM = "" + endMinutes;
		}
		
		String attnEnd = attnEndH + " : " + attnEndM;
		
		map.put("attnEnd", attnEnd);
		
		
		// 시작 시간 불러오기
		String startTime = dao.selectStartTime(map);
		
		// 근무 시간 계산하기
		int workingHour = 0;
		int workingMinute = 0;
		
		int startHour = Integer.parseInt(startTime.split(" : ")[0]);
		int startMinute = Integer.parseInt(startTime.split(" : ")[1]);
		
		// 지각 아닌 경우
		if(startHour < 9 || (startHour == 9 && startMinute == 0)) {
			System.out.println("startHour : " + startHour);
			System.out.println("startMinute : " + startMinute);
			System.out.println("endHours : " + endHours);
			System.out.println("endMinutes : " + endMinutes);
			
			if(endHours < 13) {
				workingHour = endHours - startHour;
				workingMinute = endMinutes - 0;
			} else if(endHours == 13) {
				workingHour = 13 - startHour;
				workingMinute = 0;
			} else {
				workingHour = endHours - startHour - 1;
				workingMinute = endMinutes - 0;
			}
			
		// 지각인 경우
		} else { 
			// 지각인데 점심 시간 이전 출근
			if(startHour < 13) {
				if(endHours < 13 ) {
					workingHour = endHours - startHour;
					workingMinute = endMinutes - startMinute;
				} else if(endHours == 13) {
					workingHour = 13 - startHour;
					workingMinute = 0 - startMinute;
				} else {
					workingHour = endHours - startHour - 1;
					workingMinute = endMinutes - startMinute;
				}
					
			// 지각인데 점심 시간 이후 출근
			} else {
				// 점심시간에 출근
				if(startHour == 13) {
					workingHour = endHours - 14;
					workingMinute = endMinutes - 0;
					
				// 점심시간 끝나고 출근
				} else
				workingHour = endHours - startHour;
				workingMinute = endMinutes - startMinute;
			}
			
			if(workingMinute < 0) {
				workingMinute = 60 + workingMinute;
				workingHour--;
			}
		}
		
		String workingHours = workingHour + " 시간 " + workingMinute + " 분";
		
		map.put("workingHours", workingHours);
		
		// 연장 시간 계산하기
		int tempEx = workingHour - 8;
		
		String extendedWH = "";
		
		if(tempEx >= 0) {
			extendedWH = tempEx + " 시간 " + workingMinute + " 분";
		} else {
			extendedWH = "0 시간 0 분";
		}
		
		map.put("extendedWH", extendedWH);
		
		return dao.attnEndHours(map);
	}

	// DB에서 오늘 날짜 조회
	@Override
	public Attendance selectOne(Map<String, Object> map) {
		return dao.selectOne(map);
	}
	
	
	
	 
}
