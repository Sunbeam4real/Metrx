<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> 
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
 <%@ page import ="com.fdmgroup.model.T_User"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MetrX</title>

<!-- jQuery -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js" integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E=" crossorigin="anonymous"></script>

<!-- Fontawsome -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">

<!-- js -->
<script src="script/lc_switch.js"></script>

<!-- css -->
<link rel="stylesheet" type="text/css" href="styles/login.css">
<link rel="stylesheet" type="text/css" href="styles/manageUser.css">
<link rel="stylesheet" type="text/css" href="styles/lc_switch.css">
<link rel="stylesheet" type="text/css" href="styles/businessCase.css">
<link rel="stylesheet" type="text/css" href="styles/common.css">
<link rel="stylesheet" type="text/css" href="styles/dashboard.css">
<link rel="stylesheet" type="text/css" href="styles/activity.css">






</head>
<body>
<div class="content">
	<nav class="navbar navbar-dark bg-dark"> 
		<a class="navbar-brand" href="index" method="post" style="width: 200px;"> 
			<img src="images/logo.png" alt="MetrX logo"style="width: 25%;"> 
			<img src="images/logo_text.png" alt="MetrX text" style="width: 25%;">
		</a>
	
		<% if(session.getAttribute("loggedinuser") != null) { %>
		<div class="navbar-header navbar-text">
			<div class="btn-group">
				<button type="button" class="btn btn-link dropdown-toggle user-link" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					${ loggedinuser.firstName } ${ loggedinuser.lastName }
				</button>
				<div class="dropdown-menu dropdown-menu-right">
					<form method="get" action="manageUser-regular.jsp">
						<button class="dropdown-item" type="submit">Setting</button>
					</form>
				<div class="dropdown-divider"></div>
					<form action="logout">
						<button class="dropdown-item" type="submit">Logout</button>
					</form>
				</div>
			</div>
		</div>
		<% } %>
	</nav>