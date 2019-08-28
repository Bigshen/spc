package com.foxlink.spc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository("UploadSpcDataDao")

public class UploadSpcDataDao {
	private JdbcTemplate jdbcTemplate;
	private static Logger logger = Logger.getLogger(UploadSpcDataDao.class);
	@Autowired
	public UploadSpcDataDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//插入數據
	public int inser(String sql) {
		Integer integer=	jdbcTemplate.update(sql);
		return integer;
	}
	//刪除數據
	public int delete(String sql) {
		Integer integer=	jdbcTemplate.update(sql);
		return integer;
	}
	//查詢數據
	public Integer selectPartNumber(String sql) {

		return jdbcTemplate.queryForObject(sql, Integer.class);
		
	}
	
	public void uploadOK(int x, String fileName2, String strUserName, Row row) {
		// TODO Auto-generated method stub
		
	}

	public int SelectProName(String doc_No) {
		// TODO Auto-generated method stub
		int totalRecord = -1;
//		String strSelectSql = "SELECT COUNT(*) from SPC.SPEC_CTP where PROJECT_NAME = ?";
		String strSelectSql = "SELECT COUNT(*) from SPC.PRODUCT_SIZE_MEASURE_RECORD where DOC_NO=? ";
		//System.out.println(strSelectSql);
		try {
			totalRecord=jdbcTemplate.queryForObject(strSelectSql,new Object[]{doc_No},Integer.class);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Find SPCFile error", e);
		}
//		System.out.println(totalRecord);
		return totalRecord;
	}

	public int DeleteProName(String doc_No) {
		// TODO Auto-generated method stub
		int totalRecord = -1;
		String strDeleteSql = "delete FROM  SPC.PRODUCT_SIZE_MEASURE_RECORD where DOC_NO=? ";
		//System.out.println(strDeleteSql);
		try {
			totalRecord=jdbcTemplate.update(strDeleteSql,new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1, doc_No);
				/*	arg0.setString(2, part_No);
					arg0.setString(3, mold_Cavity_Qty);
					arg0.setString(4, mold_Cavity_No);
					arg0.setString(5, machine_No);
					arg0.setString(6, measure_Date);*/
				}
			});

		
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Delete SPCFile error", e);
		}
		//System.out.println(totalRecord);
		return totalRecord;
	}
	
 
	public int uploadOK(String doc_No, String part_No, String mold_Do, String mold_Cavity_No, String mold_Cavity_Qty,
			String machine_No, String measure_Date, String size_Sn, String standard_Value, String upper_Tolerance,
			String lower_Tolerance, String upper_Spec_Limit, String lower_Spec_Limit, String mold_Cavity_M_Data_T1,
			String mold_Cavity_M_Data_T2, String mold_Cavity_M_Data_T3, String mold_Cavity_M_Data_T4,
			String aPPROVAL_PERSONNEL, String day_Shift_Personnel, String night_Shift_Personnel, String strUserName) {
		// TODO Auto-generated method stub
		int totalRecord = 0;
		String strSQLInsert ="INSERT INTO SPC.PRODUCT_SIZE_MEASURE_RECORD(DOC_NO,PART_NO,MOLD_NO,MOLD_CAVITY_NO,MOLD_CAVITY_QTY,MACHINE_NO,MEASURE_DATE,SIZE_SN,STANDARD_VALUE,"
				+ "UPPER_TOLERANCE,LOWER_TOLERANCE,UPPER_SPEC_LIMIT,LOWER_SPEC_LIMIT,MOLD_CAVITY_M_DATA_T1,MOLD_CAVITY_M_DATA_T2,MOLD_CAVITY_M_DATA_T3,MOLD_CAVITY_M_DATA_T4,"
				+ "APPROVAL_PERSONNEL,DAY_SHIFT_PERSONNEL,NIGHT_SHIT_PERSONNEL,UPLOAD_USERID,UPLOAD_TIME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
		try {
			totalRecord = jdbcTemplate.update(strSQLInsert,new PreparedStatementSetter() {
				//.toString().equals("NA") ? "0.00" : row.getCell(4).toString()
				@Override
				public void setValues(PreparedStatement arg0) throws SQLException {
					// TODO Auto-generated method stub
					arg0.setString(1, doc_No);
					arg0.setString(2, part_No);
					arg0.setString(3, mold_Do);
					arg0.setString(4, mold_Cavity_No);
					arg0.setString(5, mold_Cavity_Qty);
					arg0.setString(6, machine_No);
					arg0.setString(7, measure_Date);
					arg0.setString(8, size_Sn);
					arg0.setString(9, standard_Value);
					arg0.setString(10,upper_Tolerance);
					arg0.setString(11,lower_Tolerance);
					arg0.setString(12,upper_Spec_Limit);
					arg0.setString(13,lower_Spec_Limit);
					arg0.setString(14,mold_Cavity_M_Data_T1);
					arg0.setString(15,mold_Cavity_M_Data_T2);
					arg0.setString(16,mold_Cavity_M_Data_T3);
					arg0.setString(17,mold_Cavity_M_Data_T4);
					arg0.setString(18,aPPROVAL_PERSONNEL);
					arg0.setString(19,day_Shift_Personnel);
					arg0.setString(20,night_Shift_Personnel);
					arg0.setString(21,strUserName);
				}
			});
			//System.out.println(totalRecord);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Insert SPCFile error", e);
			//e.getMessage();
			System.out.print(e.getMessage());
		}
		
		//System.out.println("插入語句"+strSQLInsert);
		//System.out.println("插入不成功"+totalRecord);
		return totalRecord;
	}

	public int checkSPCData(String doc_No) {
		// TODO Auto-generated method stub
		int totalRecord = 0;
		String strSQL = "SELECT COUNT(*) from spc.PRODUCT_SIZE_MEASURE_RECORD where Doc_No=?";
		try {
			/*Integer iCount = selectPartNumber(strSQL);*/
			totalRecord = jdbcTemplate.queryForObject(strSQL,new Object[] { doc_No },Integer.class);
			
		} catch (Exception e) {
			logger.error("checkSPCData Erroe", e);
		}
		return totalRecord;
	}


}
