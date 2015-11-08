<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href=<%=request.getContextPath()+"/resources/css/user.css" %> rel="stylesheet">
<title>user page</title>
</head>
<body>

	<div class="menu">
		<a href="<%=request.getContextPath()%>/user">Головна</a>
		<a href="<%=request.getContextPath()%>/user/addexpense">Нові витрати</a>
		<a href="j_spring_security_logout">Вихід</a>
	</div>



	

	<div class="ExpenseList">
		<div>
		    <span class="cat">Категорія</span>
		    <span class="use">Платив</span>
		    <span class="sum">Сумма</span>
		    <span class="dat">Дата</span>
	    	<span class="tit">Опис</span>
	 	</div>
	  <c:forEach items="${expenselist }" var="user" >
	  		<div>
				<span class="cat">${user.getExpCategory().getName()}</span>
			    <span class="use">${user.getExpenseUser().getName()}</span>
			    <span class="sum">${user.getAmount()}</span>
			    <span class="dat">${user.getDate()}</span>
		    	<span class="tit">${user.getTitle()}</span>
	    	</div>
	  </c:forEach>
	</div>

		
		<div class="MoneyExpenseList">
			<div class="MoneyReturn">
				<form method="POST" action="<%=request.getContextPath()%>/user/moneyreturn">
					
						<select  name="returnUser">
				    		<option selected="selected" disabled="disabled">Хто повертає</option>
				    		<c:forEach items="${expenseUserListWithoutThis }" var="userList" >
				    			<option value="${userList.getId()}">${userList.getFirstname()}</option>
				    		</c:forEach>				    		
						</select>
					
					<input type="text" name="Sumaaaaa" class="sumToReturn"  placeholder="Сумма" required="required">
					<input type="submit" value="Повернув мені">
				</form>
			</div>
			<div class="ListToPay">
				<span class="PayUser">Кому я винен</span><span class="PayAmount">Сумма</span>
				<c:forEach items="${usersTransactionsINeedPay }" var="INeedToPay" >
	  				<div>
	  					<span class="PayUser">${INeedToPay.getToUser()}</span><span class="PayAmount">${INeedToPay.getAmount()} грн</span>    
	  				</div>	
	  			</c:forEach>
			</div>
			<div class="ListToPay">
				<span>Хто мені винен</span><span class="PayAmount">Сумма</span>
				<c:forEach items="${usersTransactionsToPayMe }" var="usersTransaction" >
	  				<div>
	  					<span class="PayUser">${usersTransaction.getToUser()}</span><span class="PayAmount">${usersTransaction.getAmount()} грн</span>  
	  				</div>  	
	  			</c:forEach>
			</div>
		</div>


</body>
</html>