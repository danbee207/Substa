<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="substa.beans.User"%>
<%@ page import="java.lang.Boolean"%>
<%@ page import="substa.beans.Customer" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Substa</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/index_css.css">
<link rel="stylesheet" href="css/jquery.datetimepicker.css">
<link rel="stylesheet" href="css/bootstrap-theme.css">
</head>
	<body>

		<div id="wrapper">
			<nav class="navbar navbar-default navbar-fixed-top">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="../main.jsp"><span
							class="SubstaLabel">Substa</span></a>
					</div>
				</div>
				<!-- /.container-fluid --> 
			</nav>
		</div>
	
		<div id="contentBody">
			<div class="row"><hr class="featurette-divider"></div>
			<hr class="featurette-divider">
			<div class="row">
				<div class="col-md-3">
					<ul class="nav nav-pills nav-stacked">
						<li role="presentation"><a href="Home.jsp">Home</a></li>
						<li role="presentation"><a href="Customer Instructions.jsp">Customer Instructions</a></li>
						<li role="presentation"><a href="Employee Instructions.jsp">Employee Instructions</a></li>
						<li role="presentation" class="active"><a href="#">Programmer's Guide</a></li>
					</ul>
				</div>
				
				<div class="col-md-9">
					<div class="page-header">
  						<h1> Welcome a programmer! <small> How is Substa made? </small></h1>
					</div>
					<h3>1. E-R Diagram</h3>
					<a href="https://docs.google.com/document/d/1riuoj7nrAym99JAJAebkBtiFZYoYdCjCT1KYzA7nhXk/edit?usp=sharing">
						<p style="text-indent: 5em;">   Look at the Google Docs for more. </p></a>
					<hr class="featurette-divider">
					
					<h3>2. DB Relational Models</h3>
					<a href="https://docs.google.com/document/d/1NZT2fsgtJoWIOqjbE-POdiC3FbyFkxwCaNNE4PMy44c/edit?usp=sharing">
						<p style="text-indent: 5em;">   Look at the Google Docs for more. </p></a>
					<hr class="featurette-divider">
					
					<h3>3. SQL Queries</h3>
					<a href="https://docs.google.com/document/d/1K_RUeENIVqV_o7IYGWcEESJ9UurmchiX3QUGMWH_zgM/edit?usp=sharing">
						<p style="text-indent: 5em;">   Look at the Google Docs for more. </p></a>
					<hr class="featurette-divider">
					
				</div>
			</div>
			<hr class="featurette-divider">
		</div>

		<div class="container" id="footer">
			<div class="navbar-header" id=footerHeader>
				<a class="navbar-brand" href="../main.jsp"><span class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-footer navbar-right">
				<h4>Copy@ Right Substa | Stony Brook University</h4>
			</div>
		</div>
	
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/newAuction_js.js"></script>
		
</body>
</html>