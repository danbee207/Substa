<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="substa.beans.User"%>
<%@ page import="java.lang.Boolean"%>
<%@ page import="substa.beans.Customer" %>
<jsp:useBean id="isCustomer" type="java.lang.Boolean" scope="session" />


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
	<%
		User LoginUser = (User)session.getAttribute("LoginUser");
		Customer customerInfo = (Customer)session.getAttribute("customerInfo");
		if(LoginUser!=null){
	%>
	<body><%}else{ %>
 	<body onload="loginReset();"><%} %>

	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="main.jsp"><span
					class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar" onclick="goCategories(this,	<%=LoginUser%>);">Book</span>
					<span class="icon-bar" onclick="goCategories(this);">Electronics</span>
					<span class="icon-bar" onclick="goCategories(this);">Fashion</span>
					<span class="icon-bar" onclick="goCategories(this);">Home & Garden</span> <span class="icon-bar" onclick="goCategories(this);">Motors</span>
					<span class="icon-bar" onclick="goCategories(this);">Sporting Goods</span> <span class="icon-bar" onclick="goCategories(this);">Toy & Hobbies</span>
				</button>

			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<form action="CategoriesShown" method="get" id="listclicked">

					<input type="hidden" value="" name="category" id="category">
				</form>
				<ul class="nav navbar-nav navbar-left">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Book
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Books</a></li>
							<li><a href="#" onclick="goCategories(this);">Textbooks</a></li>
							<li><a href="#" onclick="goCategories(this);">Magazines</a></li>
							<li><a href="#" onclick="goCategories(this);">E-Books</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Electronics<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">TV & Video</a></li>
							<li><a href="#" onclick="goCategories(this);">Cell Phones</a></li>
							<li><a href="#" onclick="goCategories(this);">Desktops</a></li>
							<li><a href="#" onclick="goCategories(this);">Laptops</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Fashion
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Women's Clothing</a></li>
							<li><a href="#" onclick="goCategories(this);">Men's Clothing</a></li>
							<li><a href="#" onclick="goCategories(this);">Kids' Clothing</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Home & Garden<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Kitchen</a></li>
							<li><a href="#" onclick="goCategories(this);">Furniture</a></li>
							<li><a href="#" onclick="goCategories(this);">Appliance</a></li>
							<li class="divider"></li>
							<li><a href="#" onclick="goCategories(this);">Flowers & Trees</a></li>
							<li><a href="#" onclick="goCategories(this);">Gardening Supplies</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Mortors
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Cars</a></li>
							<li><a href="#" onclick="goCategories(this);">Motorcylces</a></li>
							<li><a href="#" onclick="goCategories(this);">Automotive Tools</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Sporting Goods<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Team Sports</a></li>
							<li><a href="#" onclick="goCategories(this);">Leisure Sports</a></li>
							<li><a href="#" onclick="goCategories(this);">Water Sports</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Toy & Hobbies <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#" onclick="goCategories(this);">Action Figures</a></li>
							<li><a href="#" onclick="goCategories(this);">Building Toys</a></li>
							<li><a href="#" onclick="goCategories(this);">Baby Toys</a></li>
							<li class="divider"></li>
							<li><a href="#" onclick="goCategories(this);">Games</a></li>
							<li><a href="#" onclick="goCategories(this);">Videos</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><p class="navbar-text">
							Hello, <b><%=LoginUser.getLast()%></b>!
						</p>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">My
							Account<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">


							<li><a href="#">My Bidding Process</a></li>
							<li><a href="#">History</a></li>
							<li><a href="#">My Setting</a></li>
							<li class="divider"></li>
							<li><a href="#" data-toggle="modal"
								data-target="#logoutModal">Log out</a></li>

						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid --> </nav>
	</div>
	
	
		<div id="contentBody">
			<div class="row"><hr class="featurette-divider"></div>
			<hr class="featurette-divider">
			<div class="row">
				<div class="col-md-3">
					<ul class="nav nav-pills nav-stacked">
						<li role="presentation"><a href="Home.jsp">Home</a></li>
						<li role="presentation"><a href="Customer Instructions.jsp">Customer Instructions</a></li>
						<li role="presentation" class="active"><a href="#">Employee Instructions</a></li>
						<li role="presentation"><a href="Programmer's Guide.jsp">Programmer's Guide</a></li>
					</ul>
				</div>
				
				<div class="col-md-9">
					<div class="page-header">
  						<h1> Welcome our employee! <small> Manage employees and auctions. </small></h1>
					</div>
					<h3>1.Change the personal information</h3>
					<p style="text-indent: 5em;">   Click 'My Account'-'My Setting' on the top-right. </p>
					<p style="text-indent: 5em;">   Change your information. *Caution: Email & SSN are not changable.</p>
					<p style="text-indent: 5em;">   After filling the information correctly, click the orange 'Fix it!' button</p>
					<hr class="featurette-divider">
					
					<h3>2.Upload an auction</h3>
					<p style="text-indent: 5em;">   Click 'My Account'-'Upload an Auction' on the top-right. </p>
					<p style="text-indent: 5em;">   .</p>
					<p style="text-indent: 5em;">   .</p>
					<p style="text-indent: 5em;">   .</p>
					<hr class="featurette-divider">
					
					<h3>1.Sign Up</h3>
					<p style="text-indent: 5em;">   . </p>
					<p style="text-indent: 5em;">   .</p>
					<p style="text-indent: 5em;">   .</p>
					<p style="text-indent: 5em;">   .</p>
					<hr class="featurette-divider">
					
				</div>
			</div>
			<hr class="featurette-divider">
		</div>


	<div class="container" id="footer">
		<div class="navbar-header" id=footerHeader>
			<a class="navbar-brand" href="#"><span class="SubstaLabel">Substa</span></a>
		</div>
		<div class="navbar-footer navbar-right">
			<h4>Copy@ Right Substa | Stony Brook University</h4>
		</div>
	</div>

	
	<div class="modal fade bs-example-modal-sm" id="singinModal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Sing in</h4>
				</div>
				<form method="post" action="UserLogin" name="signIn">
					<div class="modal-body">

						<div class="form-group">
							<label for="SigninId">ID</label> <input type="text"
								name="signInId" class="form-control"
								placeholder="ex)abc@substa.com">
						</div>
						<div class="form-group">
							<label for="SigninPassword">Password</label><input
								type="password" name="signInPw" class="form-control">
						</div>
						<input type="hidden" value="index.jsp" name="TargetPage">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-warning"
							onclick="goTosignup();">Sign up</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Sign in</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<div class="modal fade bs-example-modal-sm" id="logoutModal"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Logout</h4>
				</div>

				<div class="modal-body">Do you want to log out ?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					<button type="button" class="btn btn-primary" onclick="Logout()">Yes</button>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript"
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/newAuction_js.js"></script>
		
</body>
</html>