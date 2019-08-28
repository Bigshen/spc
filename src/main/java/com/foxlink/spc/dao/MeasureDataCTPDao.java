package com.foxlink.spc.dao;

import com.foxlink.spc.pojo.MeasureDataCTPRequireInfo;
import com.foxlink.spc.pojo.MeasureDataCTPResultInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("MeasureDataCTPDao")
public class MeasureDataCTPDao {
    private static Logger logger = Logger.getLogger(MeasureDataCTPDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MeasureDataCTPResultInfo> ShowMeasureDataCTPResult(String StrFactory,String StrLine,String StrPartNumberV,String date,String StrProjectName,String StrTestType) {
        String sql ="SELECT DATETIME,WORKSHOP,PERIOD,WORKSHOP_NAME,\n" +
                "INSPECTION_ITEM,MACHINE_NAME,MACHINE_TYPE,REMARK1,\n" +
                "UPPER_DIM,LOWER_DIM,MEASURE_VALUE,MEASURE_RESULT,\n" +
                "BATCH_REMARK,PERSONNEL_ID \n" +
                "from SPC.MEASURE_DATA_CTP  "+
                 "WHERE FACTORY='"+StrFactory+"' AND LINE_NUMBER='"+StrLine+"'" +
                 " AND PROJECT_NAME='"+StrProjectName+"' AND PART_NUMBER_V='"+StrPartNumberV+"' AND to_char(DATETIME,'yyyy-MM-dd')='"+date+"' AND INSPECTION_TYPE='"+StrTestType+"'";


        List<MeasureDataCTPResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<MeasureDataCTPResultInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataCTPResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataCTPDao-->ShhowMeasureDataCTPResult方法異常：" + e);
        }
        return list_dcResult;
    } //KAPPA測試

    public List<MeasureDataCTPRequireInfo> FactoryDropdownBoxIf(String StrFactory) {
        String sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA_CTP md WHERE md.Factory='"+StrFactory+"' ORDER BY LINE_NUMBER ASC";
        List<MeasureDataCTPRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataCTPRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataCTPRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataCTPDao-->DropdownBoxIf方法異常：" + e);
        }
        return list_dci;
    } //廠區下拉框點擊條件查詢

    public List<MeasureDataCTPRequireInfo> ShowDay(String StrFactory,String StrLine,String StrPartNumberV,String date,String proName) {
        String sql = "";
        List<MeasureDataCTPRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataCTPRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataCTPRequireInfo.class);
            sql = "SELECT DISTINCT (TO_CHAR(DATETIME,'yyyy-MM-dd')) DATETIME \n" +
                    "FROM SPC.MEASURE_DATA_CTP md\n" +
                    " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' AND md.PART_NUMBER_V='"+StrPartNumberV+"'\n" +
                    "AND TO_CHAR(DATETIME,'yyyy-MM')='"+date+"' AND PROJECT_NAME='"+proName+"' ORDER BY DATETIME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataCTPDao-->ShowDay方法異常：" + e);
        }
        return list_dci;
    }//加載日期下拉框值

    public List<MeasureDataCTPRequireInfo> ShowFactory() {
        String sql = "";
        List<MeasureDataCTPRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataCTPRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataCTPRequireInfo.class);
            sql = "SELECT distinct(FACTORY) FROM SPC.MEASURE_DATA_CTP WHERE 1=1 ORDER BY Factory ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataCTPDao-->ShowFactory方法異常：" + e);
        }
        return list_dci;
    }//加載廠區下拉框值

    public List<MeasureDataCTPRequireInfo> ShowLine() {
        String sql = "";
        List<MeasureDataCTPRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataCTPRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataCTPRequireInfo.class);
            sql = "SELECT distinct(LINE_NUMBER) FROM SPC.MEASURE_DATA_CTP WHERE 1=1 ORDER BY LINE_NUMBER ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataCTPDao-->ShowLine方法異常：" + e);
        }
        return list_dci;
    }//加載綫別下拉框值

    public List<MeasureDataCTPRequireInfo> ShowProjectName() {
        String sql = "";
        List<MeasureDataCTPRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataCTPRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataCTPRequireInfo.class);
            sql = "SELECT distinct(PROJECT_NAME) FROM SPC.MEASURE_DATA_CTP WHERE 1=1 ORDER BY PROJECT_NAME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataCTPDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }//加載專案名稱下拉框值
    public List<MeasureDataCTPRequireInfo> ShowProjectName(String StrFactory,String StrLine) {
        String sql = "SELECT DISTINCT(PROJECT_NAME)\n" +
                "FROM SPC.MEASURE_DATA_CTP md\n" +
                " WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' ORDER BY md.PROJECT_NAME ASC";
        List<MeasureDataCTPRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataCTPRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataCTPRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataCTPDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }

    public List<MeasureDataCTPRequireInfo> ShowPartNumberV() {
        String sql = "";
        List<MeasureDataCTPRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataCTPRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataCTPRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA_CTP WHERE 1=1 ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataCTPDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }//加載料號版本下拉框值
    public List<MeasureDataCTPRequireInfo> ShowPartNumberV(String StrFactory,String StrLine) {
        String sql = "";
        List<MeasureDataCTPRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<MeasureDataCTPRequireInfo> mapper = new BeanPropertyRowMapper<>(MeasureDataCTPRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.MEASURE_DATA_CTP md WHERE md.FACTORY='"+StrFactory+"' AND md.LINE_NUMBER='"+StrLine+"' ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("MeasureDataCTPDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }
}
