package com.expense.objects;

public class Service {
	
	public static int getSumForEach(int sum, int countUsersToPay){
		
		if(sum % countUsersToPay ==0){
			return sum/countUsersToPay;
		}else{
			return(sum+(countUsersToPay-1))/countUsersToPay;
		}
	}
	
	public static String reformatDate(String date){
		
		String[] parts = date.split("/");
		String dateToDB = String.format("%s-%s-%s", parts[2],parts[0],parts[1]);
		return dateToDB;
	}

}
