package com.sicilon.frame.sutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 
 * description：配置文件操作
 * ClassName: PropFileUtil <br/> 
 * date: 2017年3月31日 下午5:16:03 <br/> 
 * @author chen
 */
public class PropertiesUtil {

	/**
	 * 根据键值获取配置文件参数值
	 * 
	 * @param fileName
	 *            配置文件
	 * @param key
	 *            键值
	 * @return
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public static String getClasspathPropValue(String fileName, String key){
		String value = "";
		InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
		
		Properties prop = new Properties();

		try {
			prop.load(new InputStreamReader(inputStream, "utf-8"));
		} catch (IOException e) {
			return value;
		}
		
		value = prop.getProperty(key);
		return value;
	}
	
	/**
	 * 以文件形式读取Properties文件值
	 * @param file 文件
	 * @param key 键
	 * @return 键对应的值
	 * @throws UnsupportedEncodingException IOException
	 */ 
	public static String getPropValueByFile(File file,String key) throws Exception{
		FileInputStream inputStream = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(new InputStreamReader(inputStream, "utf-8"));
		return prop.getProperty(key);
	}
	
	
	/**
	 * 给指定配置文件的指定键设置值
	 * @param fileName 文件名称
	 * @param key 键
	 * @param value 值
	 */
	public static void setValue(String fileName,String key, String value,String comment) {
		String path = PropertiesUtil.class.getClassLoader().getResource(fileName).getPath();
		Properties pro = new Properties();
		try {
//			pro.load(new FileInputStream(path));
//			for (Enumeration<?> e = pro.propertyNames(); e.hasMoreElements();) {
//				String s = (String) e.nextElement(); // 遍历所有元素
//				if (s.equals(key)) {
//					/** 如果键相同则覆盖 */
//					pro.setProperty(key, value);
//				} else {
//					/** 否则就原样写入 */
//					pro.setProperty(s, pro.getProperty(s));
//				}
//			}
			pro.put(key, value);
			pro.store(new FileOutputStream(path), comment);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写入文件信息
	 * @param file 文件
	 * @param key 键
	 * @param value 值
	 * @param comment 注释
	 */
	@SuppressWarnings("resource")
	public static void setValueByFile(File file,String key, String value,String comment) {
		
		Properties pro = new Properties();
		try {
			pro.load(new FileInputStream(file));
//			for (Enumeration<?> e = pro.propertyNames(); e.hasMoreElements();) {
//				String s = (String) e.nextElement(); // 遍历所有元素
//				if (s.equals(key)) {
//					/** 如果键相同则覆盖 */
//					pro.setProperty(key, value);
//				} else {
//					/** 否则就原样写入 */
//					pro.setProperty(s, pro.getProperty(s));
//				}
//			}
//			pro.store(new FileOutputStream(file),key+"="+value);
			pro.put(key, value);
			pro.store(new FileOutputStream(file),comment);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 打开一个文件流
	 * 
	 * @param fileName
	 *            文件名
	 * @return 配置文件对象
	 */
	public static Properties loadFile(String fileName) {
		InputStream inputStream = PropertiesUtil.class.getClassLoader()
				.getResourceAsStream(fileName);
		Properties prop = new Properties();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			prop.load(reader);
		} catch (Exception e) {
			System.err.println("项目中未找到属性文件" + fileName);
		}
		return prop;
	}
}
