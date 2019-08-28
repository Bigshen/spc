package com.foxlink.spc.pojo;

public class MeasureDataCTPRequireInfo {
    private String FACTORY;//廠區
    private String LINE_NUMBER;//綫別
    private String PROJECT_NAME;//專案名稱
    private String PART_NUMBER_V;//料號版本
    private String DATETIME;//天數
    private String INSPECTION_ITEM;//類型

    public String getFACTORY() {
        return FACTORY;
    }

    public void setFACTORY(String FACTORY) {
        this.FACTORY = FACTORY;
    }

    public String getLINE_NUMBER() {
        return LINE_NUMBER;
    }

    public void setLINE_NUMBER(String LINE_NUMBER) {
        this.LINE_NUMBER = LINE_NUMBER;
    }

    public String getPROJECT_NAME() {
        return PROJECT_NAME;
    }

    public void setPROJECT_NAME(String PROJECT_NAME) {
        this.PROJECT_NAME = PROJECT_NAME;
    }

    public String getPART_NUMBER_V() {
        return PART_NUMBER_V;
    }

    public void setPART_NUMBER_V(String PART_NUMBER_V) {
        this.PART_NUMBER_V = PART_NUMBER_V;
    }

    public String getDATETIME() {
        return DATETIME;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }

    public String getINSPECTION_ITEM() {
        return INSPECTION_ITEM;
    }

    public void setINSPECTION_ITEM(String INSPECTION_ITEM) {
        this.INSPECTION_ITEM = INSPECTION_ITEM;
    }
}
