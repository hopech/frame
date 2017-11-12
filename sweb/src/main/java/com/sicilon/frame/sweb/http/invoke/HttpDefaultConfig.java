package com.sicilon.frame.sweb.http.invoke;

import org.apache.http.Header;

import com.sicilon.frame.sweb.http.builder.common.HttpHeader;


/**
 * Description: 定义http请求默认的常用配置<br/>
 * Author: CHENWEIJIA<br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年8月30日 下午4:59:07 <br/>
  <br/>UpdateTime：
  <br/>UpdateUser：
  <br/>UpdateNote：
  <br/>------------------------------
 */
public class HttpDefaultConfig {
	/**
	 * 请求头
	 */
	public static Header[] headers = HttpHeader.custom().contentType("application/json;charset=UTF-8").build(); 
	
	/**
	 * 编码格式
	 */
	public static String ENCODING = "utf-8";
}
