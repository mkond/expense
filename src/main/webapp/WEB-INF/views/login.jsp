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
<c:if test="${not empty error}" >
	<c:out value="${error}"/>
</c:if>
<form action="<%=request.getContextPath()%>/j_spring_security_check" method="POST" name="form_login" class="form_login">
	<input type="text" name="user_login" placeholder="login"/><br/>
	<input type="password" name="password_login"placeholder="password"/><br/>
	<input type="submit" name="submit" value="submit"/><br/>
	<span>remember me </span><input type="checkbox" name="main_remember_me" class="check_remember_me">
</form>

</body>
</html>
