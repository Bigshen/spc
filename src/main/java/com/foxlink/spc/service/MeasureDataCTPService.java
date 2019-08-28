package com.foxlink.spc.service;

import com.foxlink.spc.dao.MeasureDataCTPDao;
import com.foxlink.spc.pojo.MeasureDataCTPRequireInfo;
import com.foxlink.spc.pojo.MeasureDataCTPResultInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("MeasureDataCTPService")
public class MeasureDataCTPService {
    private static Logger logger = Logger.getLogger(MeasureDataCTPService.class);
    private MeasureDataCTPDao MeasureDataCTPDao;

    @Autowired
    public void setInkManageDao(MeasureDataCTPDao MeasureDataCTPDao) {
        this.MeasureDataCTPDao = MeasureDataCTPDao;
    }



    public String ShowMeasureDataCTPResult(String StrFactory,String StrLine,String StrPartNumberV,String date,String StrProjectName,String StrTestType) {
        JsonObject result = new JsonObject();
        List<MeasureDataCTPResultInfo> list_dcResult = MeasureDataCTPDao.ShowMeasureDataCTPResult(StrFactory,StrLine,StrPartNumberV,date,StrProjectName,StrTestType);
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
    }//KAPPA測試


    public String FactoryDropdownBoxIf(String StrFactory) {
        JsonObject result = new JsonObject();
        List<MeasureDataCTPRequireInfo> dci_list = MeasureDataCTPDao.FactoryDropdownBoxIf(StrFactory);
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
    }

    public String ShowDropdownBoxDay(String StrFactory,String StrLine,String StrPartNumberV,String date,String proName) {
        JsonObject result = new JsonObject();
        List<MeasureDataCTPRequireInfo> list_dcResult = MeasureDataCTPDao.ShowDay(StrFactory,StrLine,StrPartNumberV,date,proName);
        Gson gson = new GsonBuilder().serializeNulls().create();
        if (list_dcResult.size() == 0 || list_dcResult == null) {
            result.addProperty("StatusCode", "500");
            result.addProperty("message", "ShowDropdownBoxDay-->天數查無數據");
        } else {
            result.addProperty("StatusCode", "200");
            result.addProperty("message", gson.toJson(list_dcResult));
        }
        System.out.println(result.toString());
        return result.toString();
    }//獲取天數

    public String ShowDropdownBoxFactory() {
        JsonObject result = new JsonObject();
        List<MeasureDataCTPRequireInfo> list_dcResult = MeasureDataCTPDao.ShowFactory();
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
    }//獲取廠區

    public String ShowDropdownBoxLine() {
        JsonObject result = new JsonObject();
        List<MeasureDataCTPRequireInfo> list_dcResult = MeasureDataCTPDao.ShowLine();
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
        List<MeasureDataCTPRequireInfo> list_dcResult = MeasureDataCTPDao.ShowProjectName();
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
    public String ShowDropdownBoxProjectName(String StrFactory, String StrLine) {
        JsonObject result = new JsonObject();
        List<MeasureDataCTPRequireInfo> list_dcResult = MeasureDataCTPDao.ShowProjectName(StrFactory,StrLine);
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
    }

    public String ShowDropdownBoxPartNumberV() {
        JsonObject result = new JsonObject();
        List<MeasureDataCTPRequireInfo> list_dcResult = MeasureDataCTPDao.ShowPartNumberV();
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
    public String ShowDropdownBoxPartNumberV(String StrFactory,String StrLine) {
        JsonObject result = new JsonObject();
        List<MeasureDataCTPRequireInfo> list_dcResult = MeasureDataCTPDao.ShowPartNumberV(StrFactory,StrLine);
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
}

