package com.example.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.ItemDAO;
import com.example.vo.ItemVO;

@Controller
@RequestMapping(value="/item")
public class ItemController {
	@Autowired
	private ItemDAO iDAO = null;
	
	@RequestMapping(value="/itemlist")
	public String itemlist(Model model, HttpSession httpSession, @RequestParam(value="page", defaultValue="1", required=false) int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		map.put("start", (page*10)-9);
		map.put("end", page*10);
		httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 1);
		//목록
		List<ItemVO> list = iDAO.selectItem(map);
		
		//개수
		int cnt = iDAO.countBoard();
		model.addAttribute("list", list);
		model.addAttribute("cnt", ((cnt-1)/10)+1);
		
		return "/item/itemlist";
	}
	
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public String boardcontent(Model model, HttpSession httpSession, @RequestParam(value="no", defaultValue="0", required=false) int no) {
		int chk = (int)httpSession.getAttribute("SESSION_BOARD_HIT_CHECK");
		if(chk==1) {
			httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 0);
		}
		
		if(no==0) {
			return "redirect:/board/boardlist";
		}	
		ItemVO obj = iDAO.selectItemOne(no);
		model.addAttribute("obj", obj);
		return "/item/content";
	}
}
