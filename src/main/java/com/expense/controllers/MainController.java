package com.expense.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.expense.dao.ExpenseDAOImpl;
import com.expense.objects.Expense;



@Controller
public class MainController {
	
	
	private String LOGINPAGE ="login";
	private String USERPAGE = "user";
	private String ADMINPAGE ="admin";
	private String ACCESSDENIEDPAGE = "accessDenied";
	
	@Autowired
	private ExpenseDAOImpl expenseDAOImpl;

	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="error", required=false) String error, HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		
//		model.addObject("test", "test ok!");

		
		
		
		if(error != null)
			model.addObject("error", "Wrong login or password");
		
		model.setViewName(LOGINPAGE);
		return model;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/user")
	public ModelAndView userPage(){
		ModelAndView model = new ModelAndView(USERPAGE);
		
		List<Expense> list = expenseDAOImpl.getExpenseList();
		for(Expense exp1 : list){
			System.out.println(exp1.getExpCategory().getName()+"."+exp1.getExpenseUser().getName()+"."+exp1.getAmount()+"."+exp1.getDate()+"."+exp1.getTitle());
		}
		
		model.addObject("expenselist", list);

		return model;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/admin")
	public ModelAndView adminPage(){
		ModelAndView model = new ModelAndView(ADMINPAGE);
		return model;
	}



}
