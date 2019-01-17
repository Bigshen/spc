package com.foxlink.spc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.foxlink.spc.service.testService;

@Controller
@RequestMapping("/test")
public class testController {
	
	private testService testService;

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
	
	@RequestMapping(value="/ShowLinkManage",method=RequestMethod.GET)
	public String ShowAllAccountPage(){
		return "CTPFileSelect";
	}
	
	@RequestMapping(value="/testdb",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String testdb(HttpSession session,@RequestParam("id")String id){
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
		testService = (testService) context.getBean("testService");
		System.out.println(id);
		String result = testService.getEmp(id);
		return result;
	}
}
