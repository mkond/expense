package com.expense.dao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	
	private String LOGINPAGE ="login";
	private String USERPAGE = "user";
	private String ADMINPAGE ="admin";
	private String ACCESSDENIEDPAGE = "accessDenied";
	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="error", required=false) String error, HttpSession session, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if(error != null)
			model.addObject("error", "Wrong login or password");
		
		model.setViewName(LOGINPAGE);
		return model;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/user")
	public ModelAndView userPage(){
		ModelAndView model = new ModelAndView(USERPAGE);
		return model;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/admin")
	public ModelAndView adminPage(){
		ModelAndView model = new ModelAndView(ADMINPAGE);
		return model;
	}



}
