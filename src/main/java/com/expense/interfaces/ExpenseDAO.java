package com.expense.interfaces;

import java.util.List;

import com.expense.objects.Expense;

public interface ExpenseDAO {
	
	public List<Expense> getExpenseList();

}
