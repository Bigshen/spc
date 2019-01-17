$(document).ready(function(){
	var vaRetrun;
	$('#Submit_Upload').click(function(){
		//1、先判断是否有选择文件。
		if($('#upfile').val().length!=0){
			//2、先判断文件类型--只能上传excel等文本类型的文件
            var fileName = $("#upfile").val();
            var fileNameExtension = fileName.substr(fileName.lastIndexOf("."));//取得文件的扩展名，.xls或者.xlsx
            if(fileNameExtension==".xls" || fileNameExtension==".xlsx"){
            	checkPartNumberExist();
            	if(vaRetrun=='Y'){
            		if(confirm("此料號規格已經存在!!是否要複寫？")) {
            			upfileAjax();
            	    };
            	}else if(vaRetrun=='N'){
            		upfileAjax();
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
		var strPartNumberAll = $("#upfile").val();
		var str2V=strPartNumberAll.substr(0,strPartNumberAll.indexOf('.'));
		$.ajax({ 
			   url:"../uploadSpec/checkPartNumber.do", 
			   type:'post',
			   async : false,
			   data:{"str2V":str2V},
			   success:function(res){ 
			     vaRetrun=res;
			    console.log(res); 
			    $("#pic").val(""); 
			    $(".showUrl").html(res); 
			    $(".showPic").attr("src",res); 
			   }, 
			   error:function(err){ 
			    alert("NG:"+err); 
			   } 
			  
			  })   
	}
	
	function upfileAjax(){
		var form = document.getElementById('form1');
		var formData = new FormData(form); 
		//用formData定义文件流才可以使用ajax上传
		//var formData = new FormData(document.getElementById("file"));  
/*		var formData = new FormData();
		formData.append('file',$('input[name=file]')[0].files[0]);*/
		//var formData = new FormData($("#file")[0]);
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
		    console.log(res); 
		    $("#pic").val(""); 
		    $(".showUrl").html(res); 
		    $(".showPic").attr("src",res); 
		   }, 
		   error:function(err){ 
		    alert('错误：'+err); 
		   } 
		  
		  }) 
	}
	
/*	ajax執行的时候對應事件
	$(document).ajaxSend(function(){
		 $('#ajaxLoader').show();
		 $('#Submit_Upload').prop('disabled',true);
		 $('#BT1').prop('disabled',true);
	});
	ajaxQ请求完成時的對應事件
	$(document).ajaxComplete(function(){
		$('#ajaxLoader').hide();
		$('#Submit_Upload').prop('disabled',false);
		$('#BT1').prop('disabled',false);
	});
	$(document).ajaxStop(function(){
		$('#ajaxLoader').hide();
		$('#Submit_Upload').prop('disabled',false);
		$('#BT1').prop('disabled',false);
	});*/
	
	
});