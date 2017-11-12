package com.silion.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sicilon.frame.sutils.ArrayUtil;

/**
 * 
 * description：数组Util
 * ClassName: ArrayUtil <br/> 
 * date: 2017年4月12日 上午10:34:00 <br/> 
 * @author chen
 */
public class ArrayUtilTest {

	// 数字数组转换字符串数组
	@Test
	public void integerArrToStringArr(){
		
		/*
		 * 测试转换
		 */
		Integer[] intArray = {1,2,3};
		String[] actual_result = ArrayUtil.integerArrToStringArr(intArray);
		// 断言转换后的数组为 String[]{"1","2","3"} 
		String[] assert_result = {"1","2","3"};
		
		Assert.assertArrayEquals(assert_result, actual_result);
		
		/*
		 * 测空
		 */
		Integer[] intArray2 = null;
		String[] assert_result2 = ArrayUtil.integerArrToStringArr(intArray2);
		// 断言转换后的数组为 String[]{} 
		String[] actual_result2 = new String[]{};
		Assert.assertArrayEquals(assert_result2, actual_result2);
		
	}
	
	// 字符串数组转换数字数组
	@Test
	public void stringArrToIntegerArr(){
		String[] strArray = {"1","2"};
		Integer[] assert_result = ArrayUtil.stringArrToIntegerArr(strArray);
		// 断言转换后的数组为 Integer[]{1,2} 
		Integer[] actual_result = {1,2};
		
		Assert.assertArrayEquals(assert_result, actual_result);
	}
	
	// 字符串数组转换list
	@Test
	public void strArrayToList(){
		String[] strArray = {"chen","zhang"};
		List<String> actual_result = ArrayUtil.strArrayToList(strArray);
		
		List<String> assert_result = new ArrayList<String>();
		assert_result.add("chen");
		assert_result.add("zhang");
		
		Assert.assertTrue(assert_result.equals(actual_result));
	}
	
	// Integer数组转换list
	@Test
	public void integerArrayToList(){
		
		/*
		 * 测试转换
		 */
		Integer[] integerArray = {1,2,3};
		List<Integer> actual_result = ArrayUtil.integerArrayToList(integerArray);
		List<Integer> assert_result = new ArrayList<Integer>();
		assert_result.add(1);
		assert_result.add(2);
		assert_result.add(3);
		
		Assert.assertTrue(actual_result.equals(assert_result));
		
		/*
		 * 测空
		 */
		Integer[] integerArray2 = null;
		List<Integer> actual_result2 = ArrayUtil.integerArrayToList(integerArray2);
		List<Integer> assert_result2 = new ArrayList<Integer>();
		Assert.assertTrue(actual_result2.equals(assert_result2));
	}
}
