package com.foxlink.spc.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.foxlink.spc.dao.UploadAllDao;

@Service("UploadAllService")
public class UploadAllService {
	private UploadAllDao uploadAllDao;
	private static Logger logger = Logger.getLogger(UploadAllService.class);

	@Autowired
	public UploadAllService(UploadAllDao uploadAllDao) {
		this.uploadAllDao = uploadAllDao;
	}

	public String CheckPartNumber(String strPartNumber2V) {
		String strResult = null;
		String strPartNumber = strPartNumber2V.substring(0, strPartNumber2V.indexOf("_"));
		String strSQL = "SELECT COUNT(*) from spc.spec where Part_Number_V LIKE '" + strPartNumber + "%'";
		try {
			Integer iCount = uploadAllDao.selectPartNumber(strSQL);
			if (iCount > 0) {
				strResult = "Y";
			} else {
				strResult = "N";
			}
		} catch (Exception e) {
			logger.error("UploadALLService_CheckPartNumber_Error", e);
		}
		return strResult;
	}

	@Transactional(rollbackFor = Exception.class)
	public String uploadOK(MultipartFile file, String strUserName) {
		String fileName = file.getOriginalFilename();
		String fileName2 = fileName.substring(0, fileName.indexOf("."));
		String fileName3 = fileName2.substring(0, fileName2.indexOf("_"));
		String strDeleteSql = "delete FROM  spc.spec WHERE Part_Number_V LIKE '" + fileName3 + "%'";
		String strSelectSql = "SELECT COUNT(*) from spc.spec where Part_Number_V LIKE '" + fileName3 + "%'";
		String filePath = "D:/ExcelBack/Spec/"+fileName;// 存储到服务器上的路径.
		int iresurt = 0;
		String strResurt = "";
		Workbook wb = null;

		String strSQLInsert = "INSERT ALL INTO spc.spec(id,Part_Number_V,Project_Name,Workshop,Inspection_Item,Inspection_Content,Nominal_Dim,Upper_Dim,Lower_Dim,Frequency,Status,Inspection_Method,Remark1,SPC_Num,Dim_Location,Date_Time,Personnel_ID) VALUES ";
		String StrTempSql = "	INTO spc.spec(id,Part_Number_V,Project_Name,Workshop,Inspection_Item,Inspection_Content,Nominal_Dim,Upper_Dim,Lower_Dim,Frequency,Status,Inspection_Method,Remark1,SPC_Num,Dim_Location,Date_Time,Personnel_ID) VALUES ";
		// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
		// 获取excel文件的io流
		try {

			InputStream is = file.getInputStream();

			if (fileName.endsWith("xls")) {
				// 2003
				wb = new HSSFWorkbook(is);
			} else if (fileName.endsWith("xlsx")) {
				// 2007
				wb = new XSSFWorkbook(is);
			}

			Sheet sheet = wb.getSheetAt(0); // 读取sheet(页) 这里选择第0页
			Integer totoalRows = sheet.getLastRowNum(); // 获取总行数量
			int x = 0;
			// 读取row
			for (int rowIndex = 1; rowIndex <= totoalRows; rowIndex++) {

				Row row = sheet.getRow(rowIndex);// 获得当前行
				int lastCellNum = row.getPhysicalNumberOfCells(); // 获得当前行的列数
				if (lastCellNum != 13) {
					return strResurt = "Excel列数应为13，此处为" + lastCellNum;
				}
				;
				strSQLInsert += " ('" + x + "', '" + fileName2 + "', '" + row.getCell(0).getStringCellValue() + "', '"
						+ row.getCell(1).getStringCellValue() + "', '" + row.getCell(2).getStringCellValue() + "', '"
						+ row.getCell(3).getStringCellValue() + "', '"
						+ (row.getCell(4).toString().equals("NA") ? "0.00" : row.getCell(4).toString()) + "', '"
						+ (row.getCell(5).toString().equals("NA") ? "0.00" : row.getCell(5).toString()) + "', '"
						+ (row.getCell(6).toString().equals("NA") ? "0.00" : row.getCell(6).toString()) + "', '"
						+ row.getCell(7).getStringCellValue() + "', '" + row.getCell(8).getStringCellValue() + "', '"
						+ row.getCell(9).getStringCellValue() + "', '" + row.getCell(10).getStringCellValue() + "', '"
						+ row.getCell(11).getStringCellValue() + "', '" + row.getCell(12).getStringCellValue()
						+ "', sysdate,'" + strUserName + "')";
				if (rowIndex != totoalRows) {
					strSQLInsert += StrTempSql;
				}
				x++;
			}
			strSQLInsert += " select 1 from dual";
			// 如果数据库已经有数据，删掉再插入， 如果数据库五此数据直接插入即可。
			if (uploadAllDao.selectPartNumber(strSelectSql) > 0) {
				if (uploadAllDao.delete(strDeleteSql) > 0) {
					iresurt = uploadAllDao.inser(strSQLInsert);
				}
			} else {
				iresurt = uploadAllDao.inser(strSQLInsert);
			}

			File targetFile = new File(filePath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			// 将前台传过来的file文件写到targetFile中.		
			file.transferTo(targetFile);

		} catch (Exception ex) {
			logger.error("UploadALLService_uploadOK_Error", ex);
			iresurt=0;
			strResurt += "NG:" + ex.toString();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			// throw new RuntimeException(ex.getMessage()); //抛出RuntimeException异常

		}
		return "插入了" + iresurt + "行数据 ," + strResurt;

	}

}
