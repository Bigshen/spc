/**
 * html5调用摄像头扫码
 */
$(document).ready(function(){

	 var u = navigator.userAgent, app = navigator.appVersion;
     var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Linux') > -1; //android终端或者uc浏览器
     var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
     //alert('是否是Android：'+isAndroid);
     //alert('是否是iOS：'+isiOS);
     if (isAndroid) {
         //如果是安卓手机，就弹框是选择图片还是拍照
         $(".tankuang").css("display", "block");
     } else {
         //如果是苹果系统，就还照之前的样子去操作即可
         //$($("#pictureA_file")[0]).click();
     }
   
		    $('#pictureC_file').change(function (e) {
		    	//$('#lefile').files;
		    	console.log(e);
		    	var fileDom = $('#pictureC_file').get(0);
				var img = $('#imageContent');
//				var fileMsg = e.currentTarget.files;
//			    var fileType = fileMsg[0].type;
//			    console.log(fileType);
				if (fileDom&&img) {
					fileHandle(fileDom,img);
				}
//			        var fr = new FileReader();
//			        fr.readAsDataURL(file);
//			        fr.onload = function(){
//			            $('#avatar-img').attr('src',fr.result)
//
//			        }
			})
		
     function fileHandle(fileDom,img) {
		//读取计算机文件
		    	//"multipart/form-data"
    	 var file = fileDom.files[0];
    	 var formData  =new FormData();
    	 formData.append('file',file);
    	 
//    	 var form = document.getElementById('uploadForm');
// 		var formData = new FormData(form);
    	console.log(formData);
    	 var reader = new FileReader();
    	 reader.readAsDataURL(file);
    	
    	 reader.onloadstart = function(){
    		
    		// console.log(reader.result);
//    		 console.log(reader);
    	 
     	 };
    	 reader.onload = function(e){
    		 
    		 var result = reader.result;
    	 	 $.ajax({ 
    	 		   url:'../test/QrCode.do', 
     	 		   type:'POST',
    	 		   async : false,
     	 		   data:formData, 
     	 		   processData:false, 
     			   contentType:false, 
     	 		   success:function(res){ 
     	 			     //vaRetrun=res;
    	 			   alert(res);
     	 			 $("#showLabel").text(res);
     	 			   }, 
     	 			   error:function(err){ 
     	 			    alert("NG:"+err); 
     	 			     
    	 			   } 
     	 			  
     	 			  }) 
    		 img.attr('src',reader.result);
    	 };
	}	    	  
	})