<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="ru">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Welcome to Expense | Sign In</title>

    <!-- Bootstrap -->
    <link href=<%=request.getContextPath()+"/resources/css/bootstrap.css" %> rel="stylesheet">
	<link href=<%=request.getContextPath()+"/resources/css/login.css" %> rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
<body>


	


<div class="container col-lg-offset-5 col-lg-2 col-md-offset-5 col-md-3 col-sm-offset-4 col-sm-4 col-xs-offset-4 col-xs-5">
	<div class="row">	
		<form class="form-signin" action="<%=request.getContextPath()%>/j_spring_security_check" method="POST">
			<h1 class="form-signin-heading text-muted">Sign In</h1>
			<input type="text" class="form-control" placeholder="user" name="user_login" required="" autofocus="">
			<input type="password" class="form-control" placeholder="password" name="password_login" required="">
			<input type="submit" class="btn btn-lg btn-primary btn-block"  name="submit"></input>
		</form>
	</div>
</div>

	
	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src=<%=request.getContextPath()+"/resources/js/bootstrap.js" %>></script>
</body>

</html>