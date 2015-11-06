package com.expense.interfaces;

import java.util.List;

import com.expense.objects.UsersTransaction;

public interface TransactionDAO {
	
	public void insertTransaction(int userIDWhoPay, int userIDForExpense, int amount);
	
	public List<UsersTransaction> getUserListWhoNeedToPayMe(String userME);
	

	
	
	

}
