package com.foxlink.spc.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Var;
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
import com.foxlink.spc.service.UploadCTPService;
import com.foxlink.spc.service.UploadSpcDataService;


@Controller
@RequestMapping("/UploadSpcData")
public class UploadSpcDataController {
	private static Logger logger = Logger.getLogger(UploadSpcCTPController.class);
	private ApplicationContext context=new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
	private UploadSpcDataService uploadSpcDataService;
	
	@RequestMapping(value="/ShowSpcDataUp",method=RequestMethod.GET)
	public String ShowAllAccountPage(){
		return "SPCDataUpload";
	}
	
	@RequestMapping(value="/ajaxUploadSpcData.do",method=RequestMethod.POST)
	public @ResponseBody String ajaxUploadSpecCTP(HttpSession session,MultipartFile[] file ) {
		SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		String strUserName =  ((UserDetails)securityContext.getAuthentication().getPrincipal()).getUsername();
		uploadSpcDataService=(UploadSpcDataService)context.getBean("UploadSpcDataService");
		//return strUserName;
		return uploadSpcDataService.uploadOK(file,strUserName);
	}
	
	@RequestMapping("/checkSPCData.do")
	public @ResponseBody String checkSPCData(MultipartFile[] file )throws IOException {
		uploadSpcDataService=(UploadSpcDataService)context.getBean("UploadSpcDataService");	
	
		//System.out.println(uploadSpcDataService.checkSPCData(file));
		return uploadSpcDataService.checkSPCData(file);
	}
}
