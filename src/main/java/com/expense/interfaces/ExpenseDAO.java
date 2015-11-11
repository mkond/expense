package com.expense.interfaces;

import java.util.List;


import com.expense.objects.Expense;

public interface ExpenseDAO {
	
	public List<Expense> getExpenseList();
	
	public void insertExpenseToExpenseList(int categoryID, int userID, int amount, String date, String title);

}
