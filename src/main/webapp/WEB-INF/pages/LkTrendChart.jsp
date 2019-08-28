<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
    <c:url value="../resources/img/loading.gif" var="ajaxLoaderPic"/>
	<link rel="stylesheet" type="text/css" href="../resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/SubPage.css">
	<script type="text/javascript" src="../resources/jq/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../resources/JS/AjaxCheckSession.js"></script>
	<script type="text/javascript" src="../resources/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../resources/JS/operate/LkTrendChart.js"></script>
	
	
</head>
<style type="text/css">
   table tr td{padding-top:20px;}
</style>
<body >
	<div class="top">
<form  target="form" id="downExcel" name="downExcel" action="test" method="post" onsubmit="return check();">
			<div class="form-box">
					<h3><span class="span-left">生成趨勢圖</span></h3>
			</div>
  	<table frame=void class="table table-condensed">
	        <colgroup>
				<col style="width:4%">
				<col style="width:15%">
				<col style="width:4%">
				<col style="width:15%">
			    <col style="width:5%">
				<col style="width:15%">
				<col style="width:5%">
				<col style="width:8%">
				<col style="width:4%">
				<col style="width:15%">
				<col style="width:20%">
				
			</colgroup>
     <tbody>
	   <tr>
	      <td align="right" >料號:</td>
	      <td>
	         <select id="id-PartNO" name="id-PartNO" class="form-control input-sm">
				
			 </select>
	      </td>
	      <td align="right">模號:</td>
		  <td >		  
		     <select id="id-MoldNO" name="id-MoldNO" class="form-control input-sm">
				
			 </select>
		  </td>
		  <td align="right">模穴號:</td>
		  <td >		  
		     <select id="id-CavNO" name="id-CavNO" class="form-control input-sm">
				
			 </select>
		  </td>
		  <td align="right">模穴數:</td>
		  <td>		   
		     <select id="id-CavQty" name="id-CavQty" class="form-control input-sm">
				
			 </select> 
		  </td>
		  <td align="right">日期:</td>
		  <td>
		     <input type="text" id="id-TimeValue" name="id-TimeValue" onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})" class="Wdate"/>
		  </td>
		  <td>
		     <input style="display:none" id="id-query" class="btn btn-primary btn-sm" type="button"   value="查詢" />
		      <input id="id-query2" class="btn btn-primary btn-sm" type="submit"    value="导出" />
		      
		  </td>
	   </tr>
	 </tbody>
	</table>
	</form>
	</div>
   <div class="bottom" style = "overflow:auto">
   <label id='txtendflag' style="display:none"></label>
	   		<div class="col-sm-2 col-md-2">
			   <img src="${ajaxLoaderPic}" id="ajaxLoader" style="display:none" />
		    </div>
		    <iframe name="form" id="form"   frameborder="no"  border="0" ></iframe>
	</div>
<!-- 	<table class="table table-hover table-bordered">
		     <colgroup>
				<col style="width:10%">
				<col style="width:10%">
				<col style="width:10%">
				<col style="width:10%">
				<col style="width:30%">
				
			</colgroup>
			    <tbody>
	    <tr >
	       <td  bgcolor="#FFC1C1" id='td-01'>01</td>
	       <td bgcolor=" #FFC1C1" id='td-02'>02</td>
	       <td bgcolor="#FFC1C1" id='td-03'>03</td>
	       <td bgcolor="#FFC1C1"  id='td-04'>04</td>
	    </tr>
	    <tr>
	       <td bgcolor="#FFC1C1" id='td-05'>05</td>
	       <td bgcolor="#FFC1C1" id='td-06'>06</td>
	       <td bgcolor="#FFC1C1" id='td-07'>07</td>
	       <td bgcolor="#FFC1C1" id='td-08'>08</td>
	    </tr>
	    <tr>
	       <td bgcolor="#FFC1C1" id='td-09'>09</td>
	       <td bgcolor="#FFC1C1" id='td-10'>10</td>
	       <td bgcolor="#FFC1C1" id='td-11'>11</td>
	       <td bgcolor="#FFC1C1" id='td-12'>12</td>
	    </tr>
	    


	    </tbody>
	</table> -->

		

</body>
</html>