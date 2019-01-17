<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="../resources/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/css/SubPage.css">
	<script type="text/javascript" src="../resources/jq/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="../resources/JS/GetFile.js"></script>
</head>
<body>
	<div class="top">
		<form class="form-horizontal" action="" method="get" role="form">
			<div class="form-box">
					<h3><span class="span-left">CTP規格書上傳</span></h3>
			</div>
			<div class="form-box">
				<label for="CTP-FileUp"  class="col-sm-1 control-label">檔案名稱:
				</label>
				<div class="file-main">
					<input id="lefile" type="file" style="display:none">
					<input id="CTP-FileUp" class="form-control file-text" type="text">
					<a class="btn btn-primary" onclick="$('input[id=lefile]').click();">選擇文件</a>
				</div>	
				<button type="submit" class="btn btn-primary file-only">上傳</button>	
			</div>
		</form>
	</div>
	<div class="bottom">
	</div>
</body>
</html>