package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.BoardDAO;
import com.example.vo.BoardVO;


@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardDAO bDAO = null;
	
	//localhost:8082/board/getimg?no=8
	@RequestMapping(value="getimg")
	public ResponseEntity<byte[]> getimg(@RequestParam("no")int no, Model model){
		BoardVO obj = bDAO.selectBoardImg(no);
		try {
			if(obj.getBrd_img().length>0) { //이미지가 있음
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.IMAGE_JPEG);
				ResponseEntity<byte[]> ret = new ResponseEntity<byte[]>(obj.getBrd_img(), header, HttpStatus.OK);
				return ret;
			}
			return null;
		}catch(Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value="/insertbatch")
	public String iteminsert(HttpSession httpSession, Model model) {
		String userid = (String)httpSession.getAttribute("SESSION_ID");
		if(userid == null) { //아이디값이 없다면 로그인 되지 않은 상태
			return "redirect:/member/login"; //로그인 페이지로 이동
		}
		model.addAttribute("userid", userid);
		return "/board/batchinsert";
	}
	//localhost:8082/board/insertbatch
	@RequestMapping(value="/insertbatch", method = RequestMethod.POST)
	public String insertbatch(
			@RequestParam("title[]") String[] title,
			@RequestParam("content[]") String[] content,
			@RequestParam("id[]") String[] id) {
		
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		for(int i=0;i<title.length;i++) {
			BoardVO obj = new BoardVO();
			obj.setBrd_title(title[i]);
			obj.setBrd_content(content[i]);
			obj.setBrd_id(id[i]);
			
			list.add(obj);
		}
		bDAO.insertBatch(list);
		return "redirect:/board/list";
	}
	@RequestMapping(value="/insert", method = RequestMethod.GET)
	public String insertBoard(HttpSession httpSession, Model model) {
		//세션에서 로그인한 사용자의 아이디값을 가져옴
		String userid = (String)httpSession.getAttribute("SESSION_ID");
		if(userid == null) { //아이디값이 없다면 로그인 되지 않은 상태
			return "redirect:/member/login"; //로그인 페이지로 이동
		}
		//그렇지 않다면 게시판 글쓰기 화면 표시
		model.addAttribute("userid", userid);
		return "/board/insert";
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public String insertBoardPost(@ModelAttribute BoardVO obj, 
			@RequestParam MultipartFile[] imgs) throws IOException {
		if(imgs != null && imgs.length >0) { //이미지가 첨부되었다면
			for(MultipartFile one : imgs) {
				obj.setBrd_img(one.getBytes());
			}
		}
		//DAO로 obj값 전달하기
		int ret = bDAO.insertBoard(obj);
		if (ret > 0) {
			return "redirect:/";
		}
		return "redirect:/board/insert";
	}
	//127.0.0.1:8082/board/list
	//127.0.0.1:8082/board/list?page=55
	@RequestMapping(value="/list")
	public String boardlist(Model model, HttpSession httpSession, @RequestParam(value="page", defaultValue="1", required=false) int page) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		
		map.put("start", (page*10)-9);
		map.put("end", page*10);
		httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 1);
		//목록
		List<BoardVO> list = bDAO.selectBoard(map);
		
		//개수
		int cnt = bDAO.countBoard();
		model.addAttribute("list", list);
		model.addAttribute("cnt", (int)Math.ceil(cnt/10.0));
		
		return "/board/boardlist";
	}
	
	@RequestMapping(value="/content", method=RequestMethod.GET)
	public String boardcontent(Model model, HttpSession httpSession, @RequestParam(value="no", defaultValue="0", required=false) int no) {
		int chk = (int)httpSession.getAttribute("SESSION_BOARD_HIT_CHECK");
		if(chk==1) {
			bDAO.updateHit(no);
			httpSession.setAttribute("SESSION_BOARD_HIT_CHECK", 0);
		}
		
		if(no==0) {
			return "redirect:/board/boardlist";
		}	
		BoardVO obj = bDAO.selectBoardOne(no);
		model.addAttribute("obj", obj);
		return "/board/content";
	}
}
