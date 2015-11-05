package com.expense.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.expense.objects.ExpenseUser;

public interface ExpenseUserDAO {
	
	public List<ExpenseUser> getExpenseUserList();
	
	public List<ExpenseUser> getExpenseUserListWithoutThisUser(UserDetails user);
	
	public int getUserId(String username);

}
