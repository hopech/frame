package com.silion.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.sicilon.frame.sutils.CollectionUtil;

/**
 * Description: 
 * Author: chenweijia <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年5月19日 上午10:44:55.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class CollectionUtilTest {

	@Test
	public void isBlank(){
		List<String> list= new ArrayList<String>();
		Assert.assertTrue(CollectionUtil.isBlank(list));
		
		List<String> list2 = null;
		Assert.assertTrue(CollectionUtil.isBlank(list2));
	}
	
	@Test
	public void isNotBlank(){
		List<String> list= new ArrayList<String>();
		Assert.assertFalse(CollectionUtil.isNotBlank(list));
		
		list.add("zhangsan");
		Assert.assertTrue(CollectionUtil.isNotBlank(list));
	}
}
