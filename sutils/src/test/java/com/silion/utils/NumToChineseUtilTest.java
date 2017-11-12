package com.silion.utils;

import org.junit.Assert;
import org.junit.Test;

import com.sicilon.frame.sutils.MoneyUtil;


/**
 * 
 * description：数字转换成中文工具类
 * ClassName: NumToChineseUtil <br/> 
 * date: 2017年4月10日 下午1:48:54 <br/> 
 * @author chen
 */
public class NumToChineseUtilTest {

	@Test
	public void changeChinese(){
		double d = 1200.01;
		String actual_result = MoneyUtil.toChineseMoney(d);
		String assert_result = "壹仟贰佰元零壹分";
		Assert.assertEquals(assert_result, actual_result);
	}
}
