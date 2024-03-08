	package com.ln.intranet.chat.controller;
	
	import java.util.ArrayList;
import java.util.HashMap;
	import java.util.List;
	import java.util.Map;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
	
	import com.google.gson.Gson;
	import com.ln.intranet.chat.model.service.ChatService;
	import com.ln.intranet.chat.model.vo.ChatRoom;
	import com.ln.intranet.chat.model.vo.ChatRoomJoin;
	import com.ln.intranet.chat.model.vo.Message;
	import com.ln.intranet.member.model.service.MemberService;
	import com.ln.intranet.member.model.vo.Member;
	
	import lombok.extern.slf4j.Slf4j;
	
	@SessionAttributes({"loginMember", "cmNo"})
	@Slf4j
	@Controller
	public class ChattingController {
	
		@Autowired
		private ChatService service;
		
		@Autowired
		private MemberService mService;
		
		
		
		
		/** loginMember js로 전달
		 * @param loginMember
		 * @return Json(loginMemberInfo)
		 */
		@ResponseBody
		@RequestMapping(value = "/loginMember", method = RequestMethod.POST)
		public String getLoginMember(@ModelAttribute("loginMember") Member loginMember) {
			
			Map<String, Object> loginMemberInfo = new HashMap<>();
			
			loginMemberInfo.put("memNo", loginMember.getMemNo());
			loginMemberInfo.put("memName", loginMember.getMemName());
			
			
			return new Gson().toJson(loginMemberInfo);
					
			}
		
	
	
		 
		/** 해당된 채팅방 리스트 조회
		 * @param loginMember
		 * @return Gson(RoomList)
		 */
		@ResponseBody
		@RequestMapping(value = "/chatRoomList", method = RequestMethod.POST)
		public String chatRoomList(@ModelAttribute("loginMember") Member loginMember
								
				) {
	
	
			int memNo = loginMember.getMemNo();
			String memName = loginMember.getMemName();
	
			Map<String, Object> map = new HashMap<>();
			

			map.put("memNo", memNo);
			map.put("memName", memName);
			
	
			List<ChatRoom> RoomList = service.selectChatRoomList(map);
	
			   for (ChatRoom chatRoom : RoomList) {
			        String createMemberName = chatRoom.getCreateMemberName();
			        if (loginMember.getMemName().equals(createMemberName)) {
			            chatRoom.setResult(1);
			        } else {
			            chatRoom.setResult(0);
			        }
			    }
			
	
			return new Gson().toJson(RoomList);
	
		}
		



	/** 채팅방 채팅 조회
	 * @param loginMember
	 * @param cmNo
	 * @return Gson(chatMessageList)
	 */
	@ResponseBody
	@RequestMapping(value = "/chatMessageList/{cmNo}", method = RequestMethod.GET)
	public String joinChatRoom(@PathVariable("cmNo") int cmNo, 
								Model model
			) {
		
		
		
		List<Message> chatMessageList = service.selectChatMessageList(cmNo);
		
		
		
		
		model.addAttribute("cmNo", cmNo);
		
		
		return new Gson().toJson(chatMessageList);	
	}
	

	/** 초대할 사원 목록 조회
	 * @return Gson(chatMember)
	 */ 
	@ResponseBody
	@RequestMapping(value = "/chatMemberList", method = RequestMethod.GET)
	public String ChatMemberList() {
		
	List<Member> chatMember = mService.selectChatMemberList();
	
		
		
		return new Gson().toJson(chatMember);
	}
	

	/** 사원 초대 & 방 생성
	 * @param loginMember
	 * @param paramMap
	 * @param join
	 * @param ra
	 * @return result
	 */
	@ResponseBody
	@RequestMapping(value = "/inviteMember", method = RequestMethod.POST)
	public int inviteMember(@ModelAttribute("loginMember") Member loginMember,
			@RequestParam("name") String name,
								ChatRoomJoin join,
								RedirectAttributes ra
			) {
		
		
		System.out.println(name);
		
		Map<String, Object> paramMap = new HashMap<>();
		int memNo = loginMember.getMemNo();
		String memberName = loginMember.getMemName();
		
		
		
		paramMap.put("inviteName", name);
		paramMap.put("createMemberName", memberName);

		
		join.setMemNo(memNo);
		
		
		int result = service.inviteMember(paramMap, join);
		
		String message = null;
		
		
		if(result > 0) {
			message = paramMap.get("memNo") + "님을 초대했습니다";
			ra.addFlashAttribute("message", message);
	
		}else {
			message = "[404 error] 일겁니다 아마 내잘못 아닙니다 님잘못임 나 진짜 아님 이거 안되면 버그임";
			ra.addFlashAttribute("message", message);
		}
		
		
		return result;
	}	 
	
	
	@ResponseBody
	@RequestMapping(value = "/exitChatRoom", method = RequestMethod.POST)
	public String exitChatRoom(int cmNo) {
		
		
		int response = service.exitChatRoom(cmNo);
		
		return new Gson().toJson(response);
		
	}


}
