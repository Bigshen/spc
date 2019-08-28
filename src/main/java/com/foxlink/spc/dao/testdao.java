package com.foxlink.spc.dao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import sun.misc.BASE64Decoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.foxlink.spc.pojo.employ;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;



@Repository("testDAO")
public class testdao {
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public testdao(JdbcTemplate jdbcTemplate) {
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
	public void test(){
		System.out.println("进入dao方法了");
	}

	public String getEmp(String id) {
		// 创建Spring对象
	    String config = "/spring/applicationContext.xml";
	    ApplicationContext app = new ClassPathXmlApplicationContext(config);
	    // 创建template
	    JdbcTemplate template = app.getBean("jdbcTemplate", JdbcTemplate.class);
	    
		String sql = "select t.name,t.id,t.costid from csr_employee t where t.id = ?";
		
		// 创建Mapper，BeanPropertyRowMapper传入对象类，将会自动映射结果
        RowMapper<employ> mapper = new BeanPropertyRowMapper<>(employ.class);
        
        Object a[] = new Object[1];
        a[0] = id;
 
        // 查询结果query(sql语句，Mapper对象); 会返回List集合
        List<employ> persons = template.query(sql, a,mapper);
		
		return persons.toString();
	}
	public boolean CheckAccount(String userName,String Password) {
		int totalRecord=-1;
    	String sSQL = "SELECT COUNT(*) FROM SWIPE.USER_DATA WHERE username = ? AND PASSWORD = ?";
    	System.out.println(" 查詢語句"+sSQL+"賬號"+userName+"密碼"+Password);
    	try {    	    	
    		totalRecord = jdbcTemplate.queryForObject(sSQL, new Object[] { userName, Password},Integer.class);
    		System.out.println("totalRecord:"+totalRecord);
    	  } catch (Exception ex) {
    		  ex.printStackTrace();
    		  }
    	/*System.out.println(sSQL);*/
    	 if(totalRecord > 0) 
			   return true; 
		 else
			 return false;
		
		
	}
	
	//ErWeiCode
	public String ErWeiCode(File file) {
		
		 MultiFormatReader formatReader = new MultiFormatReader();
		 //"D:\test.png"
	        //File file = new File(filePath);
	        String result1 = "";
	        try {
	        	System.out.println("路径地址===="+file);
	        	//图片压缩处理(缩放+质量压缩以减小高宽度及数据量大小)
		        imageResize(file,file,600,600,0.1f);//宽度大于1200的,缩放为1200,高度大于3000的缩放为3000,按比例缩放,质量压缩掉10%
	            BufferedImage image = ImageIO.read(file);
	            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
	            HashMap hints = new HashMap();
	            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	            Result result = formatReader.decode(binaryBitmap, hints);
	            result1 = result.toString();
	            //String str = "www-runoob-com";
	            String[] temp;
	            String delimeter = "/";  // 指定分割字符
	            temp = result1.split(delimeter); // 分割字符串
	            // 普通 for 循环
	            for(int i =0; i < temp.length ; i++){
	               System.out.println(temp[i]);
	               System.out.println("");
	            }
	            System.out.println("解析结果： " + result.toString());
	            System.out.println("二维码的格式类型：" + result.getBarcodeFormat());
	            System.out.println("二维码文本内容： " + result.getText() );
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		return result1;

	}

	/**
	 2      * 缩放图片(压缩图片质量，改变图片尺寸)
	 3      * 若原图宽度小于新宽度，则宽度不变！
	 4      * @param newWidth 新的宽度
	 5      * @param quality 图片质量参数 0.7f 相当于70%质量
	 6      */
	      public static void imageResize(File originalFile, File resizedFile,
	                                int maxWidth,int maxHeight, float quality) throws IOException {
	  
	         if (quality > 1) {
	             throw new IllegalArgumentException(
	                     "图片质量需设置在0.1-1范围");
	         }
	 
	         ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
	         Image i = ii.getImage();
	         Image resizedImage = null;
	 
	         int iWidth = i.getWidth(null);
	         int iHeight = i.getHeight(null);
	 
	         int newWidth = maxWidth;
	         if(iWidth < maxWidth){
	             newWidth = iWidth;
	         }
	
	 
	         if (iWidth >= iHeight) {
	            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)
	                     / iWidth, Image.SCALE_SMOOTH);
	         }
	 
	 
	 
	         int newHeight = maxHeight;
	         if(iHeight < maxHeight){
	             newHeight = iHeight;
	         }
	 
	         if(resizedImage==null && iHeight >= iWidth){
	             resizedImage = i.getScaledInstance((newHeight * iWidth) / iHeight,
	                     newHeight, Image.SCALE_SMOOTH);
	         }
	 
	         // This code ensures that all the pixels in the image are loaded.
	         Image temp = new ImageIcon(resizedImage).getImage();
	 
	         // Create the buffered image.
	         BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
	                 temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
	 
	         // Copy image to buffered image.
	         Graphics g = bufferedImage.createGraphics();
	 
	         // Clear background and paint the image.
	        // g.setColor(Color.WHITE);
	         g.setColor(java.awt.Color.white);
	         g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
	         g.drawImage(temp, 0, 0, null);
	         g.dispose();
	 
	         // Soften.
	         float softenFactor = 0.05f;
	         float[] softenArray = { 0, softenFactor, 0, softenFactor,
	                 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
	         Kernel kernel = new Kernel(3, 3, softenArray);
	         ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
	         bufferedImage = cOp.filter(bufferedImage, null);
	 
	         // Write the jpeg to a file.
	         FileOutputStream out = new FileOutputStream(resizedFile);
	 
	         // Encodes image as a JPEG data stream
	         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	 
	         JPEGEncodeParam param = encoder
	                 .getDefaultJPEGEncodeParam(bufferedImage);
	 
	         param.setQuality(quality, true);
	 
	         encoder.setJPEGEncodeParam(param);
	         encoder.encode(bufferedImage);
	     } // Example usage
}
