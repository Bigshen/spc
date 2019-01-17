/** 
     * 设置未来(全局)的AJAX请求默认选项 
     * 主要设置了AJAX请求遇到Session过期的情况 
     */  
      
    $.ajaxSetup({  
      type: 'POST',  
      contentType:"application/x-www-form-urlencoded;charset=utf-8",  
      complete: function(xhr,status) {  
        var sessionStatus = xhr.getResponseHeader('sessionstatus');  
        if(sessionStatus == 'timeout') {  
          var top = getTopWinow();  
          //var yes = confirm('由于您长时间没有操作, session已过期, 请重新登录.');  
          //if (yes) {  
            alert("登录超时,请重新登录！");  
            top.window.location.href = '../Login';  
          //}  
        }  
      }  
    });  
      
    /** 
    * 在页面中任何嵌套层次的窗口中获取顶层窗口 
    * @return 当前页面的顶层窗口对象 
    */  
      
    function getTopWinow(){  
      var p = window;  
      while(p != p.parent){  
        p = p.parent;  
      }  
      return p;  
    }   