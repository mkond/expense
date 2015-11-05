package com.expense.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.expense.daoimpl.ExpCategoryDAOImpl;
import com.expense.daoimpl.ExpenseUserDAOImpl;
import com.expense.daoimpl.TransactionDAOImpl;
import com.expense.objects.ExpCategory;
import com.expense.objects.Expense;
import com.expense.objects.ExpenseUser;



@Controller
public class MainController {
	
	
	private String LOGINPAGE ="login";
	private String USERPAGE = "user";
	private String ADMINPAGE ="admin";
	private String ACCESSDENIEDPAGE = "accessDenied";
	private String NEWEXPENSEPAGE = "newExpense";
	
	@Autowired
	private ExpenseDAOImpl expenseDAOImpl;
	
	@Autowired
	private ExpCategoryDAOImpl expCategoryDAOImpl;
	
	@Autowired
	private ExpenseUserDAOImpl expenseUserDAOImpl;
	
	@Autowired
	private TransactionDAOImpl transactionDAOImpl;

	
	@RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="error", required=false) String error, HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		System.out.println(dateFormat1.format(date1)); 
		
		model.addObject("test", "test ok!");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//date
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		
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
		model.addObject("test", "test ok!");
		model.addObject("expenselist", list);

		return model;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/admin")
	public ModelAndView adminPage(){
		ModelAndView model = new ModelAndView(ADMINPAGE);
		return model;
	}
	
	@RequestMapping(value="/user/addexpense", method=RequestMethod.GET)
	public ModelAndView addNewExpense(){
		ModelAndView model = new ModelAndView();
		List<ExpCategory> expCategotyList = expCategoryDAOImpl.getExpCategoryList();
		model.addObject("expCategotyList",expCategotyList);
		model.setViewName(NEWEXPENSEPAGE);
		
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ExpenseUser> expenseUserListWithoutThis = expenseUserDAOImpl.getExpenseUserListWithoutThisUser(userDetails);
		model.addObject("expenseUserListWithoutThis", expenseUserListWithoutThis);
		
		
		return model;
	}
	
	@RequestMapping(value="/user/addexpense/add", method=RequestMethod.POST)
	public String addExpense(		HttpServletRequest request,  
										@RequestParam(value="category") int categoryId,
										@RequestParam(value="userId", required = false)String[] checkedUserIds,
										@RequestParam("sumExp") int sum,
										@RequestParam("date") String date,
										@RequestParam("title") String title){
		

		int sumToPay = 0;
		
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String user = userDetails.getUsername();	
		int userID = expenseUserDAOImpl.getUserId(user);
		
		String[] parts = date.split("/");
		String dateToDB = String.format("%s-%s-%s", parts[2],parts[0],parts[1]);

		int countUsersToPay = checkedUserIds.length+1;
		
		if(sum % countUsersToPay ==0){
			sumToPay=sum/countUsersToPay;
		}else{
			sumToPay=(sum+(countUsersToPay-1))/countUsersToPay;
		}
		expenseDAOImpl.insertExpenseToExpenseList(categoryId, userID, sum, dateToDB, title);		
		
		for(int i=0;i<checkedUserIds.length;i++){
			transactionDAOImpl.inserTransaction(userID, Integer.parseInt(checkedUserIds[i]), sumToPay);
		}			
		return "redirect:/user";
	}



}
