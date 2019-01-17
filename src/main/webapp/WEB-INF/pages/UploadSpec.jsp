<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="../resources/jq/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="../resources/JS/UploadSpec.js"></script>

<meta charset="UTF-8"/>
<title>Insert title here</title>
<style>
L {
	font-family: "Arial Black", Gadget, sans-serif;
	color: #000000;
}

.textcss-1 {
	/* Size and position */
	position: relative;
	z-index: 100000;
	width: 180px;
	height: 25px;
	margin-left: 1px;
	/* Styles */
	background: #B9FFB7;
	border-radius: 2px;
	border: 1px;
	border-color: #000000;
	border-style: solid;
	box-shadow: #F99 cursor: pointer;
	outline: none;
	/* Font settings */
	font-weight: bold;
	color: #000000;
}

.BT1 {
	font: bold;
	font: , "Arial Black", Gadget, sans-serif;
	border: #009;
	border: 1px;
	border-radius: 3px;
	height: 25px;
	width: 90px;
	background-color: #B9FFB7;
	color: #000000;
	font-size: 16px;
	border-style: solid;
	cursor: pointer;
}

BR {
	height: 25px;
}

.BT2 {
	font: bold;
	font: , "Arial Black", Gadget, sans-serif;
	font-weight: 500;
	border: #009;
	border: 1px;
	border-radius: 3px;
	height: 25px;
	width: 70px;
	background-color: #B9FFB7;
	color: #000000;
	font-size: 16px;
	border-style: solid;
	cursor: pointer;
}

BR {
	height: 25px;
}

.ifile {
	position: absolute;
	opacity: 0;
	filter: alpha(opacity = 0);
}
</style>
</head>
<body>
	<form  name="form1" id="form1" enctype="multipart/form-data" method="post">
		<table width="550" cellpadding="5" cellspacing="5" frame="void"
			rules="groups" align="left">
			<tr>
				<td width=350 height=30 align=left colspan="2"><strong>一般巡檢規格書上傳</strong></td>
			</tr>

			<tr>
				<td width=350 height=30 align=left><strong>檔案名稱:</strong></td>

				<td width=330 height=30 align=left><input type="text"
					name="upfile" id="upfile" size="20" class="textcss-1" readonly></td>

				<td width=110 height=30 align=left><input type="button"
					value="選擇檔案" id="BT1" class="BT1" onclick="this.form.file.click();"></td>

				<td width=110 height=30 align=left><input type="button"
					name="Submit_Upload" id="Submit_Upload" value="上傳" class="BT2"></td>
					
					

				<td width=220 height=30 align=left><input type="file"
					name="file" id="file" class="ifile" 
					accept=".xls,.xlsx"
					onchange="this.form.upfile.value=this.value.substr(this.value.lastIndexOf('\\')+1);"></td>
			</tr>




		</table>
	</form>
</body>
</html>