package com.ln.intranet.member.model.service;

import java.util.List;
import java.util.Map;

import com.ln.intranet.member.model.vo.Member;

// 로그인서비스
public interface MemberService {

	Member login(Member inputMember);
	
	List<Member> selectChatMemberList();

	int changePw(Map<String, Object> paramMap);

	/** 직원 정보 추가
	 * @param member
	 * @return result
	 */
	String signUp(Member member);

	/** 직원 검색
	 * @param memNo
	 * @return searchedMem
	 */
	Member searchMember(int memNo);

	/** 직원 정보 수정
	 * @param paramMap
	 * @return result
	 */
	int update(Map<String, Object> paramMap);

	/** 직원 퇴사 처리
	 * @param memNo
	 * @return result
	 */
	int delete(int memNo);

	/** 퇴사할 직원 정보 조회
	 * @param memNo
	 * @return memSearch
	 */
	Member deleteSearch(int memNo);

	/** 전체 직원 정보 조회
	 * @return memList
	 */
	List<Member> selectAll();

	/** 검색한 직원 조회
	 * @param paramMap
	 * @return selectMem
	 */
	List<Member> selectOne(Map<String, Object> paramMap);

	

	



	

}
