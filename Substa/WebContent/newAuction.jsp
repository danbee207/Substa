<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="substa.beans.User"%>
<%@ page import="java.lang.Boolean"%>

<jsp:useBean id="LoginUser"  type="substa.beans.User" scope="session" />
<jsp:useBean id="isCustomer" type="java.lang.Boolean" scope="session" />
<jsp:useBean id="customerInfo" type="substa.beans.Customer"
	scope="session" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Substa</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="css/newAuction.css">
<link rel="stylesheet" href="css/jquery.datetimepicker.css">

</head>
<body >

	<div id="wrapper">
		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.jsp"><span
					class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar" onclick="goCategories(this,<%=LoginUser%>);">Book</span>
					<span class="icon-bar" onclick="goCategories(this);">Electronics</span>
					<span class="icon-bar" onclick="goCategories(this);">Fashion</span>
					<span class="icon-bar" onclick="goCategories(this);">Home &
						Garden</span> <span class="icon-bar" onclick="goCategories(this);">Motors</span>
					<span class="icon-bar" onclick="goCategories(this);">Sporting
						Goods</span> <span class="icon-bar" onclick="goCategories(this);">Toy
						& Hobbies</span>
				</button>

			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-left">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Book
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Books</a></li>
							<li><a href="#">Textbooks</a></li>
							<li><a href="#">Magazines</a></li>
							<li><a href="#">E-Books</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Electronics<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">TV & Video</a></li>
							<li><a href="#">Cell Phones</a></li>
							<li><a href="#">Desktops</a></li>
							<li><a href="#">Laptops</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Fashion
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Women's Clothing</a></li>
							<li><a href="#">Men's Clothing</a></li>
							<li><a href="#">Kids' Clothing</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Home
							& Garden<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Kitchen</a></li>
							<li><a href="#">Furniture</a></li>
							<li><a href="#">Appliance</a></li>
							<li class="divider"></li>
							<li><a href="#">Flowers & Trees</a></li>
							<li><a href="#">Gardening Supplies</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Mortors
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Cars</a></li>
							<li><a href="#">Motorcylces</a></li>
							<li><a href="#">Automotive Tools</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Sporting
							Goods<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Team Sports</a></li>
							<li><a href="#">Leisure Sports</a></li>
							<li><a href="#">Water Sports</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">Toy
							& Hobbies <span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action Figures</a></li>
							<li><a href="#">Building Toys</a></li>
							<li><a href="#">Baby Toys</a></li>
							<li class="divider"></li>
							<li><a href="#">Games</a></li>
							<li><a href="#">Videos</a></li>
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
							<li><a href="#">Upload an Auction</a></li>

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
		<div class="upperPart"></div>
		<form method="post" action="UploadAuction" name="newAuction" enctype="multipart/form-data ">
			<div id="uploadScene" class="container">


				<div id="imagePart">
					<img src="img/noimg.png" alt="no image yet" class="img-rounded" id="previewImg"><br><br><br/>
					<input type="file" name="ImageFile"
						id="ImageFile" onchange="readURL(this);">
					<p class="help-block">Upload image file which you want to see
						at auction page</p>
				</div>
				<div id="theOtherPart">
				<div class="form-group" >
					<label for="titleItem">Name</label> 
					<input type="text" class="form-control" name="name" id="name">
				</div>
				<div class="form-group">
					<label for="BigtypeItem">Type</label>
					<select class="form-control" name="bType" id="bType" onchange="changeSubType(this);">
						<option vlaue="0"></option>
						<option value="1">Fashion</option>
						<option value="2">Home & Garden</option>
						<option></option>
						<option></option>
						<option></option>
						
					
					</select>
					<div id="subType">
					<label for="smalltypeItem">SubType</label>
					
					<select class="form-control" name="sType" id="sType">
						
					</select>
					</div>
				</div>
				<div class="form-group">
					<label for="num"> How many do you have?  </label>
					<input type="number" class="form-control" name="num" id="num" min="1">
				
				</div>
				<div class="form-group">
					<label for="endTime">When your auction is finished?</label>
					<input type="text" class="form-control" name="endDate" id="endDate" >
				
				</div>
				<div class="form-group">
					<label for="minBid">How much money is your minimum bid?</label>
					<input type="tel" class="form-control" name="minBid" id="minBid">
				
				</div>
				<div class="form-group">
					<label for="des">Tell your products in detail.</label>
					<input tyep="textarea" class="form-control" name="desc" id="desc">
				</div>
				<div class="form-group">
				<br>
	
				
					<div id="btnbox">
					<button type ="button" class="btn btn-default" onclick="history.back();">Cancel</button>
					<button type="button" class="btn btn-warning" onclick="goToServlet(this);">Sign in</button>
					</div>
				
				</div>
				</div>
				
			</div>
		</form>
	</div>





	<div id="footer">
	<nav class="navbar navbar-default navbar-fixed-bottom">
  <div class="container">
   <div class="navbar-header" id=footerHeader>
				<a class="navbar-brand" href="#"><span class="SubstaLabel">Substa</span></a>
			</div>
			<div class="navbar-footer navbar-right">
				<h4>Copy@ Right Substa | Stony Brook University</h4>
			</div>
  </div>
</nav>
	
	</div>
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
	<script type="text/javascript" src="js/jquery.datetimepicker.full.js"></script>
	<script>
	$("#endDate").datetimepicker({value:new Date(),step:10});
	</script>
		
</body>
</html>