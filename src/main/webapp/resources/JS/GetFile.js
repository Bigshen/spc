$(document).ready(function(){
	$('input[id=lefile]').change(function() {
		/*var file = $(this).val();
		var fileName = file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
	    var fileExt=file.replace(/.+\./,"");   //正则表达式获取后缀
		$('.file-text').val(fileName+'.'+fileExt);*/
		var fileName = $('#lefile').get(0).files;
		var fileText ="";
		for(var i=0;i<fileName.length;i++){
			fileText+=fileName[i].name+",";
			//var a = fileName.file[i];
		}
		//console.log(fileText);
		$('.file-text').val(fileText)
		/*alert($('#lefile').val());*/
	});
});
