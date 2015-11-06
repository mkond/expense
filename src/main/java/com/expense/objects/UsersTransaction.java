package com.expense.objects;

public class UsersTransaction {
	
	private ExpenseUser fromUser;
	private ExpenseUser toUser;
	private int amount;
	
	public ExpenseUser getFromUser() {
		return fromUser;
	}
	public void setFromUser(ExpenseUser fromUser) {
		this.fromUser = fromUser;
	}
	public ExpenseUser getToUser() {
		return toUser;
	}
	public void setToUser(ExpenseUser toUser) {
		this.toUser = toUser;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	
	

}
