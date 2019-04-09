package com.foxlink.spc.controller;

import com.foxlink.spc.pojo.EleScaleMeasure;
import com.foxlink.spc.pojo.SaveMeasure;
import com.foxlink.spc.service.EleScaleMeasureService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/EleScaleMeasure")
public class EleScaleMeasureController {
    private static Logger logger = Logger.getLogger(LinkManageController.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
    private EleScaleMeasureService EleScaleMeasureService;

    @RequestMapping(value = "/ShowEleScaleMeasure", method = RequestMethod.GET)
    public String ShowPage() {
        return "EleScaleMeasure";
    }

//    @RequestMapping(value = "/QuerySPEC", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    @ResponseBody
//    public String QuerySPEC() {
//        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
//        return EleScaleMeasureService.QuerySPEC();
//    }


    @RequestMapping(value = "/ShowEleScaleMeasureResult", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
//@RequestParam("Factory") String Factory,@RequestParam("LINE_NUMBER") String Line,@RequestParam("ProName") String ProName,@RequestParam("PartVerion") String PartVerion,@RequestParam("MeasureStatus") String MeasureStatus,@RequestParam("MeasureFrequency") String MeasureFrequency,@RequestParam("JieCi") String JieCi,@RequestParam("TicketNumber") String TicketNumber,@RequestParam("MachineNum") String MachineNum,@RequestParam("MeasureEmpID") String MeasureEmpID
    public String ShowEleScaleMeasureResult(SaveMeasure saveMeasure,HttpSession session) {
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        session.setAttribute("wanhaoMeasure", saveMeasure);
        System.out.println(saveMeasure);
        return EleScaleMeasureService.ShowEleScaleMeasureResult(saveMeasure);
        //Factory,Line,ProName,PartVerion,MeasureStatus,MeasureFrequency,JieCi,TicketNumber,MachineNum,MeasureEmpID
    }

    @RequestMapping(value = "/FactoryDropdownBoxIf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String FactoryDropdownBoxIf(@RequestParam("Factory") String Factory) {
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        System.out.println(Factory);
        return EleScaleMeasureService.FactoryDropdownBoxIf(Factory);
    }//廠區下拉框事件

    @RequestMapping(value = "/MeasureStatusDropdownBoxIf", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String MeasureStatusDropdownBoxIf(@RequestParam("ProName") String ProName,@RequestParam("PartVerion") String partVerion,@RequestParam("MeasureStatus") String status) {
       System.out.println(ProName+"    "+partVerion+"    "+status);
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        return EleScaleMeasureService.MeasureStatusDropdownBoxIf(partVerion,status,ProName);
    }//量測狀態下拉框事件




    @RequestMapping(value = "/ShowDropdownBoxFactory", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxFactory() {
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        return EleScaleMeasureService.ShowDropdownBoxFactory();
    }//查询廠區下拉框值

    @RequestMapping(value = "/ShowDropdownBoxLine", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxLine() {
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        return EleScaleMeasureService.ShowDropdownBoxLine();
    }//綫別

    @RequestMapping(value = "/ShowDropdownBoxProjectName", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxProjectName() {
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        return EleScaleMeasureService.ShowDropdownBoxProjectName();
    }//專案名稱


    @RequestMapping(value = "/ShowDropdownBoxPartNumber", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumber() {
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        return EleScaleMeasureService.ShowDropdownBoxPartNumberV();
    }//料號版本

    @RequestMapping(value = "/ShowDropdownBoxPartNumbers", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxPartNumbers(@RequestParam("ProName") String ProName) {
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        return EleScaleMeasureService.ShowDropdownBoxPartNumberV(ProName);
    }

    @RequestMapping(value = "/ShowDropdownBoxStatus", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxStatus() {
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        return EleScaleMeasureService.ShowDropdownBoxStatus();
    }//測量階段

    @RequestMapping(value = "/ShowDropdownBoxStatuss", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String ShowDropdownBoxStatuss(@RequestParam("PartVerion") String PartVerion) {
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        return EleScaleMeasureService.ShowDropdownBoxStatus(PartVerion);
    }

    @RequestMapping(value="/btnMeasureTable",method=RequestMethod.POST,produces="application/json;charset=utf-8")
    @ResponseBody
    public String btnMeasureTable(@RequestBody EleScaleMeasure[] faultList, HttpSession session){
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        SaveMeasure saveMeasure = (SaveMeasure) session.getAttribute("wanhaoMeasure");
        System.out.println(faultList.length);
        for(int i = 0 ;i<faultList.length;i++){
//            System.out.println(faultList[i].getADim1_array().get(3)+faultList[i].getResult());
            System.out.println(faultList[i]);
        }
        System.out.println(saveMeasure);
        return EleScaleMeasureService.sumbitMeasureTable(faultList,saveMeasure);
    }

    @RequestMapping(value="/insertMeaTable",method=RequestMethod.POST,produces="application/json;charset=utf-8")
    @ResponseBody
    public String insertMeaTable(@RequestBody EleScaleMeasure[] faultList, HttpSession session){
        EleScaleMeasureService = (EleScaleMeasureService) context.getBean("EleScaleMeasureService");
        SaveMeasure saveMeasure = (SaveMeasure) session.getAttribute("wanhaoMeasure");
        System.out.println(faultList.length);
        for(int i = 0 ;i<faultList.length;i++){
            System.out.println(faultList[i]);
        }
        System.out.println(saveMeasure);
        return EleScaleMeasureService.insertMeaTable(faultList,saveMeasure);
    }


}
