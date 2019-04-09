package com.foxlink.spc.dao;

import com.foxlink.spc.pojo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("EleScaleMeasureDao")
public class EleScaleMeasureDao {
    private static Logger logger = Logger.getLogger(EleScaleMeasureDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List QuerySPEC(SaveMeasure saveMeasure) {
        String sql="";
        String sqlStrProName=" AND Project_Name='"+saveMeasure.getProjectName()+"'";
        if (saveMeasure.getProjectName().equals("")){ sqlStrProName=" AND 1=1"; }
         sql = "SELECT\n" +
                "Workshop,\n" +
                "Inspection_Item,\n" +
                "Inspection_Content,\n" +
                "Inspection_Method,\n" +
                "Nominal_Dim,\n" +
                "Upper_Dim,\n" +
                "Lower_Dim,\n" +
                "Frequency,\n" +
                "SPC_Num,\n" +
                "Dim_Location\n" +
                "from SPC.spec \n" +
                 "WHERE\n" +
                 "Part_Number_V='"+saveMeasure.getPartVersion()+"' AND Status='"+saveMeasure.getStatus()+"' AND Frequency='"+saveMeasure.getFrequency()+"'"+sqlStrProName;

        List<SPECMeasure> list_dcResult = new ArrayList<>();
        try {

            RowMapper<SPECMeasure> mapper = new BeanPropertyRowMapper<>(SPECMeasure.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->QuerySPEC查询异常：" + e);
        }
        return list_dcResult;
    }//查询SPEC表


    public List<EleScaleMeasureResultInfo> ShowEleScaleMeasureResult(SaveMeasure saveMeasure) {
        String sql="";
        String sqlStrProName=" AND Project_Name='"+saveMeasure.getProjectName()+"'";
        if (saveMeasure.getProjectName().equals("")){ sqlStrProName=" AND 1=1"; }
        sql = "SELECT WORKSHOP,INSPECTION_ITEM,NOMINAL_DIM,UPPER_DIM,LOWER_DIM,FREQUENCY\n" +
                "FROM SPC.SPEC \n" +
                "WHERE\n" +
                "Part_Number_V='"+saveMeasure.getPartVersion()+"' AND Status='"+saveMeasure.getStatus()+"' AND Frequency='"+saveMeasure.getFrequency()+"'"+sqlStrProName;

        List<EleScaleMeasureResultInfo> list_dcResult = new ArrayList<>();
        try {

            RowMapper<EleScaleMeasureResultInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureResultInfo.class);
            list_dcResult = jdbcTemplate.query(sql, mapper);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->ShhowEleScaleMeasureResult方法異常：" + e);
        }
        return list_dcResult;
    }//按條件查表單

    public List<EleScaleMeasureRequireInfo> MeasureStatusDropdownBoxIf(String StrPartNumberV,String StrStatus,String ProName) {
        String sqlStrProName=" AND Project_Name='"+ProName+"'";
        if (ProName.equals("")){ sqlStrProName=" AND 1=1"; }
        String sql ="SELECT DISTINCT(FREQUENCY)\n" +
                "from SPC.SPEC md \n" +
                "WHERE md.PART_NUMBER_V='"+StrPartNumberV+"'\n" +
                "AND md.STATUS='"+StrStatus+"'"+sqlStrProName+"ORDER BY FREQUENCY ASC";
        List<EleScaleMeasureRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->FrequencyDropdownBoxIf方法異常：" + e);
        }
        return list_dci;
    } //量測狀態下拉框點擊條件查詢

