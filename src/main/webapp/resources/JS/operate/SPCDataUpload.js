$(document).ready(function(){
	var vaRetrun;
	
/*	$('#lefile').change(function(){
		var file = this.files;
		console.log(file);
	})*/
	
	$('#Submit_DataUpload').click(function(){
		//1、先判断是否有选择文件。
		if($('#SPC-DataUp').val().length!=0){
			//2、先判断文件类型--只能上传excel等文本类型的文件
            var fileName = $("#SPC-DataUp").val();
            var file = fileName.split(',');
//            console.log(file);
            var fileList = $('#lefile').get(0).files;
//            console.log($('#lefile'));
            var formData = new FormData();
            for(var i=0;i<fileList.length;i++){    
	            var fileNameExtension = file[i].substr(file[i].lastIndexOf("."));//取得文件的扩展名，.xls或者.xlsx
	           // console.log(fileList[i]);
	            if(fileNameExtension==".xls" || fileNameExtension==".xlsx"){
	            	formData.append("file",fileList[i]);
 
	            }else{
	            	alert("NG:请选择Excel文件！");
	            	return false;
	            }
            }
            checkSPCDataExist(formData);
        	//var vaRetrun='N'; 
        	if(vaRetrun=='Y'){
        		if(confirm("選擇上傳的文檔中有已經有上傳過的文檔!!是否要複寫？")) {
        			upfileAjax(formData);
        			//showCTPSpec();
        	    };
        	}else if(vaRetrun=='N'){
        		//console.log(formData.get("file"));
        		upfileAjax(formData);
        		//showCTPSpec();
        	}else{
        		alert("NG:上传档案异常！")
        	}
		}else{

			alert("NG:未选择任何Excel文件！");
		
			return false;
		}
	});
	
	function checkSPCDataExist(formData){
		//alert(str2V);
		$.ajax({ 
			   url:"../UploadSpcData/checkSPCData.do", 
			   type:'post',
			   async : false,
			   data:formData,
			   processData:false, 
			   contentType:false, 
			   success:function(res){ 
			     vaRetrun=res;
			   }, 
			   error:function(err){ 
			    alert("NG:"+err); 
			   } 
			  
		})   
	}
	function upfileAjax(formData){

		  $.ajax({ 
		   url:"../UploadSpcData/ajaxUploadSpcData.do", 
		   type:'POST', 
		   data:formData,
		   async : false,
		   processData:false, 
		   contentType:false, 
		   success:function(res){ 
				$(".file-text").val("");
			    alert(res); 
		   }, 
		   error:function(err){ 
		    alert('错误：'+err); 
		   } 
		  
		  }) 
	}
	
	
	function showCTPSpec(){
		var strProNumberAll = $("#SPC-DataUp").val();
		var str2V=strProNumberAll.substr(0,strProNumberAll.indexOf('.'));
		$.ajax({
			url:"../UploadSpcCTP/ShowCTPSpec",
			type:"POST",
			async : false,
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
				 alert("NG:"+err+"顯示錯誤"); 
			} 
		})
	}
	function ShowSpecList(result){
		/*var obj = $.parseJSON(result)*/
		var obj = JSON.parse(result)
		$(".bottom").html('');
		/*console.log(obj[0].WorkShop);*/
		var ShowTable = '';
		ShowTable += "<table class='table table-hover table-bordered show-table' id='linkManageTable'><thead><tr><th>專案名稱</th><th>工站號碼</th><th>工站名稱</th><th>設備名稱</th><th>檢驗項目</th><th>上限</th><th>下限</th><th>檢測型態</th><th>機台型號</th><th>備註</th></tr></thead><tbody>";
		for(var i=0;i<obj.length;i++){
			//ID,PROJECT_NAME,WORKSHOP,WORKSHOP_NAME,MACHINE_NAME,INSPECTION_ITEM,UPPER_DIM,LOWER_DIM,INSPECTION_TYPE,MACHINE_TYPE,REMARK,PERSONNEL_ID,DATE_TIME
			ShowTable+='<tr><td>'+obj[i].PROJECT_NAME+'</td>'
					  +'<td>'+obj[i].WORKSHOP+'</td>'
					  +'<td>'+obj[i].WORKSHOP_NAME+'</td>'
					  +'<td>'+obj[i].MACHINE_NAME+'</td>'
					  +'<td>'+obj[i].INSPECTION_ITEM+'</td>'
					  +'<td>'+obj[i].UPPER_DIM+'</td>'
					  +'<td>'+obj[i].LOWER_DIM+'</td>'
					  +'<td>'+obj[i].INSPECTION_TYPE+'</td>'
					  +'<td>'+obj[i].MACHINE_TYPE+'</td>'
					  +'<td>'+obj[i].REMARK+'</td></tr>'
					  
		}
		ShowTable+="</tbody></table>";
		$(".bottom").append(ShowTable);
		
	}

})