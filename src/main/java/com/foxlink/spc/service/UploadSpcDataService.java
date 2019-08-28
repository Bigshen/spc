package com.foxlink.spc.service;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.foxlink.spc.dao.UploadSpcDataDao;


@Service("UploadSpcDataService")
@Transactional
public class UploadSpcDataService {
	private UploadSpcDataDao uploadSpcDataDao;
	private static Logger logger = Logger.getLogger(UploadSpcDataService.class);
	
	@Autowired
	public UploadSpcDataService(UploadSpcDataDao uploadSpcDataDao) {
		this.uploadSpcDataDao = uploadSpcDataDao;
	}

	public String uploadOK(MultipartFile[] file, String strUserName) {
		String strResurt="";
		String Part_No="";
		String Mold_Do="";
		String Mold_Cavity_No="";
		String Mold_Cavity_Qty="";
		String Machine_No="";
		String Measure_Date="";
		String Doc_No="";
		String APPROVAL_PERSONNEL="";
		String Day_Shift_Personnel="";
		String Night_Shift_Personnel="";
		String Size_Sn="";
		String Standard_Value="";
		String Upper_Tolerance="";
		String Lower_Tolerance="";
		String Upper_Spec_Limit="";
		String Lower_Spec_Limit="";
		String message="";
		// TODO Auto-generated method stub
		for(int i=0;i<file.length;i++) {
			String fileName = file[i].getOriginalFilename();
			//String fileName2 = fileName.substring(0, fileName.indexOf("@"));
			Doc_No = fileName.substring(0, fileName.indexOf("."));
			//Doc_No = fileName2.substring(0, fileName2.lastIndexOf("_"));
			String filePath = "D:/ExcelBack/SPCData/"+fileName;// 存储到服务器上的路径.
		
			int totalRecord = 0;
			Workbook wb = null;
//			int x = 0;
			try {
				InputStream is = file[i].getInputStream();
				
				if (fileName.endsWith("xls")) {
					// 2003
					wb = new HSSFWorkbook(is);
				} else if (fileName.endsWith("xlsx")) {
					// 2007
					wb = new XSSFWorkbook(is);
				}

				Sheet sheet = wb.getSheetAt(0); // 读取sheet(页) 这里选择第0页
				Integer totoalRows = sheet.getLastRowNum(); // 获取总行数量
				Row totalRows = sheet.getRow(2);
				int totoalCells = totalRows.getLastCellNum();
				System.out.println("一共多少列："+totoalCells);
				//System.out.println("总行数量:"+totoalRows);     
				Part_No=sheet.getRow(1).getCell(1).getStringCellValue();
				Mold_Do=sheet.getRow(1).getCell(7).getStringCellValue();
				Mold_Cavity_No=sheet.getRow(1).getCell(11).getStringCellValue();
				Mold_Cavity_Qty=getCellValue(sheet.getRow(1).getCell(13));
				Machine_No=getCellValue(sheet.getRow(1).getCell(15));
				Measure_Date=getCellValue(sheet.getRow(1).getCell(19));
				APPROVAL_PERSONNEL=getCellValue(sheet.getRow(totoalRows-1).getCell(2));
				Day_Shift_Personnel=getCellValue(sheet.getRow(totoalRows-1).getCell(10));
				Night_Shift_Personnel=getCellValue(sheet.getRow(totoalRows-1).getCell(18));
/*				System.out.println("檔案號:"+Doc_No);
				System.out.println("料號:"+Part_No);
				System.out.println("模号:"+Mold_Do);
				System.out.println("模穴號:"+Mold_Cavity_No);
				System.out.println("模穴數:"+Mold_Cavity_Qty);
				System.out.println("機台號:"+Machine_No);
				System.out.println("日期:"+Measure_Date);
				System.out.println("白班人員:"+Day_Shift_Personnel);
				System.out.println("夜班人員:"+Night_Shift_Personnel);*/
				int ratio= 6+(4*Integer.parseInt(Mold_Cavity_Qty));
				if(totoalCells==(ratio)) {
					//uploadSpcDataDao.SelectProName(Doc_No,Measure_Date,Part_No,Mold_Cavity_Qty,Mold_Cavity_No,Machine_No)
					if (uploadSpcDataDao.SelectProName(Doc_No)>0) {
						//uploadSpcDataDao.DeleteProName(Doc_No,Measure_Date,Part_No,Mold_Cavity_Qty,Mold_Cavity_No,Machine_No)
						if (uploadSpcDataDao.DeleteProName(Doc_No)>0) {
							
								for (int rowIndex = 4; rowIndex < totoalRows-4; rowIndex++) {
								Row row = sheet.getRow(rowIndex);// 获得当前行
								
								int law = Integer.parseInt(Mold_Cavity_Qty);
								//System.out.println("穴数"+law);
								
								String Mold_Cavity_M_Data_T1="";
								String Mold_Cavity_M_Data_T2="";
								String Mold_Cavity_M_Data_T3="";
								String Mold_Cavity_M_Data_T4="";
								for(int j=0;j<law;j++) {
									if(j==law-1) {
										Mold_Cavity_M_Data_T1+=getCellValue(row.getCell(6+j));
										Mold_Cavity_M_Data_T2+=getCellValue(row.getCell(6+j+law*1));
										Mold_Cavity_M_Data_T3+=getCellValue(row.getCell(6+j+law*2));
										Mold_Cavity_M_Data_T4+=getCellValue(row.getCell(6+j+law*3));
									}else {
										Mold_Cavity_M_Data_T1+=getCellValue(row.getCell(6+j))+";";
										Mold_Cavity_M_Data_T2+=getCellValue(row.getCell(6+j+law*1))+";";
										Mold_Cavity_M_Data_T3+=getCellValue(row.getCell(6+j+law*2))+";";
										Mold_Cavity_M_Data_T4+=getCellValue(row.getCell(6+j+law*3))+";";
									}				
								}
								Size_Sn=getCellValue(row.getCell(0));
								Standard_Value=getCellValue(row.getCell(1));
								Upper_Tolerance=getCellValue(row.getCell(2));
								Lower_Tolerance=getCellValue(row.getCell(3));
								Upper_Spec_Limit=up(getCellValue(row.getCell(1)),getCellValue(row.getCell(2)));
								Lower_Spec_Limit=sub(getCellValue(row.getCell(1)),getCellValue(row.getCell(3)));
								totalRecord+=uploadSpcDataDao.uploadOK(Doc_No,Part_No,Mold_Do,Mold_Cavity_No,Mold_Cavity_Qty,Machine_No,Measure_Date,Size_Sn,Standard_Value,Upper_Tolerance,Lower_Tolerance,Upper_Spec_Limit,Lower_Spec_Limit,Mold_Cavity_M_Data_T1,Mold_Cavity_M_Data_T2,Mold_Cavity_M_Data_T3,Mold_Cavity_M_Data_T4,APPROVAL_PERSONNEL,Day_Shift_Personnel,Night_Shift_Personnel,strUserName);
							}
						
						}
					}else {
						for (int rowIndex = 4; rowIndex < totoalRows-4; rowIndex++) {
							Row row = sheet.getRow(rowIndex);// 获得当前行	
							int law = Integer.parseInt(Mold_Cavity_Qty);
							//System.out.println("穴数"+law);
							String Mold_Cavity_M_Data_T1="";
							String Mold_Cavity_M_Data_T2="";
							String Mold_Cavity_M_Data_T3="";
							String Mold_Cavity_M_Data_T4="";
							for(int j=0;j<law;j++) {
								if(j==law-1) {
									Mold_Cavity_M_Data_T1+=getCellValue(row.getCell(6+j));
									Mold_Cavity_M_Data_T2+=getCellValue(row.getCell(6+j+law*1));
									Mold_Cavity_M_Data_T3+=getCellValue(row.getCell(6+j+law*2));
									Mold_Cavity_M_Data_T4+=getCellValue(row.getCell(6+j+law*3));
								}else {
									Mold_Cavity_M_Data_T1+=getCellValue(row.getCell(6+j))+";";
									Mold_Cavity_M_Data_T2+=getCellValue(row.getCell(6+j+law*1))+";";
									Mold_Cavity_M_Data_T3+=getCellValue(row.getCell(6+j+law*2))+";";
									Mold_Cavity_M_Data_T4+=getCellValue(row.getCell(6+j+law*3))+";";
								}				
							}
							Size_Sn=getCellValue(row.getCell(0));
							Standard_Value=getCellValue(row.getCell(1));
							Upper_Tolerance=getCellValue(row.getCell(2));
							Lower_Tolerance=getCellValue(row.getCell(3));
							Upper_Spec_Limit=up(getCellValue(row.getCell(1)),getCellValue(row.getCell(2)));
							Lower_Spec_Limit=sub(getCellValue(row.getCell(1)),getCellValue(row.getCell(3)));
							totalRecord+=uploadSpcDataDao.uploadOK(Doc_No,Part_No,Mold_Do,Mold_Cavity_No,Mold_Cavity_Qty,Machine_No,Measure_Date,Size_Sn,Standard_Value,Upper_Tolerance,Lower_Tolerance,Upper_Spec_Limit,Lower_Spec_Limit,Mold_Cavity_M_Data_T1,Mold_Cavity_M_Data_T2,Mold_Cavity_M_Data_T3,Mold_Cavity_M_Data_T4,APPROVAL_PERSONNEL,Day_Shift_Personnel,Night_Shift_Personnel,strUserName);
							
							
						}
						//if(totalRecord)
						
						//System.out.println(message);
					}
					
					
					
					File targetFile = new File(filePath);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// 将前台传过来的file文件写到targetFile中.		
					file[i].transferTo(targetFile);		
					
					
					/*System.out.println("Rows:"+totoalRows);
					System.out.println("Record:"+totalRecord);*/
					
				}else {
					 message+="文檔有誤，請檢查文檔！";
				}	
			
			
				is.close();
				wb.close();
				
			} catch (Exception e) {
				// TODO: handle exception
//				totalRecord = 0;
				message += "NG:" + e.toString();
			}
			
			message+="文檔:"+fileName+" 插入了"+totalRecord+"條記錄\n";
			
		/*	if(i==file.length-1) {
				return message;
			}*/
		}
	
		return message;
	}
	

