<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href=<%=request.getContextPath()+"/resources/css/newexpense.css" %> rel="stylesheet">
<title>New expense</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
</head>
<body>

	<form action="<%=request.getContextPath()%>/user/addexpense/add" method="POST">
		<select  name="category">
		    <option selected="selected"  disabled>Категорія</option>
		    	<c:forEach items="${expCategotyList }" var="category" >
					<option  value="${category.getId()}">${category.getName()}</option>
				</c:forEach>
		</select><br>
		<p>
			<c:forEach items="${expenseUserListWithoutThis }" var="userList" >
				<input type="checkbox" name="userId" value="${userList.getId()}">${userList.getFirstname()}<br>
				
			</c:forEach>
		</p>
		
		<p>
			Сумма: <input type="text" name="sumExp" >
		</p>
		
		<p>
			Дата: <input type="text" id="datepicker" name="date">
		</p>
		<p>
			Опис: <input type="text" name="title">
		</p>
		
		<p>
			<input type="submit" value="Записати"/>
		</p>
	</form>

	
	
	 
</body>
</html>