<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href=<%=request.getContextPath()+"/resources/css/user.css" %> rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Lato:100italic,300' rel='stylesheet' type='text/css'>
<title>user page</title>

</head>
<body>

	<div class="menu">
		<a href="<%=request.getContextPath()%>/user"><div>Головна</div></a>
		<a href="<%=request.getContextPath()%>/user/addexpense"><div>Нові витрати</div></a>
		<a href="j_spring_security_logout"><div>Вихід</div></a>
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
					
						<select required aria-required="true"  name="returnUser" class="returnUser">
				    		<option value="">Хто повертає</option>
				    		<c:forEach items="${expenseUserListWithoutThis }" var="userList" >
				    			<option value="${userList.getId()}">${userList.getFirstname()}</option>
				    		</c:forEach>				    		
						</select>
					
					<input  name="Sumaaaaa" type="number" autofocus class="sumToReturn" placeholder="Сумма" required="required">
					<input type="submit" value="Повернув мені" class="btnrtrn">
				</form>
			</div>
			<div class="ListToPay">
				<div><span>Кому я винен</span><span>Сумма</span></div>
				<c:forEach items="${usersTransactionsINeedPay }" var="INeedToPay" >
	  				<div>
	  					<span>${INeedToPay.getToUser()}</span><span>${INeedToPay.getAmount()} грн</span>    
	  				</div>	
	  			</c:forEach>
			</div>
			<div class="ListToPay">
				<div><span>Хто мені винен</span><span>Сумма</span></div>
				<c:forEach items="${usersTransactionsToPayMe }" var="usersTransaction" >
	  				<div>
	  					<span class="PayUser">${usersTransaction.getToUser()}</span><span class="PayAmount">${usersTransaction.getAmount()} грн</span>  
	  				</div>  	
	  			</c:forEach>
			</div>
		</div>


</body>
</html>