package com.foxlink.spc.controller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.google.gson.JsonObject;

import com.foxlink.spc.service.testService;
@CrossOrigin(origins = "*", maxAge = 60)
@Controller
@RequestMapping("/test")
public class testController {
	@Resource
	private testService testService;
    private static Logger logger = Logger.getLogger(LinkManageController.class);
    
	@RequestMapping(value="/test.show",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String test(HttpSession session,@RequestParam("username")String username){
		JsonObject exception=new JsonObject();
		System.out.println("spring mvc配置成功了"+username);
		//ApplicationContext context = new FileSystemXmlApplicationContext(this.getClass().getClassLoader().getResource("").getPath()+"/spring/applicationContext.xml");
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		testService = (testService) context.getBean("testService");
		testService.test();
		exception.addProperty("StatusCode", "200");
		exception.addProperty("Messages", "成功返回数据");
		return exception.toString();
	}
	
	@RequestMapping(value="/testjsp.show",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	public String testjsp(HttpSession session){
		return "test";
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String admin(HttpSession session){
		return "登陆成功";
	}
	
//	@RequestMapping(value="/ShowLinkManage",method=RequestMethod.GET)
//	public String ShowAllAccountPage(){
//		return "CTPFileSelect";
//	}
//	
	@RequestMapping(value="/testdb",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String testdb(HttpSession session,@RequestParam("id")String id){
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		testService = (testService) context.getBean("testService");
		System.out.println(id);
		String result = testService.getEmp(id);
		return result;
	}
	
	@RequestMapping(value="/Login",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String Login(HttpSession session,@RequestParam("userName")String userName,@RequestParam("Password")String Password){

		JsonObject exception=new JsonObject();
	
		try{
			
			if(testService.CheckAccount(userName, Password)){
				exception.addProperty("StatusCode", "200");
				exception.addProperty("Message", "登錄成功");
			}
			else{
				exception.addProperty("StatusCode", "500");
				exception.addProperty("Message", "賬戶或密碼錯誤");
			}
		}
		catch(Exception ex){
			logger.error("Disable the IOCardMaIP info is failed, due to:",ex);
			exception.addProperty("StatusCode", "500");
			exception.addProperty("Message", "登錄失敗，原因:"+ex.toString());
		}		
		return exception.toString();
		
	}
	//二维码解析
	@RequestMapping(value="/QrCode.do",method=RequestMethod.POST)
	
	public @ResponseBody String QrCode(HttpSession session,MultipartFile file)  {

		//String base64string = Request["files"];
		String filePath = "";
		File targetFile = null;
		try {
			 System.out.println("进入方法===="+file);
			    String fileName = file.getOriginalFilename();
				
				 filePath = "D:/ExcelBack/Spec/CTP/"+fileName;// 存储到服务器上的路径.
				
				
				targetFile = new File(filePath);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				// 将前台传过来的file文件写到targetFile中.		
				file.transferTo(targetFile);
			  
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
		 // String aString = "A";
		return testService.ErWeiCode(targetFile);
	     
		
		
	}

}
