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
	<p><a href="j_spring_security_logout">Logout</a></p>


	
	<a href="<%=request.getContextPath()%>/user/addexpense">Нові витрати</a>
	<table>
		<tr>
		    <td>Категорія</td>
		    <td>Платив</td>
		    <td>Сумма</td>
		    <td>Дата</td>
	    	<td>Опис</td>
	 	</tr>
	  <c:forEach items="${expenselist }" var="user" >
	  		<tr>
				<td>${user.getExpCategory().getName()}</td>
			    <td>${user.getExpenseUser().getName()}</td>
			    <td>${user.getAmount()}</td>
			    <td>${user.getDate()}</td>
		    	<td>${user.getTitle()}</td>
	    	</tr>
	  </c:forEach>
	</table>
	
	<div class="myExpense"></div>
	<div class="MonyToMe">
		<table>
			<tr>
			    <td>Должнік</td>
			    <td>Сумма</td>
	 		</tr>
	 	<c:forEach items="${usersTransactionsToPayMe }" var="usersTransaction" >
	  		<tr>
				<td>${usersTransaction.getToUser()}</td>
			    <td>${usersTransaction.getAmount()}</td>
	    	</tr>
	  </c:forEach>
		</table>
	</div>


</body>
</html>