package com.silion.utils;


import org.junit.Assert;
import org.junit.Test;

import com.sicilon.frame.sutils.SecurityUtil;
	
/**
 * 
 * description：安全工具类测试
 * ClassName: SecurityUtilTest <br/> 
 * date: 2017年4月10日 下午3:45:50 <br/> 
 * @author chen
 */
public class SecurityUtilTest {

	// 对称加密测试
	@Test
	public void aes() throws Exception{
		String msg = "zhangsan";
		String key = "abcdabcdabcdabcdabcdabcdabcdabce";
		String miwen = SecurityUtil.getA231(key, msg);
		
		// 断言对密文进行解密后和明文相等
		Assert.assertEquals(msg, SecurityUtil.getA232(key, miwen));
		
	}
	
	// sql注入检查
	@Test
	public void isValidSql(){
		
		// 返回fasle为发现sql注入
		String sql1 = "update a set a = 's' ";
		Assert.assertFalse(SecurityUtil.isValidSql(sql1));
		
		String sql2 = "insert into b a values 'a' ";
		Assert.assertFalse(SecurityUtil.isValidSql(sql2));
	}
	
	// 字符串过滤特殊字符
	@Test
	public void stringFlilter(){
		String name = "￥@zhangsan";
		String actual_result = SecurityUtil.stringFlilter(name);
		String assert_result = "zhangsan";
		
		Assert.assertEquals(assert_result, actual_result);
	}
	
	// 检查是否存在特殊字符
	@Test
	public void checkStringFlilter(){
		String name = "￥@zamg";
		Assert.assertTrue(SecurityUtil.existSpeicialChar(name));
		
		String name2 = "zhangsan";
		Assert.assertFalse(SecurityUtil.existSpeicialChar(name2));
	}
}
