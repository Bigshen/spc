package com.foxlink.spc.pojo;

public class MeasureDataCTPResultInfo {
    private String DATETIME;//時間
    private String WORKSHOP;//工站號
    private String PERIOD;//節次
    private String WORKSHOP_NAME;//工站名稱
    private String INSPECTION_ITEM;//檢驗項目
    private String MACHINE_NAME;//機臺名稱
    private String MACHINE_TYPE;//機臺類型
    private String REMARK1;//備注
    private String UPPER_DIM;//上限
    private String LOWER_DIM;//下綫
    private String MEASURE_VALUE;//量測值
    private String MEASURE_RESULT;//量測結果
    private String BATCH_REMARK;//檢驗備注
    private String PERSONNEL_ID;//工號


    public String getDATETIME() {
        return DATETIME;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }

    public String getWORKSHOP() {
        return WORKSHOP;
    }

    public void setWORKSHOP(String WORKSHOP) {
        this.WORKSHOP = WORKSHOP;
    }

    public String getPERIOD() {
        return PERIOD;
    }

    public void setPERIOD(String PERIOD) {
        this.PERIOD = PERIOD;
    }

    public String getWORKSHOP_NAME() {
        return WORKSHOP_NAME;
    }

    public void setWORKSHOP_NAME(String WORKSHOP_NAME) {
        this.WORKSHOP_NAME = WORKSHOP_NAME;
    }

    public String getINSPECTION_ITEM() {
        return INSPECTION_ITEM;
    }

    public void setINSPECTION_ITEM(String INSPECTION_ITEM) {
        this.INSPECTION_ITEM = INSPECTION_ITEM;
    }

    public String getMACHINE_NAME() {
        return MACHINE_NAME;
    }

    public void setMACHINE_NAME(String MACHINE_NAME) {
        this.MACHINE_NAME = MACHINE_NAME;
    }

    public String getMACHINE_TYPE() {
        return MACHINE_TYPE;
    }

    public void setMACHINE_TYPE(String MACHINE_TYPE) {
        this.MACHINE_TYPE = MACHINE_TYPE;
    }

    public String getREMARK1() {
        return REMARK1;
    }

    public void setREMARK1(String REMARK1) {
        this.REMARK1 = REMARK1;
    }

    public String getUPPER_DIM() {
        return UPPER_DIM;
    }

    public void setUPPER_DIM(String UPPER_DIM) {
        this.UPPER_DIM = UPPER_DIM;
    }

    public String getLOWER_DIM() {
        return LOWER_DIM;
    }

    public void setLOWER_DIM(String LOWER_DIM) {
        this.LOWER_DIM = LOWER_DIM;
    }

    public String getMEASURE_VALUE() {
        return MEASURE_VALUE;
    }

    public void setMEASURE_VALUE(String MEASURE_VALUE) {
        this.MEASURE_VALUE = MEASURE_VALUE;
    }

    public String getMEASURE_RESULT() {
        return MEASURE_RESULT;
    }

    public void setMEASURE_RESULT(String MEASURE_RESULT) {
        this.MEASURE_RESULT = MEASURE_RESULT;
    }

    public String getBATCH_REMARK() {
        return BATCH_REMARK;
    }

    public void setBATCH_REMARK(String BATCH_REMARK) {
        this.BATCH_REMARK = BATCH_REMARK;
    }

    public String getPERSONNEL_ID() {
        return PERSONNEL_ID;
    }

    public void setPERSONNEL_ID(String PERSONNEL_ID) {
        this.PERSONNEL_ID = PERSONNEL_ID;
    }
}
