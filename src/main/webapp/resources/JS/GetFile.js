$(document).ready(function(){
	$('input[id=lefile]').change(function() {
		var file = $(this).val();
		var fileName = file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
	    var fileExt=file.replace(/.+\./,"");   //正则表达式获取后缀
	    /*var reg = /.+//(/w+)/./w+/g;
		var fileName = file.replace(/.+//(/w+)/./w+/g,"$1");
		$('.file-text').val(fi*/
		$('.file-text').val(fileName+'.'+fileExt);
	});
})
