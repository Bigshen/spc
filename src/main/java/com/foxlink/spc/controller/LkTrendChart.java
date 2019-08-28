package com.foxlink.spc.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.Region;
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellCopyPolicy;
import org.apache.poi.ss.usermodel.Chart;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.charts.AxisCrosses;
import org.apache.poi.ss.usermodel.charts.AxisPosition;
import org.apache.poi.ss.usermodel.charts.AxisTickMark;
import org.apache.poi.ss.usermodel.charts.ChartAxis;
import org.apache.poi.ss.usermodel.charts.ChartDataSource;
import org.apache.poi.ss.usermodel.charts.ChartLegend;
import org.apache.poi.ss.usermodel.charts.DataSources;
import org.apache.poi.ss.usermodel.charts.LegendPosition;
import org.apache.poi.ss.usermodel.charts.LineChartData;
import org.apache.poi.ss.usermodel.charts.LineChartSeries;
import org.apache.poi.ss.usermodel.charts.ValueAxis;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xssf.streaming.SXSSFDrawing;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerStyle;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/LkTrendChart")
public class LkTrendChart {

	private static Logger logger = Logger.getLogger(LkTrendChart.class);
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String Query() {
		return "LkTrendChart";
	}

	@RequestMapping("/getPartData")
	@ResponseBody
	public List<Map<String, Object>> GetPartData() {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String strSQL = "SELECT DISTINCT T.PART_NO FROM SPC.PRODUCT_SIZE_MEASURE_RECORD T ORDER BY T.PART_NO";
		try {
			listMap = (List<Map<String, Object>>) jdbcTemplate.queryForList(strSQL);
			if(listMap.isEmpty()) return null;
		} catch (Exception e) {
			logger.info(e);
		}
		return listMap;
	}

