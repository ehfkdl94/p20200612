package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//public class HomeController extends Controller{
public class HomeController {
	@RequestMapping(value="/")
	public String home(HttpSession httpSession) {
//		String userid
//			= (String)httpSession.getAttribute("SESSION_ID");
		return "index";
	}
	
	@RequestMapping(value="/test1")
	public String test1() {
		return "";
	}
}
