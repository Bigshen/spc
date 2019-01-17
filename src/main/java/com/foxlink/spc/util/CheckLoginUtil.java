package com.foxlink.spc.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckLoginUtil implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		System.out.println("======"+requestURI+"======");
		Object username =  SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		if(requestURI.equals("/spc/")){
			response.sendRedirect(request.getContextPath() + "/index");
			return false;
		}
		if(username == null || username.equals("anonymousUser")){
			if(request.getHeader("x-requested-with") != null&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
				//如果是ajax请求响应头会有，x-requested-with；
                response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
			}else{
				response.sendRedirect(request.getContextPath() + "/Login");
			}
			    return false;
		}
		return true;
	}

}
