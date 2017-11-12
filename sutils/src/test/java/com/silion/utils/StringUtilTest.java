package com.silion.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sicilon.frame.sutils.StringUtil;


/**
 * 
 * description：字符串工具类测试
 * ClassName: StringUtilTest <br/> 
 * date: 2017年4月10日 上午8:55:23 <br/> 
 * @author chen
 */
public class StringUtilTest {

	// 测空
	@Test
	public void blankTest(){
		String str1 = "zhangbo";
		String str2 = "   ";
		String str3 = "";
		String str4 = null;
		
		// 为空测试
		Assert.assertFalse(StringUtil.isBlank(str1));
		Assert.assertTrue(StringUtil.isBlank(str2));
		Assert.assertTrue(StringUtil.isBlank(str3));
		Assert.assertTrue(StringUtil.isBlank(str4));
		
		// 不为空测试
		Assert.assertTrue(StringUtil.isNotBlank(str1));
		Assert.assertFalse(StringUtil.isNotBlank(str2));
		Assert.assertFalse(StringUtil.isNotBlank(str3));
		Assert.assertFalse(StringUtil.isNotBlank(str4));
	}
	
	// 测试大小写
	@Test
	public void firstToUpperLowercase(){
		String name = "cwj";
		String assert_name = "Cwj";
		Assert.assertEquals(StringUtil.firstToUppercase(name), assert_name);
		
		String name2 = "ZB";
		String assert_name2 = "zB";
		Assert.assertEquals(StringUtil.firstToLowerCase(name2), assert_name2);
		
	}
	
	// 判断字符串数组是否包含某元素
	@Test
	public void isIn(){
		String[] strArray = new String[]{"abc","ddf","ouy"};
		String str1 = "r";
		String str2 = "a";
		String str3 = "abc";
		
		Assert.assertFalse(StringUtil.isIn(str1, strArray));
		Assert.assertFalse(StringUtil.isIn(str2, strArray));
		Assert.assertTrue(StringUtil.isIn(str3, strArray));
	}
	
	// 相等判断
	@Test
	public void equals(){
		String name1 = "chen";
		String name2 = "CHEN";
		
		Assert.assertFalse(StringUtil.equals(name1, name2));
		Assert.assertTrue(StringUtil.equalsIgnoreCase(name1, name2));
	}
	
	// 判断是否全部为数字
	@Test
	public void isNum(){
		String num1 = "1234";
		String num2 = "1234K";
		
		Assert.assertTrue(StringUtil.isNum(num1));
		Assert.assertFalse(StringUtil.isNum(num2));
	}
	
	// 去除空格
	@Test
	public void replaceBlank(){
		String str = "	a bc	";
		String assert_trim = "a bc";
		String assert_removeBlank = "abc";
		
		Assert.assertEquals(str.trim(), assert_trim);
		Assert.assertEquals(StringUtil.removeBlank(str), assert_removeBlank);
	}
	
	// list转换
	@Test
	public void changeStringToList(){
		String str = "aaa,bbb,ccc";
		List<String> assert_list = new ArrayList<String>();
		assert_list.add("aaa");
		assert_list.add("bbb");
		assert_list.add("ccc");
		
		List<String> actul_list = StringUtil.changeStringToList(str, ",");
		Assert.assertTrue(assert_list.equals(actul_list));
	}
	
	// list转String
	@Test
	public void changeListToString(){
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		String actual_string = StringUtil.changeListToString(list, ";");
		String assert_string = "aaa;bbb";
		Assert.assertEquals(assert_string, actual_string);
	}
	
	// 从拼接类字符串中删除指定字符串
	@Test
	public void delChildStr(){
		
		String str = "a,b,c,a";
		String child = "a";
		
		String actual_result = StringUtil.delChildStr(str, child,",");
		String assert_result = "b,c";
		Assert.assertEquals(assert_result, actual_result);
		
	}
	
	
	
}