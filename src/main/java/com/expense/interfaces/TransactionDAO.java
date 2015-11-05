package com.expense.interfaces;

public interface TransactionDAO {
	
	public void inserTransaction(int userIDWhoPay, int userIDForExpense, int amount);

}
