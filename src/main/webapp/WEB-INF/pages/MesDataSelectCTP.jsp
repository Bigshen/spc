<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/SubPage.css">
	<script type="text/javascript" src="../resources/jq/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../resources/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="../resources/JS/AjaxCheckSession.js"></script>
	<script type="text/javascript" src="../resources/JS/operate/MeasureDataCTP.js"></script>
</head>
<body>
<div class="top">
	<form class="form-horizontal" id="form1" enctype="multipart/form-data" method="post">
		<div class="dateSelect">
			<div>
				<select style="width:215px; height: 30px" class="form-control" id="year">
					<option></option>
					<option></option>
					<option></option>
					<option selected></option>
					<option></option>
					<option></option>
					<option></option>
				</select>
			</div>
			<div style="position: absolute;left: 220px;top: 10px;">
				<select style="width:215px; height: 30px" class="form-control" id="month">
					<option>01</option>
					<option>02</option>
					<option>03</option>
					<option>04</option>
					<option>05</option>
					<option>06</option>
					<option>07</option>
					<option>08</option>
					<option>09</option>
					<option>10</option>
					<option>11</option>
					<option>12</option>
				</select>
			</div>
			<div style="position: absolute;left: 440px;top: 10px;">
				<button type="button" class="btn btn-primary btn-main" style="width: 80px;height: 30px;" id="DateOK">OK</button>
			</div>
		</div>
		<div class="form-box">
			<label for="fl-Factory" class="col-sm-1 control-label">廠區(*):
			</label>
			<div class="col-sm-2">
				<select id="fl-Factory" name="fl-Factory" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="fl-Link" class="col-sm-1 control-label">綫別(*):
			</label>
			<div class="col-sm-2">
				<select id="fl-Link" name="fl-Link" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="pro-Name" class="col-sm-1 control-label">專案名稱(*):
			</label>
			<div class="col-sm-2">
				<select id="pro-Name" name="pro-Name" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="part-Verion" class="col-sm-1 control-label">料號_版本(*):
			</label>
			<div class="col-sm-2">
				<select id="part-Verion" name="part-Version" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
		</div>
		<div class="form-box">
			<label for="dpick1" class="col-sm-1 control-label">搜尋日期(*):
			</label>
			<div class="col-sm-2">
				<%--<input id="dpick1" name="search-time" style="height:34px;" class="Wdate form-control" type="text" onClick="WdatePicker({maxDate:'%y-%M-{%d}'})" autocomplete="off">--%>
				<div class="col-sm-2">
					<select id="dpick1" name="search-time" style="width:215px;"  class="form-control"  >
						<option>禁止选择</option>
					</select>
				</div>
			</div>
			<label for="testType" class="col-sm-1 control-label">測試類別(*):
			</label>
			<div class="col-sm-2">
				<div class="col-sm-2">
					<select id="testType" name="search-time" style="width: 215px;"  class="form-control"  >
						<option></option>
						<option>一般</option>
						<option>成型</option>
						<option>鐳雕</option>
					</select>
				</div>
			</div>
			<div>
				<button type="button" class="btn btn-primary btn-main" id="QueryOK" style="width: 80px;height:35px">查詢</button>
			<%--<button type="button" class="btn btn-primary btn-main" id="exportData"  style="width: 80px;height:35px">導出</button>--%>
			</div>

		</div>
	</form>
</div>

<div class="bottom" style = "overflow:auto">
	<table class="table table-hover table-bordered show-table" style="width:90%;" id="MeasureDataCTPTable">
		<thead>
		</thead>
		<tbody></tbody>
	</table>
</div>
</body>
</html>