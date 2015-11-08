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

import com.expense.objects.UsersTransaction;



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
		
		if(error != null)
			model.addObject("error", "Wrong login or password");
		
		model.setViewName(LOGINPAGE);
		

		
		return model;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/user")
	public ModelAndView userPage(){
			
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String user = userDetails.getUsername().toString();
		
		List<Expense> list = expenseDAOImpl.getExpenseList();
		List<UsersTransaction> usersTransactionsToPayMe = transactionDAOImpl.getUserListWhoNeedToPayMe(user);	
		List<UsersTransaction> usersTransactionsINeedPay = transactionDAOImpl.getUsersListWhomINeedToPay(user);
		List<ExpenseUser> expenseUserListWithoutThis = expenseUserDAOImpl.getExpenseUserListWithoutThisUser(userDetails);
		
		
		ModelAndView model = new ModelAndView(USERPAGE);
		model.addObject("usersTransactionsToPayMe" ,usersTransactionsToPayMe);		
		model.addObject("usersTransactionsINeedPay" ,usersTransactionsINeedPay);	
		model.addObject("expenselist", list);
		model.addObject("expenseUserListWithoutThis", expenseUserListWithoutThis);
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
		int countUsersToPay = checkedUserIds.length+1;
		String listUsersForTitle = "";
		
		
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String user = userDetails.getUsername();	
		int userID = expenseUserDAOImpl.getUserId(user);
		
		String[] parts = date.split("/");
		String dateToDB = String.format("%s-%s-%s", parts[2],parts[0],parts[1]);

		
		
		if(sum % countUsersToPay ==0){
			sumToPay=sum/countUsersToPay;
		}else{
			sumToPay=(sum+(countUsersToPay-1))/countUsersToPay;
		}
		
		for(int i=0;i<checkedUserIds.length;i++){
			transactionDAOImpl.insertTransaction(userID, Integer.parseInt(checkedUserIds[i]), sumToPay);
			ExpenseUser expUser = expenseUserDAOImpl.getUserById(Integer.parseInt(checkedUserIds[i]));
			if(i==0){
				listUsersForTitle = String.format("%s %s,",expUser.getFirstname(), expUser.getLastname());
				continue;
			}
			if(i==checkedUserIds.length-1)
				listUsersForTitle = String.format("%s %s %s", listUsersForTitle, expUser.getFirstname(), expUser.getLastname());
			else
				listUsersForTitle = String.format("%s %s %s,", listUsersForTitle,expUser.getFirstname(), expUser.getLastname());
				
		}	
		title = String.format("%s | %s", listUsersForTitle, title);
		System.out.println("title - "+title);
		
		expenseDAOImpl.insertExpenseToExpenseList(categoryId, userID, sum, dateToDB, title);		
		
		return "redirect:/user";
	}
	
	@RequestMapping(value="/user/moneyreturn", method=RequestMethod.POST)
	public String moneyReturn(@RequestParam("returnUser") int FromID, @RequestParam("Sumaaaaa") int sum){
		UserDetails userDetails =
				 (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String user = userDetails.getUsername();	
		int ToID = expenseUserDAOImpl.getUserId(user);
		transactionDAOImpl.insertTransaction(FromID, ToID, sum);
		return "redirect:/user";
	}



}
