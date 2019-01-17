<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true"%> --%>
<!DOCTYPE html>
<%-- <%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%> --%>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>FoxLink品質資訊與數位分析 系統</title>
	<link rel="stylesheet" type="text/css" href="./resources/bootstrap-3.3.7-dist/css/bootstrap.min.css"> 
<%-- 	<c:url value="./resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" var="iconsCSS" />
	<link href="${iconsCSS}" rel="stylesheet"> --%>
	<link rel="stylesheet" type="text/css" href="./resources/css/loginmain.css"> 

	<script type="text/javascript" src="./resources/jq/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="./resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="container-head">
			<div class='logo-style'></div>
			<h1 class='head-first'>品質資訊與數位分析 系統</h1>
			<h2 class='head-second'>Quality Information ＆ Analytics System</h2>
		</div>
		<div class="container-content">
			<div class='content-tab'>
  				<!-- Nav tabs -->
  				<ul class="nav nav-tabs" role="tablist">
  				  <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">登陸</a></li>
  				  <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">忘記密碼</a></li>
  				</ul>
				
  				<!-- Tab panes -->
  				<div class="tab-content">
  				  <div role="tabpanel" class="tab tab-pane active" id="home">
  				  	<div class="login">
  				  		<form method="post" action="login">
  							<div class="form-group">
  							  <label for="exampleInputAccount">賬號Account</label>
  							  <input type="text" class="form-control" id="exampleInputAccount" placeholder="Account" name="username">
  							</div>
  							<div class="form-group">
  							  <label for="exampleInputPassword">密碼Password</label>
  							  <input type="password" class="form-control" id="exampleInputPassword" placeholder="Password" name = "password">
  							 </div>
  							 <div class="tips">
  							 	<c:if test="${not empty error}">
                            		<div class="error" style="color:#FF0000">${error}</div>
                           		 </c:if>
                           		 <c:if test="${not empty logout}">
                            		<div class="logout" style="color:#FF0000">${logout}</div>
                           		 </c:if>
                           		 <c:if test="${not empty kickout}">
                            		<div class="kickout" style="color:#FF0000">${kickout}</div>
                           		 </c:if>			
  							 </div>
  							<button type="submit" class="btn btn-default btn-right">登陸</button>
						</form>
					</div>	
  				  </div>
  				  <div role="tabpanel" class="tab tab-pane" id="profile">
  				  	<div class="udPassword">
  				 		<form>
  							<div class="form-group">
  							 <label for="exampleInputAccountS">賬號Account</label>
  							  <input type="text" class="form-control" id="exampleInputAccountS" placeholder="賬號">
  							</div>
  							<div class="form-group">
  							  <label for="exampleInputName">姓名Name</label>
  							  <input type="text" class="form-control" id="exampleInputName" placeholder="中文姓名(繁體)">
  							 </div>
  							 <div class="form-group">
  							 <label for="exampleInputDepId">賬號DepId</label>
  							  <input type="text" class="form-control" id="exampleInputDepId" placeholder="部門代碼">
  							</div>
  							<div class="form-group">
  							 <label for="exampleInputNewPassWord1">新密碼PassWord</label>
  							  <input type="password" class="form-control" id="exampleInputNewPassWord1" placeholder="新密碼">
  							</div>
  							<div class="form-group">
  							 <label for="exampleInputNewPassWord2">確認新密碼</label>
  							  <input type="password" class="form-control" id="exampleInputNewPassWord2" placeholder="確認新密碼">
  							</div>
  							<!-- 	<div class="form-group">
  							 <label for="exampleInputAccount1">賬號Account</label>
  							  <input type="text" class="form-control" id="exampleInputAccount1" placeholder="Account">
  							</div> -->
  							<button type="submit" class="btn btn-default">重設密碼</button>
						</form>
					</div>
  				  </div>
  				</div>
			</div>
		</div>
		<div class="container-footer">
			※如有問題 , 請聯繫 : XXXXXXXXXXXXXXXXXX
		</div>
	</div>
</body>
</html>