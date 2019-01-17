package com.foxlink.spc.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.foxlink.spc.service.UploadAllService;

@RequestMapping("/uploadSpec")
@Controller
public class UploadSpecController {
	private static Logger logger=Logger.getLogger(UploadSpecController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private UploadAllService uploadAllService;
	
	@RequestMapping("/uploadSpec.show")
	public String ShowUploadSpec() {
		return "UploadSpec";
	}
	@RequestMapping(value="/ajaxUploadSpec.do",method=RequestMethod.POST)
	public @ResponseBody String ajaxUploadSpec(HttpSession session,MultipartFile file ) {
		SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		String strUserName =  ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
		uploadAllService=(UploadAllService)context.getBean("UploadAllService");
		return	uploadAllService.uploadOK(file,strUserName);
	}
	@RequestMapping("/checkPartNumber.do")
	public @ResponseBody String checkPartNumber(@RequestParam(value="str2V")String str2V )throws IOException {
		uploadAllService=(UploadAllService)context.getBean("UploadAllService");	
	return	uploadAllService.CheckPartNumber(str2V);
		
	}
	
	

}
