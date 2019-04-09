package com.foxlink.spc.service;

import com.foxlink.spc.dao.EleScaleMeasureDao;
import com.foxlink.spc.pojo.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("EleScaleMeasureService")
public class EleScaleMeasureService {
    private static Logger logger = Logger.getLogger(EleScaleMeasureService.class);
    private EleScaleMeasureDao EleScaleMeasureDao;

    @Autowired
    public void setInkManageDao(EleScaleMeasureDao EleScaleMeasureDao) {
        this.EleScaleMeasureDao = EleScaleMeasureDao;
    }

    public String ShowEleScaleMeasureResult(SaveMeasure saveMeasure) {
        JsonObject result = new JsonObject();
        List<EleScaleMeasureResultInfo> list_dcResult = EleScaleMeasureDao.ShowEleScaleMeasureResult(saveMeasure);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public String FactoryDropdownBoxIf(String StrFactory) {
        JsonObject result = new JsonObject();
        List<EleScaleMeasureRequireInfo> dci_list = EleScaleMeasureDao.FactoryDropdownBoxIf(StrFactory);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (dci_list.size() == 0 || dci_list == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(dci_list));
        }
        System.out.println(result.toString());
        return result.toString();
    }//廠區下拉框事件server

    public String MeasureStatusDropdownBoxIf(String StrPartNumberV,String StrStatus,String ProName) {
        System.out.println(ProName+"    "+StrPartNumberV+"    "+StrStatus);
        JsonObject result = new JsonObject();
        List<EleScaleMeasureRequireInfo> dci_list = EleScaleMeasureDao.MeasureStatusDropdownBoxIf(StrPartNumberV,StrStatus,ProName);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (dci_list.size() == 0 || dci_list == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(dci_list));
        }
        System.out.println(result.toString());
        return result.toString();
    }//量測狀態下拉框事件server

    public String ShowDropdownBoxFactory() {
        JsonObject result = new JsonObject();
        List<EleScaleMeasureRequireInfo> list_dcResult = EleScaleMeasureDao.ShowFactory();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxFactory-->廠區查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取廠區下拉框值

    public String ShowDropdownBoxLine() {
        JsonObject result = new JsonObject();
        List<EleScaleMeasureRequireInfo> list_dcResult = EleScaleMeasureDao.ShowLine();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxLine-->綫別查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取綫別

    public String ShowDropdownBoxProjectName() {
        JsonObject result = new JsonObject();
        List<EleScaleMeasureRequireInfo> list_dcResult = EleScaleMeasureDao.ShowProjectName();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxProjectName-->專案名稱查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取專案名稱

    public String ShowDropdownBoxPartNumberV() {
        JsonObject result = new JsonObject();
        List<EleScaleMeasureRequireInfo> list_dcResult = EleScaleMeasureDao.ShowPartNumberV();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxPartNumberV-->料號版本查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取料號版本
    public String ShowDropdownBoxPartNumberV(String ProName ) {
        JsonObject result = new JsonObject();
        List<EleScaleMeasureRequireInfo> list_dcResult = EleScaleMeasureDao.ShowPartNumberV(ProName);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxPartNumberV-->料號版本查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取料號版本

    public String ShowDropdownBoxStatus() {
        JsonObject result = new JsonObject();
        List<EleScaleMeasureRequireInfo> list_dcResult = EleScaleMeasureDao.ShowStatus();
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxStatus-->量測階段查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取測量階段
    public String ShowDropdownBoxStatus(String PartVerion) {
        JsonObject result = new JsonObject();
        List<EleScaleMeasureRequireInfo> list_dcResult = EleScaleMeasureDao.ShowStatus(PartVerion);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxStatus-->量測階段查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取測量階段

    public String sumbitMeasureTable(EleScaleMeasure[] faultList, SaveMeasure saveMeasure) {

        List<SPECMeasure> SPECMeasure = EleScaleMeasureDao.QuerySPEC(saveMeasure);
         int deleteResult = EleScaleMeasureDao.deleteMeasureTable(faultList, saveMeasure);
        JsonObject resultJson = new JsonObject();
        if(deleteResult==0){
        if (0 == 0) {
            int result = EleScaleMeasureDao.sumbitMeasureTable(faultList, saveMeasure, SPECMeasure);
            if (result == 0) {
                resultJson.addProperty("SPECStatus", "200");
                resultJson.addProperty("Message", "量測數據提交成功");
            } else {
                resultJson.addProperty("SPECStatus", "500");
                resultJson.addProperty("Message", "量測數據提交失敗");
            }
        } else {
            resultJson.addProperty("SPECStatus", "500");
            resultJson.addProperty("Message", "量測數據提交失敗,修改原量測數據失敗");
        }
        }else{
            resultJson.addProperty("SPECStatus", "500");
            resultJson.addProperty("Message", "量測數據提交失敗,修改原量測數據失敗");
        }

        return resultJson.toString();
    }

    public String insertMeaTable(EleScaleMeasure[] faultList, SaveMeasure saveMeasure) {

        List<SPECMeasure> SPECMeasure = EleScaleMeasureDao.QuerySPEC(saveMeasure);
        int deleteResult = EleScaleMeasureDao.deleteMeasureTable1(faultList, saveMeasure);
        JsonObject resultJson = new JsonObject();
        if(deleteResult==0){
        if (0 == 0) {
            int result = EleScaleMeasureDao.insertMeaTable(faultList, saveMeasure, SPECMeasure);
            if (result == 0) {
                resultJson.addProperty("SPECStatus", "200");
                resultJson.addProperty("Message", "量測數據1提交成功");
            } else {
                resultJson.addProperty("SPECStatus", "500");
                resultJson.addProperty("Message", "量測數據1提交失敗");
            }
            } else {
              resultJson.addProperty("SPECStatus", "500");
                resultJson.addProperty("Message", "量測數據1提交失敗,修改原量測數據失敗");
             }

            }else{
            resultJson.addProperty("SPECStatus", "500");
            resultJson.addProperty("Message", "量測數據1提交失敗,修改原量測數據失敗");
            }


        return resultJson.toString();
    }
}

