$(document).ready(function(){
	var vaRetrun;
	$('#Submit_Upload').click(function(){
		//1、先判断是否有选择文件。
		if($('#SP-FileUp').val().length!=0){
			//2、先判断文件类型--只能上传excel等文本类型的文件
            var fileName = $("#SP-FileUp").val();
            var fileNameExtension = fileName.substr(fileName.lastIndexOf("."));//取得文件的扩展名，.xls或者.xlsx
            if(fileNameExtension==".xls" || fileNameExtension==".xlsx"){
            	checkPartNumberExist();
            	if(vaRetrun=='Y'){
            		if(confirm("此料號規格已經存在!!是否要複寫？")) {
            			upfileAjax();
            			showSpec();
            	    };
            	}else if(vaRetrun=='N'){
            		upfileAjax();
            		showSpec();
            	}else{
            		alert("NG:上传档案异常！")
            	}
            }else{
            	alert("NG:请选择Excel文件！");
            	return false;
            }
		}else{
			alert("NG:未选择任何Excel文件！");
			return false;
		}
	});
	
	function checkPartNumberExist(){
		var strPartNumberAll = $("#SP-FileUp").val();
		/*alert(strPartNumberAll);*/
		var str2V=strPartNumberAll.substr(0,strPartNumberAll.indexOf('.'));
	/*	alert(str2V);*/
		$.ajax({ 
			   url:"../uploadSpec/checkPartNumber.do", 
			   type:'post',
			   async : false,
			   data:{"str2V":str2V},
			   success:function(res){ 
			     vaRetrun=res;
			   }, 
			   error:function(err){ 
			    alert("NG:"+err); 
			   } 
			  
			  })   
	}
	
	function upfileAjax(){
		var form = document.getElementById(' ');
		var formData = new FormData(form);
		/*console.log(formData.get('file'));*/
		  $.ajax({ 
		   url:"../uploadSpec/ajaxUploadSpec.do", 
		   type:'POST', 
		   data:formData,
		   async : false,
		   processData:false, 
		   contentType:false, 
		   success:function(res){ 
			  if(res){ 
			    alert(res); 
			  } 
		   }, 
		   error:function(err){ 
		    alert('错误：'+err); 
		   } 
		  
		  }) 
	}
	
	function showSpec(){
		var strPartNumberAll = $("#SP-FileUp").val();
		var str2V=strPartNumberAll.substr(0,strPartNumberAll.indexOf('.'));
		$.ajax({
			url:"../uploadSpec/ShowSpec",
			type:"POST",
			async : true,
			data:{"str2V":str2V},
			success:function(result){ 
				var StatusCode = result.StatusCode;
				var message = result.message;
				if(StatusCode == "500"){
					alert(message);
				}else if(StatusCode == "200"){
					ShowSpecList(message);
				}
			}, 
			error:function(err){ 
				 alert("NG:"+err); 
			} 
		})
	}
//	private String PROJECT_NAME;//專案名稱
//	private String WorkShop;//工站名稱
//	private String Inspection_Item;//檢驗項目
//	private String INSPECTION_CONTENT;//檢驗內容
//	private String INSPECTION_METHOD;//檢驗方法
//	private int Nominal_Dim;//尺寸
//	private int Upper_Dim;//上限
//	private int Lower_Dim;//下限
//	private String Frequency;//頻率
//	private String Status;//檢驗階段
//	private String Remark1;//備註
//	private String SPC_NUM;//spc尺寸
//	private String DIM_LOCATION;//尺寸位置
//	private String DATE_TIME;//上傳時間
//	private String PERSONNEL_ID;//上傳者
	function ShowSpecList(result){
		/*var obj = $.parseJSON(result)*/
		var obj = JSON.parse(result)
		$(".bottom").html('');
		/*console.log(obj[0].WorkShop);*/
		var ShowTable = '';
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable'><thead><tr><th>專案名稱</th><th>FAI/工站號</th><th>檢驗項目</th><th>檢驗內容</th><th>尺寸</th><th>上限</th><th>下限</th><th>頻率</th><th>檢驗階段</th><th>檢驗方式</th><th>備注</th><th>SPC尺寸號</th><th>SPC尺寸位子</th></tr></thead><tbody>";
		for(var i=0;i<obj.length;i++){
			ShowTable+='<tr><td>'+obj[i].PROJECT_NAME+'</td>'
				      +'<td>'+obj[i].WorkShop+'</td>'
					  +'<td>'+obj[i].Inspection_Item+'</td>'
					  +'<td>'+obj[i].INSPECTION_CONTENT+'</td>'
					  +'<td>'+obj[i].Nominal_Dim+'</td>'
					  +'<td>'+obj[i].Upper_Dim+'</td>'
					  +'<td>'+obj[i].Lower_Dim+'</td>'
					  +'<td>'+obj[i].Frequency+'</td>'
					  +'<td>'+obj[i].Status+'</td>'
					  +'<td>'+obj[i].INSPECTION_METHOD+'</td>'
					 
					  
			var Remarks = obj[i].Remark1!=null?obj[i].Remark1:' ';
			var Spc_num = obj[i].SPC_NUM!=null?obj[i].SPC_NUM:'';
			var Dim_location = obj[i].DIM_LOCATION!=null?obj[i].DIM_LOCATION:'';
			ShowTable+='<td>'+Remarks+'</td>'
			 +'<td>'+Spc_num+'</td>'
			 +'<td>'+Dim_location+'</td></tr>'
		}
		ShowTable+="</tbody></table>";
		$(".bottom").append(ShowTable);
	}
})