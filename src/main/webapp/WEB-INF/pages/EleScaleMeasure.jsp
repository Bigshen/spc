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
	<script type="text/javascript" src="../resources/JS/operate/EleScaleMeasure.js"></script>
</head>
<html>
<body class="xqwacy">
<div class="top">
	<form class="form-horizontal" id="form1" enctype="multipart/form-data" method="post">
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
			<label for="pro-Name" class="col-sm-1 control-label">專案名稱:
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
		<div class="cy form-box">
			<label for="measure-Status" class="col-sm-1 control-label">量測階段(*):
			</label>
			<div class="col-sm-2">
				<select id="measure-Status" name="measure-Status" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>

			<label for="measure-Frequency" class="col-sm-1 control-label">量測頻率(*):
			</label>
			<div class="col-sm-2">
				<select id="measure-Frequency" name="measure-Frequency" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>

			<label for="JieCi" class="col-sm-1 control-label">節次(*):
			</label>
			<div class="col-sm-2">
				<select id="JieCi" name="JieCi" class="form-control">
					<option></option>
					<option>初件</option>
					<option>巡檢一</option>
					<option>巡檢二</option>
					<option>巡檢三</option>
					<option>巡檢四</option>
					<option>巡檢五</option>
				</select>
			</div>

			<label for="EnterStorageNum" class="col-sm-1 control-label">入庫單號:
			</label>
			<div class="col-sm-2">
				<input id="EnterStorageNum" type="text" class="form-control" />
			</div>
		</div>

		<div class="cy1 form-box">
			<label for="MeasureMachineNum" class="col-sm-1 control-label">量測機台號:
			</label>
			<div class="col-sm-2">
				<input id="MeasureMachineNum" type="text" class="form-control" />
			</div>

			<label for="MachineNum" class="col-sm-1 control-label">機台號:
			</label>
			<div class="col-sm-2">
				<input id="MachineNum"  type="text" class="form-control" />
			</div>

			<label for="MeasureEmpID" class="col-sm-1 control-label">量測員工號(*):
			</label>
			<div class="col-sm-2">
				<input id="MeasureEmpID" type="text" class="form-control" />
			</div>
			<div> <button type="button" class="btn btn-primary btn-main" id="QueryOK" style="width: 80px;height: 35px;">查詢</button></div>
		</div>
	</form>
</div>


<div class="bottom" style = "overflow:auto">
	<table class="table table-hover table-bordered show-table" style="width: 80%" id="EleScaleManageTable">
		<thead>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div style="display:none; position: relative" class="a">
		<input  width='140px' height='30px' style="position: absolute;top: 15px;left: 35%"  type='submit'  id='submit_Upload' value='資料上傳' class='btn btn-primary btn-main'>
	</div>
</div>

</body>
</html>