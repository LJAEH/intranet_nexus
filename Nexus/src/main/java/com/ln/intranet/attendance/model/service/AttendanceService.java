package com.ln.intranet.attendance.model.service;

import java.util.List;
import java.util.Map;

import com.ln.intranet.attendance.model.vo.Attendance;

public interface AttendanceService {

	// 근태 기록 조회
	List<Attendance> selectAttendanceList(Map<String, Object> map);

	// 출근 시간 입력
	int attnStrartHours(Map<String, Object> map);

	// 퇴근 시간 입력
	int attnEndHours(Map<String, Object> map);

	// DB에서 오늘 날짜 값 조회
	Attendance selectOne(Map<String, Object> map);

}
