<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>FoxLink品質資訊與數位分析 系統</title>
	<link rel="stylesheet" type="text/css" href="./resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script type="text/javascript" src="./resources/jq/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="./resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<style type="text/css">
		*{
			margin:0;
			padding: 0;
		}
		.form-horizontal > div{
			margin-top:10px;
			display: block;
		}
		
		.form-horizontal .form-box{
			display:inline-block;
			width:80%;
		}
		.btn-left{
			position:absolute; 
			top:170px;
			right:200px;
			width:150px;
		}
		
	</style>
</head>
<body>
	<form class="form-horizontal" action="" method="get" role="form">
		<div class=".form-group form-box">
			<label for="fl-Factory"  class="col-sm-1 control-label">廠區:
			</label>
			<div class="col-sm-2">
				<select id="fl-Factory" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="fl-Link"  class="col-sm-1 control-label">綫別:
			</label>
			<div class="col-sm-2">
				<select id="fl-Link" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="pro-Name"  class="col-sm-1 control-label">專案名稱:
			</label>
			<div class="col-sm-2">
				<select id="pro-Name" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="part-Verion"  class="col-sm-1 control-label">料號_版本:
			</label>
			<div class="part-Verion">
				<select id="disabledSelect" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
		</div>
		<div class=".form-group form-box">
			<label for="meas-Stage"  class="col-sm-1 control-label">量測階段:
			</label>
			<div class="col-sm-2">
				<select id="meas-Stage" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="meas-Hz"  class="col-sm-1 control-label">量測頻率:
			</label>
			<div class="col-sm-2">
				<select id="meas-Hz" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="p-Number"  class="col-sm-1 control-label">節次:
			</label>
			<div class="col-sm-2">
				<select id="p-Number" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="input-No"  class="col-sm-1 control-label">入庫單號:
			</label>
			<div class="col-sm-2">
				<select id="input-No" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
		</div>
		<div class=".form-group form-box">
			<label for="meas-Mac-No"  class="col-sm-1 control-label">量測機台號:
			</label>
			<div class="col-sm-2">
				<select id="meas-Mac-No" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="mac-No"  class="col-sm-1 control-label">機台號:
			</label>
			<div class="col-sm-2">
				<select id="mac-No" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="emp-No"  class="col-sm-1 control-label">量測員工號:
			</label>
			<div class="col-sm-2">
				<select id="emp-No" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
			<label for="disabledSelect"  class="col-sm-1 control-label">专案名称:
			</label>
			<div class="col-sm-2">
				<select id="disabledSelect" class="form-control">
					<option>禁止选择</option>
				</select>
			</div>
		</div>
		<button type="submit" class="btn btn-primary btn-left">提交</button>
		<!-- <button type="button" class="btn btn-primary">点击</button> -->
	</form>
</body>
</html>