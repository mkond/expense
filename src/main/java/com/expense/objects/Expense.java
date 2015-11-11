package com.expense.objects;

import java.util.Date;

import javax.validation.constraints.Size;

public class Expense {
	
	private int id;
	private ExpCategory expCategory;
	private ExpenseUser expenseUser;
	@Size(max=4)
	private int amount;
	private Date date;
	private String title;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public ExpCategory getExpCategory() {
		return expCategory;
	}
	public void setExpCategory(ExpCategory expCategory) {
		this.expCategory = expCategory;
	}
	
	public ExpenseUser getExpenseUser() {
		return expenseUser;
	}
	public void setExpenseUser(ExpenseUser expenseUser) {
		this.expenseUser = expenseUser;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
