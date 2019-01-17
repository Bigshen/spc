<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FoxLink品質資訊與數位分析 系統</title>
	<link rel="stylesheet" type="text/css" href="../resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/SubPage.css">
	<script type="text/javascript" src="../resources/jq/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="top">
		<form class="form-horizontal" action="" method="get" role="form">
			<div class="form-box">
				<label for="fl-Factory"  class="col-sm-1 control-label">廠區:
				</label>
				<div class="col-sm-2">
					<select id="fl-Factory" class="form-control">
						<option>FQ-1F</option>
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
				<div class="col-sm-2">
					<select id="part-Verion" class="form-control">
						<option>禁止选择</option>
					</select>
				</div>
			</div>
			<div class="form-box">
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
					<input type="text" id="input-No" class="form-control"/>
				</div>
			</div>
			<div class="form-box">
				<label for="meas-Mac-No"  class="col-sm-1 control-label">量測機台號:
				</label>
				<div class="col-sm-2">
					<input type="text" id="meas-Mac-No" class="form-control"/>
				</div>
				<label for="mac-No"  class="col-sm-1 control-label">機台號:
				</label>
				<div class="col-sm-2">
					<input type="text" id="mac-No" class="form-control"/>
				</div>
				<label for="emp-No"  class="col-sm-1 control-label">量測員工號:
				</label>
				<div class="col-sm-2">
					<input type="text" id="emp-No" class="form-control"/>
				</div>
			</div>
			<button type="submit" class="btn btn-primary btn-left">提交</button>
			<!-- <button type="button" class="btn btn-primary">点击</button> -->
		</form>
	</div>
	<div class="bottom">
	</div>
</body>
</html>