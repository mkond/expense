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
    <title>Ooops some problems!</title>
    <!-- Bootstrap -->
    <link href=<%=request.getContextPath()+"/resources/css/bootstrap.css" %> rel="stylesheet">
	<link href=<%=request.getContextPath()+"/resources/css/404.css" %> rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
<body>
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
					<span class="sr-only">Меню</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a href="#" class="navbar-brand"><i>#expense</i></a>
			</div>
			<div class="collapse navbar-collapse" id="responsive-menu">
				<ul class="nav navbar-nav">
					<li><a href="<%=request.getContextPath()%>">Головна</a></li>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="container">
		<h3>Ooops some problems!</h3>
	</div>
</body>
</html>