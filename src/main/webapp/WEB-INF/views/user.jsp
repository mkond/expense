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
		    <span>Категорія</span>
		    <span>Платив</span>
		    <span>Сумма</span>
		    <span>Дата</span>
	    	<span>Опис</span>
	 	</div>
	  <c:forEach items="${expenselist }" var="user" >
	  		<div>
				<span>${user.getExpCategory().getName()}</span>
			    <span>${user.getExpenseUser().getName()}</span>
			    <span>${user.getAmount()}</span>
			    <span>${user.getDate()}</span>
		    	<span>${user.getTitle()}</span>
	    	</div>
	  </c:forEach>
	</div>

		
		<div class="MoneyExpenseList">
			<div class="INeedToPay">
				<span class="UserName">Кому я винен</span><span class="UserAmount">Сумма</span>
				<c:forEach items="${usersTransactionsINeedPay }" var="INeedToPay" >
	  				<div>
	  					<span class="UserName">${INeedToPay.getToUser()}</span><span class="UserAmount">${INeedToPay.getAmount()}</span>    
	  				</div>	
	  			</c:forEach>
			</div>
			<div class="WhoNeedToPayMe">
				<span class="UserName">Хто мені винен</span><span class="UserAmount">Сумма</span>
				<c:forEach items="${usersTransactionsToPayMe }" var="usersTransaction" >
	  				<div>
	  					<span class="UserName">${usersTransaction.getToUser()}</span><span class="UserAmount">${usersTransaction.getAmount()}</span>  
	  				</div>  	
	  			</c:forEach>
			</div>
		</div>


</body>
</html>