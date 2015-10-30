<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
<%-- 	<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet"> --%>
	<link href=<%=request.getContextPath()+"/resources/css/login.css" %> rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Lato:100italic,300' rel='stylesheet' type='text/css'>
</head>
<body>
	<span class="errorUP">
		<c:if test="${not empty error}" >
			<c:out value="${error}"/>
		</c:if>
	</span>
	<form action="<%=request.getContextPath()%>/j_spring_security_check" method="POST" name="form_login" class="form_login">
		<input type="text" name="user_login" placeholder="login" required="required"/><br/>
		<input type="password" name="password_login"placeholder="password" required="required"/><br/>
		<input type="submit" name="submit" value="Sign in"/><br/>
	</form>

</body>
</html>
