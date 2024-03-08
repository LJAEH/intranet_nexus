package com.ln.intranet.member.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ln.intranet.common.model.vo.Pagination;
import com.ln.intranet.member.model.dao.MemberDAO;
import com.ln.intranet.member.model.vo.Member;
import com.ln.intranet.notice.model.vo.Notice;

@Service
public class MemberServiceImpl implements MemberService {
	


	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	// 로그인서비스
	@Override
	public Member login(Member inputMember) {
		
		
		
		Member loginMember = dao.login(inputMember);
		
		//logger.info("아이디:" + loginMember.getMemId());
		
		if(loginMember != null) { 	
			
			if( bcrypt.matches(inputMember.getMemPw(),loginMember.getMemPw())) {
				loginMember.setMemPw(null); 
			} else { 
				loginMember = null;
			}	
		}
		
		return loginMember;
	}

	// 채팅 member List 조회
	@Override
	public List<Member> selectChatMemberList() {
		
		return dao.selectChatMemberList();
	}

	// 비밀번호 변경 서비스 구현
	@Override
	public int changePw(Map<String, Object> paramMap) {
		
		String encPw = dao.selectEncPw((int)paramMap.get("memNo"));
		
		if(bcrypt.matches((String)paramMap.get("currentPw"), encPw)) {
			
			paramMap.put("newPw", bcrypt.encode((String)paramMap.get("newPw")));
			
			return dao.changePw(paramMap);
		}
		return 0;
	}

	
	// 직원 추가 서비스 구현
	@Override
	public String signUp(Member member) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int result = dao.signUp(member);
		
		String message = null;
		String memId = "NEXUS" + member.getMemNo();
		
		if(result > 0) {
			message = "직원이 추가 되었습니다.,," + memId;
		} else {
			message = "직원 추가 실패";
		}
		
		return message;
	}

	// 직원 검색 서비스 구현
	@Override
	public Member searchMember(int memNo) {
		
		return dao.searchMember(memNo);
	}

	// 직원 정보 수정 서비스 구현
	@Override
	public int update(Map<String, Object> paramMap) {

		return dao.update(paramMap);
	}

	// 직원 퇴사 처리 서비스 구현
	@Override
	public int delete(int memNo) {

		return dao.delete(memNo);
	}

	// 퇴사할 직원 조회 서비스 구현
	@Override
	public Member deleteSearch(int memNo) {

		return dao.deleteSearch(memNo);
	}

	// 직원 전체 정보 조회 서비스 구현
	@Override
	public List<Member> selectAll() {

		List<Member> memList = dao.selectAll();
		
		return memList;
	}

	// 검색한 직원 조회
	@Override
	public List<Member> selectOne(Map<String, Object> paramMap) {

		return dao.selectOne(paramMap);
	}

   
	
	

	

	
	
	
	
	
	
	
	

}
