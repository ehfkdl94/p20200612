package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.MemberDAO;
import com.example.vo.MemberVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberDAO mDAO = null; // MemberDAO mDAO = new MemberDAO();2줄이 이거랑 같은 뜻
	
//	crtl + ? = 주석문
//	crtl + shift + f = 자동정렬
	@RequestMapping(value = "/memberlist")
	public String memberlist(Model model) {
		List<MemberVO> list = mDAO.selectMemberList();
		
		model.addAttribute("name", "가나다");
		model.addAttribute("list", list); //hashmap<key, value> Controller -> View로 갑 전달
		return "memberlist";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession httpSession) {
//		httpSession.setAttribute("SESSION_ID", null);
		httpSession.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginpost(@ModelAttribute MemberVO obj, HttpSession httpSession) {
		//DAO로 전달
		MemberVO obj1 = mDAO.selectMemberLogin(obj);
		if(obj1 != null) { //로그인 성공
			httpSession.setAttribute("SESSION_ID", obj.getUserid());
			return "redirect:/";
		}
		
		//로그인 실패 /member/login GET방식으로 전송
		return "login";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinpost(@ModelAttribute MemberVO obj) {
		System.out.println(obj.toString());
		int ret = mDAO.insertMember(obj);
		if (ret > 0) {
			return "redirect:/";
		}
		return "redirect:/member/join";
	}

}