	@RequestMapping("/getModelData")
	@ResponseBody
	public List<Map<String, Object>> GetModelData(@RequestParam(value = "varPartNO") String varPartNO) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String strSQL = "SELECT DISTINCT T.MOLD_CAVITY_QTY,T.MOLD_CAVITY_NO, T.MOLD_NO FROM SPC.PRODUCT_SIZE_MEASURE_RECORD T WHERE T.PART_NO='"
				+ varPartNO + "' ORDER BY T.MOLD_NO,T.MOLD_CAVITY_QTY";
		try {
		listMap = (List<Map<String, Object>>) jdbcTemplate.queryForList(strSQL);
		if(listMap.isEmpty()) return null;
		} catch (Exception e) {
			logger.info(e);
		}
		return listMap;
	}

	@RequestMapping("/getModelQtyData")
	@ResponseBody
	public List<Map<String, Object>> GetModelQtyData(@RequestParam(value = "varMoldNO") String varMoldNO) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String strSQL = "SELECT DISTINCT T.MOLD_CAVITY_QTY FROM SPC.PRODUCT_SIZE_MEASURE_RECORD T WHERE T.MOLD_NO ='"
				+ varMoldNO + "' ORDER BY T.MOLD_CAVITY_QTY";
		try {
		listMap = (List<Map<String, Object>>) jdbcTemplate.queryForList(strSQL);
		} catch (Exception e) {
			logger.info(e);
		}
		return listMap;
	}

	@RequestMapping("/getAllMonth")
	@ResponseBody
	public List<Map<String, Object>> getMonth(@RequestParam(value = "varPartNO") String varPartNO,
			@RequestParam(value = "varMoldNO") String varMoldNO,
			@RequestParam(value = "varMOLD_CAVITY_QTY") String varMOLD_CAVITY_QTY,
			@RequestParam(value = "varYearMonth") String varYearMonth) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		String strSQL = "select DISTINCT T.MEASURE_DATE from spc.PRODUCT_SIZE_MEASURE_RECORD t where T.PART_NO='"
				+ varPartNO + "' and T.MOLD_NO='" + varMoldNO + "' AND T.MOLD_CAVITY_QTY=" + varMOLD_CAVITY_QTY
				+ " AND T.MEASURE_DATE LIKE '" + varYearMonth + "%'";
		try {
		listMap = (List<Map<String, Object>>) jdbcTemplate.queryForList(strSQL);
		} catch (Exception e) {
			logger.info(e);
		}
		return listMap;

	}
	
	@RequestMapping("/getendflag")
	@ResponseBody
    public Object getendflag(HttpServletRequest request) {
        
        Object flag = request.getSession().getAttribute("endflag"); //获取结束标记*/
        System.out.println(flag);
        return flag;
    }
	
	@RequestMapping("/test2")
	public void test2() throws IOException {
		 //TemplateExportParams params = new TemplateExportParams("D:\\\\offer_template2.xlsx", true);
		File fi = new File("D:\\offer_template23.xlsx");
		InputStream in = new FileInputStream(fi);
		
		XSSFWorkbook wb = new XSSFWorkbook(in);
		XSSFSheet sheet1 =  wb.getSheetAt(0);
		XSSFDrawing drawing =sheet1.getDrawingPatriarch();
		List<XSSFChart> my_line_chart=drawing.getCharts();
		XSSFChart AC=my_line_chart.get(0);
		LineChartData data =  AC.getChartDataFactory().createLineChartData();
		AC.plot(data);
		drawing.createAnchor(0, 0, 0, 0, 2, 26, 124, 40);
		FileOutputStream out = new FileOutputStream("D:/export1.xlsx");
		 wb.write(out);wb.close(); out.close();
		
		/*XSSFSheet sheet1 = wb.createSheet("Sheet1");
		XSSFDrawing xlsx_drawing= sheet1.getDrawingPatriarch();
		XSSFClientAnchor anchor = xlsx_drawing.createAnchor(0, 0, 0, 0, 2, 26, 124, 40);
		 XSSFChart my_line_chart = xlsx_drawing.createChart(anchor);
		 XSSFChartLegend legend = my_line_chart.getOrCreateLegend();
		 legend.setPosition(LegendPosition.LEFT);
		   
		   LineChartData data =  my_line_chart.getChartDataFactory().createLineChartData();
		   
		   ChartAxis bottomAxis =   my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
		   bottomAxis.setCrosses(AxisCrosses.MIN);
		   //bottomAxis.setMajorTickMark(AxisTickMark.NONE);//取消X轴的标刻度
		   ValueAxis  leftAxis = my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
		   leftAxis.setCrosses(AxisCrosses.AUTO_ZERO); //添加数据
		   ChartDataSource<Number> xs  = DataSources.fromNumericCellRange(sheet1, new CellRangeAddress(29, 29, 2,   126)); 
		   ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet1,  new CellRangeAddress(27, 27,2 , 126)); 
		   ChartDataSource<Number> ys2 =  DataSources.fromNumericCellRange(sheet1, new CellRangeAddress(28, 28,2 , 126)); 
		   ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(sheet1,  new CellRangeAddress(25, 25,2 , 126));
		   
		   LineChartSeries series = data.addSeries(xs, ys1);
		   series.setTitle("Data");//设置序列名称
		   
		   XSSFChart xssfChart = (XSSFChart)my_line_chart; 
		   CTPlotArea plotArea = xssfChart.getCTChart().getPlotArea(); 
		   CTMarker ctMarker = CTMarker.Factory.newInstance(); 
		   ctMarker.setSymbol(CTMarkerStyle.Factory.newInstance()); 
		   for (CTLineSer ser : plotArea.getLineChartArray()[0].getSerArray()) { 
		       ser.setMarker(ctMarker); 
		   } 
		   
		   LineChartSeries series2=data.addSeries(xs, ys2);
		   series2.setTitle("LSL");//设置序列名称 
		   LineChartSeries series3=data.addSeries(xs, ys3); series2.setTitle("EE");//设置序列名称
		   //my_line_chart1.setTitleText("Run Chart");//设置图表标题
		   my_line_chart.plot(data, new ChartAxis[] { bottomAxis, leftAxis }); //my_line_chart.plot(data);
		   FileOutputStream out = new FileOutputStream("D:/export1.xlsx");
			
			// 通知客户端响应内容长度为888个字节
		   wb.write(out);wb.close(); out.close();*/
		
	}

	@RequestMapping("/test")
	@ResponseBody
	public String test(HttpServletRequest request,HttpServletResponse response,
			/*
			 * @RequestParam(value="varPartNO")String varPartNO,
			 * 
			 * @RequestParam(value="varMoldNO")String varMoldNO,
			 * 
			 * @RequestParam(value="varMOLD_CAVITY_QTY")String varMOLD_CAVITY_QTY,
			 * 
			 * @RequestParam(value="varYearMonth")String varYearMonth
			 */
			@RequestParam(value = "id-PartNO") String varPartNO, @RequestParam(value = "id-MoldNO") String varMoldNO,
			@RequestParam(value = "id-CavQty") String varMOLD_CAVITY_QTY,
			@RequestParam(value = "id-TimeValue") String varYearMonth,
			@RequestParam(value = "id-CavNO") String varMOLD_CAVITY_NO) throws IOException, ParseException {
/*		Object flag = request.getSession().getAttribute("endflag"); //获取标记
		if(flag!=null) {
			if(flag.equals("S")) {
			return "正在導出";}}
		request.getSession().setAttribute("endflag", "S");//设置结束标记
*/		request.getSession().removeAttribute("endflag");//每次导入前，清除结束标记
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		varYearMonth = varYearMonth.replace("-", "");
		String strReturn="";

		String strSQL = "select DISTINCT * from spc.PRODUCT_SIZE_MEASURE_RECORD t where T.PART_NO='" + varPartNO
				+ "' and T.MOLD_NO='" + varMoldNO + "' AND T.MOLD_CAVITY_QTY=" + varMOLD_CAVITY_QTY
				+ " AND T.MEASURE_DATE LIKE '" + varYearMonth + "%' AND T.MOLD_CAVITY_NO='"+varMOLD_CAVITY_NO+"' ORDER BY T.MEASURE_DATE,T.SIZE_SN";
		System.out.println(strSQL);
		try {
		listMap = (List<Map<String, Object>>) jdbcTemplate.queryForList(strSQL);
		} catch (Exception e) {
			logger.info(e);
			request.getSession().setAttribute("endflag", "1");//设置结束标记
			return "查詢數據庫出錯！";
		}
		if (listMap.isEmpty()) {
			request.getSession().setAttribute("endflag", "1");//设置结束标记
			return "無資料";// 捞取出来的资料是空的，不用继续了。
		}
		int iCavSum = Integer.parseInt(listMap.get(0).get("MOLD_CAVITY_QTY").toString());
		File fi = new File("D:\\offer_template"+iCavSum+".xlsx");
		if (!fi.exists()) {
			request.getSession().setAttribute("endflag", "1");//设置结束标记
			return "offer_template"+iCavSum+"模板文件不存在";
		}

		InputStream in = null;
		// 读取excel模板，同时一个新Excel
		XSSFWorkbook wb = null;
		//XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(in);
		OutputStream output = response.getOutputStream();
		try {

		// 判断模板的sheet是否大于Cav的数量。
			in = new FileInputStream(fi);
			wb = new XSSFWorkbook(in);
		int iSheetSum = wb.getNumberOfSheets();
		if (iCavSum > iSheetSum) {
			return "模板文件的Sheet數量太少，請更新模板文件！";
		}

		// 先整出一个不重复的SIZE_SN IS '尺寸序號'; 序号当做key，往excel的哪一行写资料当做value
		// 当数据循环的时候，取出值为key中的value值
		Set<String> setSize = new HashSet<String>();//// 实例化一个set集合
		Map<Object, String> map = new HashMap<>();
		Object[] setSizeToArray = null;
		int irow = 6;// 这样玩意是，计算填充行下一循环的位置，每循环一次加19
		for (Map<String, Object> m : listMap) {
			setSize.add(m.get("SIZE_SN").toString());
			setSizeToArray = setSize.toArray();
		}
		for (Object o : setSizeToArray) {
			String S = Integer.toString(irow);
			map.put(o, S);
			irow += 19;
		}


	

		for (int j = 0; j < Integer.parseInt(listMap.get(0).get("MOLD_CAVITY_QTY").toString()); j++) {// 循环穴位
			// if(j!=0) wb.cloneSheet(0, "Cav" + (j+1));// 根据穴位生成同数量的sheet，Cav2,Cav3....
			XSSFSheet sheet1 = null;

			sheet1 = wb.getSheetAt(j);
			int rowNum = sheet1.getLastRowNum();// 获得总行数
			int intSizeRowSum = setSizeToArray.length * 19 + 2;// 如果一个size_sn就是1*19+3=22，2*19+3=
			if (rowNum < intSizeRowSum) {
				return "模板文件的Sheet总行数少于需要填充的总行数，請更新模板文件";// sheet的总行数少于需要填充的总行数，不用继续了。
			}
			sheet1.setForceFormulaRecalculation(true);
			String strChangMouth = "";// 循环的日期如果变了，就把irow重新设为6

			/*
			 * //List<XSSFShape> shapes= sheet1.getDrawingPatriarch().getShapes();
			 * XSSFDrawing xssDraw= sheet1.getDrawingPatriarch();
			 * 
			 * XSSFChart my_line_chart1=xssDraw.getCharts().get(0); //CTAbsoluteAnchor ctab=
			 * sheet1.getDrawingPatriarch().getCTDrawing().getAbsoluteAnchorArray(0);
			 * 
			 * 
			 * 
			 * //获取图表形状？ List<XSSFShape> shapes= sheet1.getDrawingPatriarch().getShapes();
			 * //for(XSSFShape shape : shapes){ XSSFDrawing xssDraw=
			 * shapes.get(0).getDrawing(); XSSFClientAnchor anchor1 =
			 * xssDraw.createAnchor(0, 0, 0, 0, 2, 26, 13, 40); XSSFChart my_line_chart1 =
			 * xssDraw.createChart(anchor1);
			 * 
			 * //}
			 * 
			 * 
			 * XSSFDrawing xlsx_drawing = sheet1.createDrawingPatriarch(); //
			 * 八个参数，前四个表示图片离起始单元格和结束单元格边缘的位置， //
			 * 后四个表示起始和结束单元格的位置，如下表示从第2列到第12列，从第1行到第15行,需要注意excel起始位置是0
			 * //2代表xy,位于第三列。26代表Y在26行的下面，40代表y在第40行的上面 XSSFClientAnchor anchor =
			 * xlsx_drawing.createAnchor(0, 0, 0, 0, 2, 26, 124, 40);
			 * 
			 * XSSFChart my_line_chart = xlsx_drawing.createChart(anchor);
			 * 
			 * 
			 * 
			 * //ChartLegend legend=my_line_chart.getOrCreateLegend(); XSSFChartLegend
			 * legend = my_line_chart1.getOrCreateLegend(); //XDDFChartLegend xddlegend=
			 * my_line_chart1.getOrAddLegend(); legend.setPosition(legend.getPosition());
			 * 
			 * LineChartData data =
			 * my_line_chart.getChartDataFactory().createLineChartData();
			 * 
			 * ChartAxis bottomAxis =
			 * my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
			 * bottomAxis.setCrosses(AxisCrosses.MIN);
			 * //bottomAxis.setMajorTickMark(AxisTickMark.NONE);//取消X轴的标刻度 ValueAxis
			 * leftAxis =
			 * my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
			 * leftAxis.setCrosses(AxisCrosses.AUTO_ZERO); //添加数据 ChartDataSource<Number> xs
			 * = DataSources.fromNumericCellRange(sheet1, new CellRangeAddress(29, 29, 2,
			 * 126)); ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet1,
			 * new CellRangeAddress(27, 27,2 , 126)); ChartDataSource<Number> ys2 =
			 * DataSources.fromNumericCellRange(sheet1, new CellRangeAddress(28, 28,2 ,
			 * 126)); ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(sheet1,
			 * new CellRangeAddress(25, 25,2 , 126));
			 * 
			 * LineChartSeries series = data.addSeries(xs, ys1);
			 * series.setTitle("Data");//设置序列名称
			 * 
			 * LineChartSeries series2=data.addSeries(xs, ys2);
			 * series2.setTitle("LSL");//设置序列名称 LineChartSeries series3=data.addSeries(xs,
			 * ys3); series2.setTitle("EE");//设置序列名称
			 * //my_line_chart1.setTitleText("Run Chart");//设置图表标题 my_line_chart.plot(data,
			 * new ChartAxis[] { bottomAxis, leftAxis }); //my_line_chart.plot(data);
			 */
			for (Map<String, Object> m : listMap) {

				// 当数据循环的时候，取出值为key中的value值
				String strSizeSN = m.get("SIZE_SN").toString();
				irow = Integer.parseInt(map.get(strSizeSN));
				int iMonth=Integer.parseInt(varYearMonth.substring(4));

				// 第二行填充，除了模穴不一样，其他都一样
				sheet1.getRow(1).getCell(2).setCellValue(iMonth);// 第2行 第3列，统计月份：
				sheet1.getRow(1).getCell(5).setCellValue(listMap.get(0).get("MOLD_CAVITY_NO").toString());// 第2行 第6列，模穴號
				sheet1.getRow(1).getCell(8).setCellValue(listMap.get(0).get("MOLD_NO").toString());// 第2行 第9列，模號
				sheet1.getRow(1).getCell(13).setCellValue("Cav" + (j + 1));// 第2行 第14列
				sheet1.getRow(1).getCell(16).setCellValue(listMap.get(0).get("PART_NO").toString());// 第2行 第17，料号
				// B列填充，m.get("LOWER_TOLERANCE").toString()
				String strNO = m.get("SIZE_SN").toString();
				Double DouNominal = Double.parseDouble(m.get("STANDARD_VALUE").toString());
				Double DouZTol = Double.parseDouble(m.get("UPPER_TOLERANCE").toString());
				Double DouFTol = Double.parseDouble(m.get("LOWER_TOLERANCE").toString());
				sheet1.getRow(irow + 1).getCell(1).setCellValue(strNO);// 第8行 第2列
				sheet1.getRow(irow + 2).getCell(1).setCellValue(DouNominal);// 第9行 第2列
				sheet1.getRow(irow + 3).getCell(1).setCellValue(DouZTol);// 第10行 第2列
				sheet1.getRow(irow + 4).getCell(1).setCellValue(DouFTol);// 第11行 第2列
				// 对所有Measured Data测量数据填充。
				String[] strsT1 = m.get("MOLD_CAVITY_M_DATA_T1").toString().split(";", -1);
				String[] strsT2 = m.get("MOLD_CAVITY_M_DATA_T2").toString().split(";", -1);
				String[] strsT3 = m.get("MOLD_CAVITY_M_DATA_T3").toString().split(";", -1);
				String[] strsT4 = m.get("MOLD_CAVITY_M_DATA_T4").toString().split(";", -1);
				int intMonth = Integer.parseInt(m.get("MEASURE_DATE").toString().substring(6));// 分解出此行数据是哪日的。
				if (!strsT1[j].isEmpty())
					sheet1.getRow(irow).getCell(intMonth * 4 - 2)
							.setCellValue(Double.parseDouble(strsT1[j].toString()));// 第5行 第7列，分号前第j个数据
				if (!strsT2[j].isEmpty())
					sheet1.getRow(irow).getCell(intMonth * 4 - 2 + 1)
							.setCellValue(Double.parseDouble(strsT2[j].toString()));// 第5行 第7列，分号前第j个数据
				if (!strsT3[j].isEmpty())
					sheet1.getRow(irow).getCell(intMonth * 4 - 2 + 2)
							.setCellValue(Double.parseDouble(strsT3[j].toString()));// 第5行 第7列，分号前第j个数据
				if (!strsT4[j].isEmpty())
					sheet1.getRow(irow).getCell(intMonth * 4 - 2 + 3)
							.setCellValue(Double.parseDouble(strsT4[j].toString()));// 第5行 第7列，分号前第j个数据
				// 白夜班、机台填充，所有Cav1、Cav2...的Date
				String strDayPeopre = StringUtils.isEmpty(m.get("DAY_SHIFT_PERSONNEL"))?"":m.get("DAY_SHIFT_PERSONNEL").toString();// 白班人员
				String strShiftPeopre = StringUtils.isEmpty(m.get("NIGHT_SHIT_PERSONNEL"))?"":m.get("NIGHT_SHIT_PERSONNEL").toString();// 夜班人员
				String strMacNo = m.get("MACHINE_NO").toString();// 机台
				sheet1.getRow(irow - 3).getCell(intMonth * 4 - 2 + 1).setCellValue(strDayPeopre);// 白班填充
				sheet1.getRow(irow - 3).getCell(intMonth * 4 - 2 + 2).setCellValue(strMacNo + "#");// 机台填充
				sheet1.getRow(irow - 3).getCell(intMonth * 4 - 2 + 3).setCellValue(strShiftPeopre);// 夜班填充

			}
			// 月份填充
			int irowMouth2 = 3;// 填充月份的循环，每循环一次加19
			SimpleDateFormat datetemp = new SimpleDateFormat("yyyyMMdd");
			Date DT1 = datetemp.parse(listMap.get(0).get("MEASURE_DATE").toString());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DT1);
			int iDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			for (Object o : setSizeToArray) {
				for (int i = 1; i <= iDay; i++) {
					calendar.set(Calendar.DAY_OF_MONTH, i);
					sheet1.getRow(irowMouth2).getCell(i * 4 - 2).setCellValue(calendar.getTime());// 月份填充
				}
				irowMouth2 += 19;
			}

			// sheet1.shiftRows(41, 668, -1);//删除第一行到第四行，然后使下方单元格上移
			// for(int i=41;i<=59;i++) {
			// removeRow1(sheet,i);
			// sheet.getDrawingPatriarch().getCTDrawing().setNil();
            for (int i = intSizeRowSum+1; i <= rowNum; i++) {
   			 XSSFRow removingRow=sheet1.getRow(i);
   			 removingRow.setZeroHeight(true);
				
			}

			// sheet1.removeRow(removingRow);

			// sheet1.removeRowBreak(i);

			// }
		
		}
		// System.out.println(k + " : " + m.get(k));
		// }

		// }
		// }
		// }

		/*
		 * sheet.getRow(1).getCell(2).setCellValue(7);//第2行 第3列
		 * sheet.getRow(1).getCell(5).setCellValue("LQ");//第2行 第6列
		 */

		/*
		 * CellCopyPolicy cellCopyPolicy=new CellCopyPolicy();
		 * cellCopyPolicy.setCopyCellStyle(true); cellCopyPolicy.setCopyCellValue(true);
		 * cellCopyPolicy.setCopyMergedRegions(true);
		 * cellCopyPolicy.setCopyRowHeight(true);
		 * cellCopyPolicy.setCopyCellFormula(true);
		 * 
		 * sheet.copyRows(2, 20, 21,cellCopyPolicy);
		 */
/*		int iCavQty=Integer.parseInt(listMap.get(0).get("MOLD_CAVITY_QTY").toString());
		for (int i = iCavQty; i < iSheetSum; i++) {
			
			wb.removeSheetAt(i);
		}*/

		String strMoldNo = listMap.get(0).get("MOLD_NO").toString();
		String strNow = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String strFileName = strMoldNo + "_@SPC_" + varYearMonth + "_" + strNow + ".xlsx";

		
		response.reset();// 清除buffer缓存
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel;charset=utf-8");// 设置contentType为excel格式
		response.setHeader("Content-Disposition", "Attachment;Filename=" + strFileName);
		// 通知客户端响应内容长度为888个字节
		wb.write(output);
		
		
/*} catch (Exception e) {
	logger.info(e.getMessage());
	strReturn= "導出異常！";*/
}finally {
	request.getSession().setAttribute("endflag", "1");//设置结束标记
	//request.getSession().setAttribute("endflag", "E");//设置结束标记
	if(in!=null) in.close();
	if(wb!=null) wb.close();
	
	output.flush();
	output.close();
	System.gc();
	
}

		return "OK";

		/*
		 * //修改模板内容导出新模板 FileOutputStream out = new FileOutputStream("D:/export1.xlsx");
		 * wb.write(out);wb.close(); out.close();
		 */

		// return "LinkManage";

	}
	
	@RequestMapping("/testbb")
	@ResponseBody
	public String testbb(HttpServletRequest request,HttpServletResponse response,
			/*
			 * @RequestParam(value="varPartNO")String varPartNO,
			 * 
			 * @RequestParam(value="varMoldNO")String varMoldNO,
			 * 
			 * @RequestParam(value="varMOLD_CAVITY_QTY")String varMOLD_CAVITY_QTY,
			 * 
			 * @RequestParam(value="varYearMonth")String varYearMonth
			 */
			@RequestParam(value = "id-PartNO") String varPartNO, @RequestParam(value = "id-MoldNO") String varMoldNO,
			@RequestParam(value = "id-CavQty") String varMOLD_CAVITY_QTY,
			@RequestParam(value = "id-TimeValue") String varYearMonth,
			@RequestParam(value = "id-CavNO") String varMOLD_CAVITY_NO) throws IOException, ParseException {
/*		Object flag = request.getSession().getAttribute("endflag"); //获取标记
		if(flag!=null) {
			if(flag.equals("S")) {
			return "正在導出";}}
		request.getSession().setAttribute("endflag", "S");//设置结束标记
*/		
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		varYearMonth = varYearMonth.replace("-", "");
		String strReturn="";

		String strSQL = "select DISTINCT * from spc.PRODUCT_SIZE_MEASURE_RECORD t where T.PART_NO='" + varPartNO
				+ "' and T.MOLD_NO='" + varMoldNO + "' AND T.MOLD_CAVITY_QTY=" + varMOLD_CAVITY_QTY
				+ " AND T.MEASURE_DATE LIKE '" + varYearMonth + "%' AND T.MOLD_CAVITY_NO='"+varMOLD_CAVITY_NO+"' ORDER BY T.MEASURE_DATE,T.SIZE_SN";
		System.out.println(strSQL);
		try {
		listMap = (List<Map<String, Object>>) jdbcTemplate.queryForList(strSQL);
		} catch (Exception e) {
			logger.info(e);
			return "查詢數據庫出錯！";
		}
		if (listMap.isEmpty()) {
			return "無資料";// 捞取出来的资料是空的，不用继续了。
		}
		int iCavSum = Integer.parseInt(listMap.get(0).get("MOLD_CAVITY_QTY").toString());
		File fi = new File("D:\\offer_template"+iCavSum+".xlsx");
		if (!fi.exists()) {
			return "offer_template"+iCavSum+"模板文件不存在";
		}
		InputStream in = null;
		// 读取excel模板，同时一个新Excel
		XSSFWorkbook wb1 = null;
		//XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(in);
		OutputStream output = response.getOutputStream();
		try {
		// 判断模板的sheet是否大于Cav的数量。
			in = new FileInputStream(fi);
			wb1 = new XSSFWorkbook(in);
			SXSSFWorkbook wb = new SXSSFWorkbook(wb1,1000);
		int iSheetSum = wb.getNumberOfSheets();
		if (iCavSum > iSheetSum) {
			return "模板文件的Sheet數量太少，請更新模板文件！";
		}

		// 先整出一个不重复的SIZE_SN IS '尺寸序號'; 序号当做key，往excel的哪一行写资料当做value
		// 当数据循环的时候，取出值为key中的value值
		Set<String> setSize = new HashSet<String>();//// 实例化一个set集合
		Map<Object, String> map = new HashMap<>();
		Object[] setSizeToArray = null;
		int irow = 6;// 这样玩意是，计算填充行下一循环的位置，每循环一次加19
		for (Map<String, Object> m : listMap) {
			setSize.add(m.get("SIZE_SN").toString());
			setSizeToArray = setSize.toArray();
		}
		for (Object o : setSizeToArray) {
			String S = Integer.toString(irow);
			map.put(o, S);
			irow += 19;
		}


	

		for (int j = 0; j < Integer.parseInt(listMap.get(0).get("MOLD_CAVITY_QTY").toString()); j++) {// 循环穴位
			// if(j!=0) wb.cloneSheet(0, "Cav" + (j+1));// 根据穴位生成同数量的sheet，Cav2,Cav3....
			SXSSFSheet sheet1 = null;

			sheet1 = wb.getSheetAt(j);
			int rowNum = sheet1.getLastRowNum();// 获得总行数
			int intSizeRowSum = setSizeToArray.length * 19 + 2;// 如果一个size_sn就是1*19+3=22，2*19+3=
			if (rowNum < intSizeRowSum) {
				//return "模板文件的Sheet总行数少于需要填充的总行数，請更新模板文件";// sheet的总行数少于需要填充的总行数，不用继续了。
			}
			sheet1.setForceFormulaRecalculation(true);
			String strChangMouth = "";// 循环的日期如果变了，就把irow重新设为6

			/*
			 * //List<XSSFShape> shapes= sheet1.getDrawingPatriarch().getShapes();
			 * XSSFDrawing xssDraw= sheet1.getDrawingPatriarch();
			 * 
			 * XSSFChart my_line_chart1=xssDraw.getCharts().get(0); //CTAbsoluteAnchor ctab=
			 * sheet1.getDrawingPatriarch().getCTDrawing().getAbsoluteAnchorArray(0);
			 * 
			 * 
			 * 
			 * //获取图表形状？ List<XSSFShape> shapes= sheet1.getDrawingPatriarch().getShapes();
			 * //for(XSSFShape shape : shapes){ XSSFDrawing xssDraw=
			 * shapes.get(0).getDrawing(); XSSFClientAnchor anchor1 =
			 * xssDraw.createAnchor(0, 0, 0, 0, 2, 26, 13, 40); XSSFChart my_line_chart1 =
			 * xssDraw.createChart(anchor1);
			 * 
			 * //}
			 * 
			 * 
			 * XSSFDrawing xlsx_drawing = sheet1.createDrawingPatriarch(); //
			 * 八个参数，前四个表示图片离起始单元格和结束单元格边缘的位置， //
			 * 后四个表示起始和结束单元格的位置，如下表示从第2列到第12列，从第1行到第15行,需要注意excel起始位置是0
			 * //2代表xy,位于第三列。26代表Y在26行的下面，40代表y在第40行的上面 XSSFClientAnchor anchor =
			 * xlsx_drawing.createAnchor(0, 0, 0, 0, 2, 26, 124, 40);
			 * 
			 * XSSFChart my_line_chart = xlsx_drawing.createChart(anchor);
			 * 
			 * 
			 * 
			 * //ChartLegend legend=my_line_chart.getOrCreateLegend(); XSSFChartLegend
			 * legend = my_line_chart1.getOrCreateLegend(); //XDDFChartLegend xddlegend=
			 * my_line_chart1.getOrAddLegend(); legend.setPosition(legend.getPosition());
			 * 
			 * LineChartData data =
			 * my_line_chart.getChartDataFactory().createLineChartData();
			 * 
			 * ChartAxis bottomAxis =
			 * my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
			 * bottomAxis.setCrosses(AxisCrosses.MIN);
			 * //bottomAxis.setMajorTickMark(AxisTickMark.NONE);//取消X轴的标刻度 ValueAxis
			 * leftAxis =
			 * my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
			 * leftAxis.setCrosses(AxisCrosses.AUTO_ZERO); //添加数据 ChartDataSource<Number> xs
			 * = DataSources.fromNumericCellRange(sheet1, new CellRangeAddress(29, 29, 2,
			 * 126)); ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(sheet1,
			 * new CellRangeAddress(27, 27,2 , 126)); ChartDataSource<Number> ys2 =
			 * DataSources.fromNumericCellRange(sheet1, new CellRangeAddress(28, 28,2 ,
			 * 126)); ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(sheet1,
			 * new CellRangeAddress(25, 25,2 , 126));
			 * 
			 * LineChartSeries series = data.addSeries(xs, ys1);
			 * series.setTitle("Data");//设置序列名称
			 * 
			 * LineChartSeries series2=data.addSeries(xs, ys2);
			 * series2.setTitle("LSL");//设置序列名称 LineChartSeries series3=data.addSeries(xs,
			 * ys3); series2.setTitle("EE");//设置序列名称
			 * //my_line_chart1.setTitleText("Run Chart");//设置图表标题 my_line_chart.plot(data,
			 * new ChartAxis[] { bottomAxis, leftAxis }); //my_line_chart.plot(data);
			 */
			for (Map<String, Object> m : listMap) {

				// 当数据循环的时候，取出值为key中的value值
				String strSizeSN = m.get("SIZE_SN").toString();
				irow = Integer.parseInt(map.get(strSizeSN));
				int iMonth=Integer.parseInt(varYearMonth.substring(4));

				// 第二行填充，除了模穴不一样，其他都一样
				sheet1.getRow(1).getCell(2).setCellValue(iMonth);// 第2行 第3列，统计月份：
				sheet1.getRow(1).getCell(5).setCellValue(listMap.get(0).get("MOLD_CAVITY_NO").toString());// 第2行 第6列，模穴號
				sheet1.getRow(1).getCell(8).setCellValue(listMap.get(0).get("MOLD_NO").toString());// 第2行 第9列，模號
				sheet1.getRow(1).getCell(13).setCellValue("Cav" + (j + 1));// 第2行 第14列
				sheet1.getRow(1).getCell(16).setCellValue(listMap.get(0).get("PART_NO").toString());// 第2行 第17，料号
				// B列填充，m.get("LOWER_TOLERANCE").toString()
				String strNO = m.get("SIZE_SN").toString();
				Double DouNominal = Double.parseDouble(m.get("STANDARD_VALUE").toString());
				Double DouZTol = Double.parseDouble(m.get("UPPER_TOLERANCE").toString());
				Double DouFTol = Double.parseDouble(m.get("LOWER_TOLERANCE").toString());
				sheet1.getRow(irow + 1).getCell(1).setCellValue(strNO);// 第8行 第2列
				sheet1.getRow(irow + 2).getCell(1).setCellValue(DouNominal);// 第9行 第2列
				sheet1.getRow(irow + 3).getCell(1).setCellValue(DouZTol);// 第10行 第2列
				sheet1.getRow(irow + 4).getCell(1).setCellValue(DouFTol);// 第11行 第2列
				// 对所有Measured Data测量数据填充。
				String[] strsT1 = m.get("MOLD_CAVITY_M_DATA_T1").toString().split(";", -1);
				String[] strsT2 = m.get("MOLD_CAVITY_M_DATA_T2").toString().split(";", -1);
				String[] strsT3 = m.get("MOLD_CAVITY_M_DATA_T3").toString().split(";", -1);
				String[] strsT4 = m.get("MOLD_CAVITY_M_DATA_T4").toString().split(";", -1);
				int intMonth = Integer.parseInt(m.get("MEASURE_DATE").toString().substring(6));// 分解出此行数据是哪日的。
				if (!strsT1[j].isEmpty())
					sheet1.getRow(irow).getCell(intMonth * 4 - 2)
							.setCellValue(Double.parseDouble(strsT1[j].toString()));// 第5行 第7列，分号前第j个数据
				if (!strsT2[j].isEmpty())
					sheet1.getRow(irow).getCell(intMonth * 4 - 2 + 1)
							.setCellValue(Double.parseDouble(strsT2[j].toString()));// 第5行 第7列，分号前第j个数据
				if (!strsT3[j].isEmpty())
					sheet1.getRow(irow).getCell(intMonth * 4 - 2 + 2)
							.setCellValue(Double.parseDouble(strsT3[j].toString()));// 第5行 第7列，分号前第j个数据
				if (!strsT4[j].isEmpty())
					sheet1.getRow(irow).getCell(intMonth * 4 - 2 + 3)
							.setCellValue(Double.parseDouble(strsT4[j].toString()));// 第5行 第7列，分号前第j个数据
				// 白夜班、机台填充，所有Cav1、Cav2...的Date
				String strDayPeopre = StringUtils.isEmpty(m.get("DAY_SHIFT_PERSONNEL"))?"":m.get("DAY_SHIFT_PERSONNEL").toString();// 白班人员
				String strShiftPeopre = StringUtils.isEmpty(m.get("NIGHT_SHIT_PERSONNEL"))?"":m.get("NIGHT_SHIT_PERSONNEL").toString();// 夜班人员
				String strMacNo = m.get("MACHINE_NO").toString();// 机台
				sheet1.getRow(irow - 3).getCell(intMonth * 4 - 2 + 1).setCellValue(strDayPeopre);// 白班填充
				sheet1.getRow(irow - 3).getCell(intMonth * 4 - 2 + 2).setCellValue(strMacNo + "#");// 机台填充
				sheet1.getRow(irow - 3).getCell(intMonth * 4 - 2 + 3).setCellValue(strShiftPeopre);// 夜班填充

			}
			// 月份填充
			int irowMouth2 = 3;// 填充月份的循环，每循环一次加19
			SimpleDateFormat datetemp = new SimpleDateFormat("yyyyMMdd");
			Date DT1 = datetemp.parse(listMap.get(0).get("MEASURE_DATE").toString());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DT1);
			int iDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			for (Object o : setSizeToArray) {
				for (int i = 1; i <= iDay; i++) {
					calendar.set(Calendar.DAY_OF_MONTH, i);
					sheet1.getRow(irowMouth2).getCell(i * 4 - 2).setCellValue(calendar.getTime());// 月份填充
				}
				irowMouth2 += 19;
			}

			// sheet1.shiftRows(41, 668, -1);//删除第一行到第四行，然后使下方单元格上移
			// for(int i=41;i<=59;i++) {
			// removeRow1(sheet,i);
			// sheet.getDrawingPatriarch().getCTDrawing().setNil();
            for (int i = intSizeRowSum+1; i <= rowNum; i++) {
   			 SXSSFRow removingRow=sheet1.getRow(i);
   			 removingRow.setZeroHeight(true);
				
			}

			// sheet1.removeRow(removingRow);

			// sheet1.removeRowBreak(i);

			// }
		
		}
		// System.out.println(k + " : " + m.get(k));
		// }

		// }
		// }
		// }

		/*
		 * sheet.getRow(1).getCell(2).setCellValue(7);//第2行 第3列
		 * sheet.getRow(1).getCell(5).setCellValue("LQ");//第2行 第6列
		 */

		/*
		 * CellCopyPolicy cellCopyPolicy=new CellCopyPolicy();
		 * cellCopyPolicy.setCopyCellStyle(true); cellCopyPolicy.setCopyCellValue(true);
		 * cellCopyPolicy.setCopyMergedRegions(true);
		 * cellCopyPolicy.setCopyRowHeight(true);
		 * cellCopyPolicy.setCopyCellFormula(true);
		 * 
		 * sheet.copyRows(2, 20, 21,cellCopyPolicy);
		 */
/*		int iCavQty=Integer.parseInt(listMap.get(0).get("MOLD_CAVITY_QTY").toString());
		for (int i = iCavQty; i < iSheetSum; i++) {
			
			wb.removeSheetAt(i);
		}*/

		String strMoldNo = listMap.get(0).get("MOLD_NO").toString();
		String strNow = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String strFileName = strMoldNo + "_@SPC_" + varYearMonth + "_" + strNow + ".xlsx";

		
		response.reset();// 清除buffer缓存
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel;charset=utf-8");// 设置contentType为excel格式
		response.setHeader("Content-Disposition", "Attachment;Filename=" + strFileName);
		// 通知客户端响应内容长度为888个字节
		wb.write(output);
		
		
/*} catch (Exception e) {
	logger.info(e.getMessage());
	strReturn= "導出異常！";*/
}finally {

	//request.getSession().setAttribute("endflag", "E");//设置结束标记
	if(in!=null) {
		in.close();
		}
	
	output.flush();
	output.close();
	System.gc();
	
}

		return "OK";

		/*
		 * //修改模板内容导出新模板 FileOutputStream out = new FileOutputStream("D:/export1.xlsx");
		 * wb.write(out);wb.close(); out.close();
		 */

		// return "LinkManage";

	}

	public static void removeRow1(XSSFSheet sheet, int rowIndex) {
		int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		}
		if (rowIndex == lastRowNum) {
			XSSFRow removingRow = sheet.getRow(rowIndex);
			if (removingRow != null) {
				sheet.removeRow(removingRow);
			}
		}
	}
	

	public static XSSFWorkbook getXSSFWorkbook(String filePath) {
		XSSFWorkbook workbook =  null;
		BufferedOutputStream outputStream = null;
		try {
			File fileXlsxPath = new File(filePath);
			outputStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
			workbook = new XSSFWorkbook();
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}
	
	

}
