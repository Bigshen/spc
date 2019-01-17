<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
	<title>FoxLink品質資訊與數位分析 系統</title>
	<link rel="stylesheet" type="text/css" href="./resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./resources/easyUI/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="./resources/easyUI/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="./resources/css/indexmain.css"/>
    <!-- <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"> -->
	<script type="text/javascript" src="./resources/easyUI/jquery.min.js"></script>
	<script type="text/javascript" src="./resources/easyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="./resources/JS/AjaxCheckSession.js"></script>
	<script type="text/javascript" src="./resources/JS/NavBarMenu.js"></script>
	<script type="text/javascript" src="./resources/JS/nav-main.js"></script>
	<script type="text/javascript"src="./resources/JS/tab-look.js"></script>

</head>
<body class="easyui-layout">
	<div region="north" border="false" class="north-s">
		<h1></h1>
		<div class="name-exit">
			<span>${message}</span>
			<a href="#" id="exitLog">退出登陸</a>
		</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width:205px;">
		<div class="easyui-accordion" fit="true" border="false">
			
			<ul id="main-nav" class="nav nav-stacked" style="width:100%;height:100%;overflow:auto">
              
                </ul>		
		</div>
	</div>
	<div id="content" region="center">
		<div class="easyui-layout" fit="true">
		<!-- 	<div region="north"  style="overflow: hidden;height:30%;" split="true"> -->
				 <div id="tabs" class="easyui-tabs" fit='true'  border="false"> 
					
				 </div>
				<!--  <div id="iframeContent" style="overflow: hidden">
						
				</div> -->
			<!-- </div> -->
		<!-- 	<div region="center" class="ceshi" style="overflow: hidden;height:70%;">
			</div> -->
		</div>
	</div>
	<%-- <jsp:include page="logout.jsp" /> --%>
</body>
</html>