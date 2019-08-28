package com.foxlink.spc.controller;

import com.foxlink.spc.service.MeasureDataCTPService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/MeasureDataCTP")
public class MeasureDataCTPController {
    private static Logger logger = Logger.getLogger(LinkManageController.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
    private MeasureDataCTPService MeasureDataCTPService;

    @RequestMapping(value = "/ShowMeasureDataCTP", method = RequestMethod.GET)
    public String ShowPage() {
        return "MesDataSelectCTP";
    }


    @RequestMapping(value = "/ShowMeasureDataCTPResult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowMeasureDataCTPResult(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("DATETIME") String date,@RequestParam("PROJECT_NAME") String StrProjectName,@RequestParam("TestType") String testType) {
        MeasureDataCTPService = (MeasureDataCTPService) context.getBean("MeasureDataCTPService");
        return MeasureDataCTPService.ShowMeasureDataCTPResult(Factory,Line,partVerion,date,StrProjectName,testType);
    }


    @RequestMapping(value = "/FactoryDropdownBoxIf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String FactoryDropdownBoxIf(@RequestParam("Factory") String Factory) {
        MeasureDataCTPService = (MeasureDataCTPService) context.getBean("MeasureDataCTPService");
        System.out.println(Factory);
        return MeasureDataCTPService.FactoryDropdownBoxIf(Factory);
    }

    @RequestMapping(value = "/ShowDropdownBoxDay", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxDay(@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("PART_NUMBER_V") String partVerion,@RequestParam("DATETIME") String date,@RequestParam("PROJECT_NAME") String proName) {
        MeasureDataCTPService = (MeasureDataCTPService) context.getBean("MeasureDataCTPService");
        return MeasureDataCTPService.ShowDropdownBoxDay(Factory,Line,partVerion,date,proName);
    }//天數
    @RequestMapping(value = "/ShowDropdownBoxFactory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxFactory() {
        MeasureDataCTPService = (MeasureDataCTPService) context.getBean("MeasureDataCTPService");
        return MeasureDataCTPService.ShowDropdownBoxFactory();
    }//廠區

    @RequestMapping(value = "/ShowDropdownBoxLine", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxLine() {
        MeasureDataCTPService = (MeasureDataCTPService) context.getBean("MeasureDataCTPService");
        return MeasureDataCTPService.ShowDropdownBoxLine();
    }//綫別

    @RequestMapping(value = "/ShowDropdownBoxProjectName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectName() {
        MeasureDataCTPService = (MeasureDataCTPService) context.getBean("MeasureDataCTPService");
        return MeasureDataCTPService.ShowDropdownBoxProjectName();
    }//專案名稱

    @RequestMapping(value = "/ShowDropdownBoxProjectNames", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectNames(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line) {
        MeasureDataCTPService = (MeasureDataCTPService) context.getBean("MeasureDataCTPService");
        return MeasureDataCTPService.ShowDropdownBoxProjectName(Factory, Line);
    }

    @RequestMapping(value = "/ShowDropdownBoxPartNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumber() {
        MeasureDataCTPService = (MeasureDataCTPService) context.getBean("MeasureDataCTPService");
        return MeasureDataCTPService.ShowDropdownBoxPartNumberV();
    }//料號版本

    @RequestMapping(value = "/ShowDropdownBoxPartNumbers", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumbers(@RequestParam("Factory") String Factory, @RequestParam("LINE_NUMBER") String Line) {
        MeasureDataCTPService = (MeasureDataCTPService) context.getBean("MeasureDataCTPService");
        return MeasureDataCTPService.ShowDropdownBoxPartNumberV(Factory, Line);
    }

}
