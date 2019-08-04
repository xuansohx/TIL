package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@RequestMapping("/useradd.mc")
	public ModelAndView uadd(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/add");
		
		session.setAttribute("Hi", "Hiii");
		return mv;
	}
	
	@RequestMapping("/userlist.mc")
	public ModelAndView ulist() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/list");
		return mv;
	}
	
}