	public String getCellValue(Cell cell){  
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
        DecimalFormat decimalFormat = new DecimalFormat();
        String cellValue = "";

        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
            	 if(HSSFDateUtil.isCellDateFormatted(cell)) {
                     double d = cell.getNumericCellValue();
                     Date date = HSSFDateUtil.getJavaDate(d);
                     cellValue = sFormat.format(date);
                 }
                 else {                
                     cellValue = decimalFormat.format((cell.getNumericCellValue()));     	 
                 }
                break;
            case STRING:
            	cellValue = cell.getStringCellValue();
                break;
            case FORMULA:
            	cellValue = cell.getCellFormula().toString();
                break;
            case BLANK:
            	cellValue = "";
                break;
            case BOOLEAN:
            	 cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case ERROR:
            	 cellValue = "";
                break;
            default:
               break;
        }
		return cellValue;
	}    
	
	public String up(String cellNum,String num) {
		String cellUp="";
		try {		
			float a = Float.parseFloat(cellNum);
			float b = Float.parseFloat(num);
			BigDecimal c = new BigDecimal(a+b);
		    cellUp=String.valueOf(c.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		return cellUp;	
	}
	
	public String sub(String cellNum,String num) {
		String cellSub="";
		try {
			float a = Float.parseFloat(cellNum);
			float b = Float.parseFloat(num);
			BigDecimal c = new BigDecimal(a-b);
			cellSub=String.valueOf(c.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		return cellSub;	
	}

	public String checkSPCData(MultipartFile[] file) {
		// TODO Auto-generated method stub
		String Doc_No="";
		String strResult="";
		int totalRecords = 0;
		for (int i = 0; i < file.length; i++) {
			String fileName = file[i].getOriginalFilename();
			Doc_No = fileName.substring(0, fileName.indexOf("."));
			totalRecords+=uploadSpcDataDao.checkSPCData(Doc_No);
			
		}
		if (totalRecords > 0) {
			strResult="Y";
		} else {
			strResult="N";
		}
		//System.out.println(totalRecords);
		return strResult;
	}
}