    public List<EleScaleMeasureRequireInfo> ShowFactory() {
        String sql = "";
        List<EleScaleMeasureRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
            sql = "SELECT distinct(FACTORY) FROM SPC.LINE_NUMBER WHERE 1=1 ORDER BY Factory ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->ShowFactory方法異常：" + e);
        }
        return list_dci;
    }//查詢廠區下拉框值
    public List<EleScaleMeasureRequireInfo> FactoryDropdownBoxIf(String StrFactory) {
        String sql = "SELECT distinct(LINE_NUMBER) FROM SPC.LINE_NUMBER md WHERE md.Factory='"+StrFactory+"' ORDER BY LINE_NUMBER ASC";
        List<EleScaleMeasureRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->FactoryDropdownBoxIf方法異常：" + e);
        }
        return list_dci;
    }//按廠區條件查詢綫別

    public List<EleScaleMeasureRequireInfo> ShowLine() {
        String sql = "";
        List<EleScaleMeasureRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
            sql = "SELECT distinct(LINE_NUMBER) FROM SPC.LINE_NUMBER WHERE 1=1 ORDER BY LINE_NUMBER ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->ShowLine方法異常：" + e);
        }
        return list_dci;
    }//查詢綫別

    public List<EleScaleMeasureRequireInfo> ShowProjectName() {
        String sql = "";
        List<EleScaleMeasureRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
            sql = "SELECT distinct(PROJECT_NAME) FROM SPC.SPEC WHERE 1=1 ORDER BY PROJECT_NAME ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->ShowProjectName方法異常：" + e);
        }
        return list_dci;
    }

    public List<EleScaleMeasureRequireInfo> ShowPartNumberV() {
        String sql = "";
        List<EleScaleMeasureRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.SPEC WHERE 1=1 ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }
    public List<EleScaleMeasureRequireInfo> ShowPartNumberV(String ProName) {
        String sql = "";
        List<EleScaleMeasureRequireInfo> list_dci = new ArrayList<>();
        if (ProName!=""){
            try {
                RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
                sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.SPEC md WHERE md.PROJECT_NAME='"+ProName+"' ORDER BY PART_NUMBER_V ASC";
                list_dci = jdbcTemplate.query(sql, mapper);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("MeasureDataDao-->ShowPartNumberV_ProName方法異常：" + e);
            }
            return list_dci;
        }
        try {
            RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
            sql = "SELECT distinct(PART_NUMBER_V) FROM SPC.SPEC md WHERE 1=1 ORDER BY PART_NUMBER_V ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->ShowPartNumberV方法異常：" + e);
        }
        return list_dci;
    }

    public List<EleScaleMeasureRequireInfo> ShowStatus() {
        String sql = "";
        List<EleScaleMeasureRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
            sql = "SELECT distinct(STATUS) FROM SPC.SPEC WHERE 1=1 ORDER BY STATUS ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->ShowStatus方法異常：" + e);
        }
        return list_dci;
    }
    public List<EleScaleMeasureRequireInfo> ShowStatus(String PartVerion) {
        String sql = "";
        List<EleScaleMeasureRequireInfo> list_dci = new ArrayList<>();
        try {
            RowMapper<EleScaleMeasureRequireInfo> mapper = new BeanPropertyRowMapper<>(EleScaleMeasureRequireInfo.class);
            sql = "SELECT distinct(STATUS) FROM SPC.SPEC md  WHERE md.PART_NUMBER_V='"+PartVerion+"' ORDER BY md.STATUS ASC";
            list_dci = jdbcTemplate.query(sql, mapper);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("EleScaleMeasureDao-->ShowStatus方法異常：" + e);
        }
        return list_dci;
    }


       public int sumbitMeasureTable(EleScaleMeasure[] faultList, SaveMeasure saveMeasure,List<SPECMeasure> sPECMeasure) {

        String sql = "INSERT INTO spc.MEASURE_DATA_BALANCE_TEMP(Project_Name,Part_Number_V,Line_Number,Ticket_Number,DateTime,Date1,Time1,"//4个字段
                + "Workshop,Inspection_Item,Inspection_Content,Inspection_Method,Nominal_Dim,Upper_Dim,Lower_Dim,Frequency,Status,Period,"//10个字段
                +" A_Measure_Value1,A_Measure_Value2,A_Measure_Value3,A_Measure_Value4,A_Measure_Value5,"
               + " B_Measure_Value1,B_Measure_Value2,B_Measure_Value3,B_Measure_Value4,B_Measure_Value5,"
                +" Measure_Value1,Measure_Value2,Measure_Value3,Measure_Value4,Measure_Value5,"
                + "MACHINE_NUMBER,FACTORY,PERSONNEL_ID,MEASURE_RESULT,REMARK1) "
                + "VALUES(?,?,?,?,sysdate,to_char(sysdate,'yyyy-mm-dd'),to_char(sysdate,'hh24:mi:ss'),"
                + "?,?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                "?,?,?,?,?)";
        int result = 0;
        try{
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    // TODO Auto-generated method stub

                    ps.setString(1, saveMeasure.getProjectName());
                    ps.setString(2,saveMeasure.getPartVersion());
                    ps.setString(3,saveMeasure.getLink());
                    ps.setString(4,saveMeasure.getTicketNumber());
                    ps.setString(5,faultList[i].getWorkShop());
                    ps.setString(6,faultList[i].getInspection_Item());
                    ps.setString(7,sPECMeasure.get(i).getInspection_Content());
                    ps.setString(8,sPECMeasure.get(i).getInspection_Method());
                    ps.setString(9,faultList[i].getNominal_Dim());
                    ps.setString(10,faultList[i].getUpper_Dim());
                    ps.setString(11, faultList[i].getLower_Dim());
                    ps.setString(12,saveMeasure.getFrequency());
                    ps.setString(13,saveMeasure.getStatus());
                    ps.setString(14,saveMeasure.getPeriod());

                    ps.setString(15, faultList[i].getADim1_array().get(0));
                    ps.setString(16,faultList[i].getADim1_array().get(1));
                    ps.setString(17, faultList[i].getADim1_array().get(2));
                    ps.setString(18,faultList[i].getADim1_array().get(3));
                    ps.setString(19,faultList[i].getADim1_array().get(4));

                    ps.setString(20, faultList[i].getBDim1_array().get(0));
                    ps.setString(21,faultList[i].getBDim1_array().get(1));
                    ps.setString(22, faultList[i].getBDim1_array().get(2));
                    ps.setString(23,faultList[i].getBDim1_array().get(3));
                    ps.setString(24,faultList[i].getBDim1_array().get(4));


                    ps.setString(25, faultList[i].getDim1_array().get(0));
                    ps.setString(26,faultList[i].getDim1_array().get(1));
                    ps.setString(27, faultList[i].getDim1_array().get(2));
                    ps.setString(28,faultList[i].getDim1_array().get(3));
                    ps.setString(29,faultList[i].getDim1_array().get(4));



                    ps.setString(30,saveMeasure.getMachineNumber());
                    ps.setString(31,saveMeasure.getFactory());
                    ps.setString(32,saveMeasure.getPersonnel_ID());
                    ps.setString(33,faultList[i].getResult());
                    ps.setString(34,faultList[i].getREMARK1());
                }

                @Override
                public int getBatchSize() {
                    // TODO Auto-generated method stub
                    return faultList.length;
                }
            });
        }catch(Exception e){
            logger.error("提交量測數據失敗，原因："+e);
            e.printStackTrace();
            result=1;
        }
        return result;
    }

        public int insertMeaTable(EleScaleMeasure[] faultList, SaveMeasure saveMeasure,List<SPECMeasure> sPECMeasure) {


        String sql = "INSERT INTO spc.measure_data(Project_Name,Part_Number_V,Line_Number,Ticket_Number,DateTime,Date1,Time1,"
                + "Workshop,Inspection_Item,Inspection_Content,Inspection_Method,Nominal_Dim,Upper_Dim,Lower_Dim,Frequency,Status,Period,"
                + "Measure_Value1,Measure_Value2,Measure_Value3,Measure_Value4,Measure_Value5,"
                + "Measure_Value6,Measure_Value7,Measure_Value8,Measure_Value9,Measure_Value10,Measure_Value11,Measure_Value12,"
                + "Measure_Value13,Measure_Value14,Measure_Value15,Measure_Value16,Measure_Value17,Measure_Value18,Measure_Value19,"
                + "Measure_Value20,Measure_Value21,Measure_Value22,Measure_Value23,Measure_Value24,Measure_Value25,Measure_Value26,"
                + "Measure_Value27,Measure_Value28,Measure_Value29,Measure_Value30,Measure_Value31,Measure_Value32,"
                + "Measure_Result,Factory,Measurement_Number,Machine_Number,Personnel_ID,Remark1,SPC_Num,Dim_Location) "
                + "VALUES(?,?,?,?,sysdate,to_char(sysdate,'yyyy-mm-dd'),to_char(sysdate,'hh24:mi:ss'),"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int result = 0;
        try{
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    // TODO Auto-generated method stub
                    ps.setString(1, saveMeasure.getProjectName());
                    ps.setString(2,saveMeasure.getPartVersion());
                    ps.setString(3,saveMeasure.getLink());
                    ps.setString(4,saveMeasure.getTicketNumber());
                    ps.setString(5,faultList[i].getWorkShop());
                    ps.setString(6,faultList[i].getInspection_Item());
                    ps.setString(7,sPECMeasure.get(i).getInspection_Content());
                    ps.setString(8,sPECMeasure.get(i).getInspection_Method());
                    ps.setString(9,faultList[i].getNominal_Dim());
                    ps.setString(10,faultList[i].getUpper_Dim());
                    ps.setString(11, faultList[i].getLower_Dim());
                    ps.setString(12,saveMeasure.getFrequency());
                    ps.setString(13,saveMeasure.getStatus());
                    ps.setString(14,saveMeasure.getPeriod());

                    ps.setString(15,faultList[i].getDim1_array().get(0));
                    ps.setString(16,faultList[i].getDim1_array().get(1));
                    ps.setString(17, faultList[i].getDim1_array().get(2));
                    ps.setString(18,faultList[i].getDim1_array().get(3));
                    ps.setString(19,faultList[i].getDim1_array().get(4));

                    ps.setString(20,"");
                    ps.setString(21,"");
                    ps.setString(22,"");
                    ps.setString(23,"");
                    ps.setString(24,"");
                                    
                    ps.setString(25,"");
                    ps.setString(26,"");
                    ps.setString(27,"");
                    ps.setString(28,"");
                    ps.setString(29,"");

                    ps.setString(30,"");
                    ps.setString(31,"");
                    ps.setString(32,"");
                    ps.setString(33,"");
                    ps.setString(34,"");

                    ps.setString(35,"");
                    ps.setString(36,"");
                    ps.setString(37,"");
                    ps.setString(38,"");
                    ps.setString(39,"");

                    ps.setString(40,"");
                    ps.setString(41,"");
                    ps.setString(42,"");
                    ps.setString(43,"");
                    ps.setString(44,"");
                    ps.setString(45,"");
                    ps.setString(46,"");



                    ps.setString(47,faultList[i].getResult());
                    ps.setString(48,saveMeasure.getFactory());
                    ps.setString(49,saveMeasure.getMeasurementNumber());
                    ps.setString(50,saveMeasure.getMachineNumber());
                    ps.setString(51,saveMeasure.getPersonnel_ID());
                    ps.setString(52,sPECMeasure.get(i).getRemark1());
                    ps.setString(53,sPECMeasure.get(i).getSPC_NUM());
                    ps.setString(54,sPECMeasure.get(i).getDIM_Location());
                }

                @Override
                public int getBatchSize() {
                    // TODO Auto-generated method stub
                    return faultList.length;
                }
            });
        }catch(Exception e){
            logger.error("提交量測數據1失敗，原因："+e);
            e.printStackTrace();
            result=1;
        }
        return result;
    }

        public int deleteMeasureTable(EleScaleMeasure[] faultList,SaveMeasure saveMeasure){
        String sql = "delete from SPC.MEASURE_DATA_BALANCE_TEMP t where Part_Number_V=? AND Status=? AND Frequency=? and period=? "
                + " and line_number=? and workshop=?  and date1 = to_char(sysdate,'yyyy-mm-dd') ";
        int result = 0;
        try{
            if(saveMeasure.getTicketNumber().equals("")||saveMeasure.getTicketNumber()==""){
                sql += " and Ticket_Number is null ";
            }else{
                sql += " and Ticket_Number = ? ";
            }
            if(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()==""){
                sql += " and machine_number is null ";
            }else{
                sql += " and machine_number = ? ";
            }
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    // TODO Auto-generated method stub
                    ps.setString(1,saveMeasure.getPartVersion());
                    ps.setString(2,saveMeasure.getStatus());
                    ps.setString(3,saveMeasure.getFrequency());
                    ps.setString(4,saveMeasure.getPeriod());
                    ps.setString(5,saveMeasure.getLink());
                    ps.setString(6,faultList[i].getWorkShop());
                    if(saveMeasure.getTicketNumber().equals("")||saveMeasure.getTicketNumber()==""){
                        if(!(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()=="")){
                            ps.setString(7,saveMeasure.getMachineNumber());
                        }
                    }else{
                        ps.setString(7,saveMeasure.getTicketNumber());
                        if(!(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()=="")){
                            ps.setString(8,saveMeasure.getMachineNumber());
                        }
                    }
                }

                @Override
                public int getBatchSize() {
                    // TODO Auto-generated method stub
                    return faultList.length;
                }
            });
        }catch(Exception e){
            logger.error("刪除歷史量測數據失敗，原因："+e);
            e.printStackTrace();
            result=1;
        }
        return result;

    }

    	public int deleteMeasureTable1(EleScaleMeasure[] faultList,SaveMeasure saveMeasure){
            String sql = "delete from SPC.MEASURE_DATA t where Part_Number_V=? AND Status=? AND Frequency=? and period=? "
                    + " and line_number=? and workshop=?  and date1 = to_char(sysdate,'yyyy-mm-dd') ";
            int result = 0;
            try{
                if(saveMeasure.getTicketNumber().equals("")||saveMeasure.getTicketNumber()==""){
                    sql += " and Ticket_Number is null ";
                }else{
                    sql += " and Ticket_Number = ? ";
                }
                if(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()==""){
                    sql += " and machine_number is null ";
                }else{
                    sql += " and machine_number = ? ";
                }
                jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        // TODO Auto-generated method stub
                        ps.setString(1,saveMeasure.getPartVersion());
                        ps.setString(2,saveMeasure.getStatus());
                        ps.setString(3,saveMeasure.getFrequency());
                        ps.setString(4,saveMeasure.getPeriod());
                        ps.setString(5,saveMeasure.getLink());
                        ps.setString(6,faultList[i].getWorkShop());
                        if(saveMeasure.getTicketNumber().equals("")||saveMeasure.getTicketNumber()==""){
                            if(!(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()=="")){
                                ps.setString(7,saveMeasure.getMachineNumber());
                            }
                        }else{
                            ps.setString(7,saveMeasure.getTicketNumber());
                            if(!(saveMeasure.getMachineNumber().equals("")||saveMeasure.getMachineNumber()=="")){
                                ps.setString(8,saveMeasure.getMachineNumber());
                            }
                        }
                    }

                    @Override
                    public int getBatchSize() {
                        // TODO Auto-generated method stub
                        return faultList.length;
                    }
                });
            }catch(Exception e){
                logger.error("刪除歷史量測數據失敗，原因："+e);
                e.printStackTrace();
                result=1;
            }
            return result;

        }//删MEASURE_DATA表




}
