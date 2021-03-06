<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href=<%=request.getContextPath()+"/resources/css/user.css" %> rel="stylesheet">
    <link href=<%=request.getContextPath()+"/resources/css/bootstrap.css" %> rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Lato:100italic,300' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
<title>Expense list</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

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
                            <li><a href="<%=request.getContextPath()%>/user">Головна</a></li>
                            <li><a href="j_spring_security_logout">Вихід</a></li>
                        </ul>
                    </div>
                </div>
            </div>
		

		<div class="container">
       <div class="row">
            <div class="bs-example op col-lg-offset-1 col-xs-offset-1 col-lg-5 col-md-5 col-sm-5 col-xs-5">
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <th>Категорія</th>
                            <th class="hidden-xs">Платив</th>
                            <th>Сумма</th>
                            <th class="hidden-xs hidden-sm">Дата</th>
                            <th class="hidden-xs hidden-sm">Опис</th>
                        </tr>
                    </thead>
                    <tbody>
                    	 <c:forEach items="${expenselist }" var="user" >
					  		<tr>
								 <td>${user.getExpCategory().getName()}</td>
							     <td class="hidden-xs">${user.getExpenseUser().getName()}</td>
							     <td>${user.getAmount()}</td>
							     <td class="hidden-xs hidden-sm">${user.getDate()}</td>
						    	 <td class="hidden-xs hidden-sm">${user.getTitle()}</td>
					    	</tr>
					  </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-offset-1 col-sm-offset-1 col-lg-4  col-md-4 col-sm-4 col-xs-4 ">
            	<div class="row">
                  <button class="btn btn-lg btn-primary btn-block" data-toggle="modal" data-target="#modal-2" style="margin-bottom: 20px">Нові витрати</button>
                </div>
                <div class="row">
                  <button class="btn btn-lg btn-info btn-block" data-toggle="modal" data-target="#modal-1" style="margin-bottom: 20px">Поверння</button>
                </div>
               <div class="row">                    
                    <table class="table table-bordered op">
                        <thead>
                            <tr>
                                <th><div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">Кому я</div></th>
                                <th> <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">Сумма</div></th>

                            </tr>
                        </thead>
                        <tbody>
                         	<c:forEach items="${usersTransactionsINeedPay }" var="INeedToPay" >
                            <tr>
                                <td>${INeedToPay.getToUser()}</td>
                                <td>${INeedToPay.getAmount()} грн</td>
                            </tr>	
	  			</c:forEach>
                        </tbody>
                    </table>
                </div>
                
                <div class="row">                    
                    <table class="table table-bordered op">
                        <thead>
                        
                            <tr>
                                <th><div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">Хто мені</div></th>
                                <th> <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">Сумма</div></th>

                            </tr>
                        </thead>
                        <tbody>
                       	 <c:forEach items="${usersTransactionsToPayMe }" var="usersTransaction" >
                            <tr>
                                <td>${usersTransaction.getToUser()}</td>
                                <td>${usersTransaction.getAmount()} грн</td>
                            </tr>	
	  					</c:forEach>
                        </tbody>
                    </table>
                </div>
                
            </div>
        </div>
    </div>


    <div class="modal fade" id="modal-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                   <button class="close"type="button" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Повернення фінансів</h4>
                </div>
                <div class="modal-body">
                    <div class="MoneyReturn">
						<form method="POST" action="<%=request.getContextPath()%>/user/moneyreturn">
					
						<select required aria-required="true"  name="returnUser" class="returnUser">
				    		<option value="">Хто повертає</option>
				    		<c:forEach items="${expenseUserListWithoutThis }" var="userList" >
				    			<option value="${userList.getId()}">${userList.getFirstname()}</option>
				    		</c:forEach>				    		
						</select>
					
					<input id="inputSum" name="Sumaaaaa" type="number" autofocus class="sumToReturn" placeholder="Сумма" required="required">
					<input type="submit" value="Повернув мені" class="btn btn-success">
				</form>
			</div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Отмена</button>
                </div>
            </div>
        </div>
    </div>
    <!-- modal 2 -->
   	<div class="modal fade" id="modal-2">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                   <button class="close"type="button" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Новий платіж</h4>
                </div>
                <div class="modal-body">
						<form action="<%=request.getContextPath()%>/user/addexpense/add" method="POST" onsubmit="return validateFrom()">
							<table class="table">
								<tbody>
									<tr>
										<th>Категорія</th>
										<th>
											<select  name="category" required="required" required aria-required="true">
											    <option selected="selected"  disabled></option>
											    	<c:forEach items="${expCategotyList }" var="category" >
														<option  value="${category.getId()}">${category.getName()}</option>
													</c:forEach>
											</select>
										</th>
									</tr>
									<tr>
										<th>Хто скідується</th>
										<th>
											<div class="checkbox-group required" id='test1'>
												<c:forEach items="${expenseUserListWithoutThis }" var="userList" >
													<input type="checkbox" name="userId" value="${userList.getId()}"> ${userList.getFirstname()}<br>
												</c:forEach>
											</div>
										</th>
									</tr>
									<tr>
										<th>Сумма</th>
										<th><input id="inputSum" name="sumExp" autofocus type="number" required="required" pattern="[0-9]{3}"></th>
									</tr>
									<tr>
										<th>Дата</th>
										<th><input type="text" id="datepicker" name="date" required="required"></th>
									</tr>
									<tr>
										<th>Опис</th>
										<th><input type="text" name="title" required="required"></th>
									</tr>
								</tbody>
							</table>
							<input class="btn btn-success btn-lg " type="submit" value="Записати"/>
						</form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Отмена</button>
                </div>
            </div>
        </div>
    </div>
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src=<%=request.getContextPath()+"/resources/js/bootstrap.js" %>></script>
    
    <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
  <script type="text/javascript">
  function validateFrom() {
	  var result = $("div.checkbox-group.required input:checked").length > 0;
	  if(!result) {
		  console.log('fill checkboxes')
		  }
	  return result;
	  }
  </script>
</body>
</html>