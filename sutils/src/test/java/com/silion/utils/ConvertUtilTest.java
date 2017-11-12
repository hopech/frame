package com.silion.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.sicilon.frame.sutils.ConvertUtil;


/**
 * 
 * description：类型转换工具类测试
 * ClassName: ConvertUtilTest <br/> 
 * date: 2017年4月12日 下午4:04:04 <br/> 
 * @author chen
 */
public class ConvertUtilTest {

	// 对象转换map
	@Test
	public void beanToMap() throws Exception{
		Student stu = new Student("zhangsan","lanqiu");
		Map<String, String> actual_map = ConvertUtil.beanToMap(stu);
		
		Map<String, String> assert_map = new HashMap<String, String>();
		Assert.assertEquals(assert_map, actual_map);
	}
	
	// 将map序列化转换测试
	@Test
	public void seriMap(){
		Map<String ,Object> map = new HashMap<String ,Object>(); 
		Student stu1 = new Student("zhangsan","lanqiu");
		Student stu2 = new Student("LISI","lanqiu");
		map.put("stu1", stu1);
		map.put("stu2", stu2);
		
		Map<byte[], byte[]> byteMap = ConvertUtil.objMapToSeriMap(map);
		
		Map<String, Object> objMap = ConvertUtil.seriMapToObjMap(byteMap);
		
		Assert.assertEquals(map, objMap);
	}
	
	// 将string类型转换为boolean类型
	@Test
	public void stringToBoolean(){
		String a = "true";
		String b = "false";
		String c = "aaa";
		
		Assert.assertTrue(ConvertUtil.stringToBoolean(a));
		Assert.assertFalse(ConvertUtil.stringToBoolean(b));
		Assert.assertFalse(ConvertUtil.stringToBoolean(c));
		
	}
	
	// short和byte相互转换
	@Test
	public void shortByte(){
		short a = 1;
		byte[] byteShort = ConvertUtil.shortToByte(a);
		
		Assert.assertEquals(a, ConvertUtil.byteToShort(byteShort));
		
	}
	
	// long和byte相互转换
	@Test
	public void longByte(){
		long a = 32323232;
		
		byte[] longToByte = ConvertUtil.longToByte(a);
		
		Assert.assertEquals(a, ConvertUtil.byteToLong(longToByte));
	}
	
	// int和byte相互转换
	@Test
	public void intByte(){
		int a = 7888;
		byte[] intToByte = ConvertUtil.intToByte(a);
		
		Assert.assertEquals(a, ConvertUtil.bytesToInt(intToByte));
	}
	
}
