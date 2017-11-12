package com.silion.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.sicilon.frame.sutils.DateTimeUtil;

/**
 * 
 * description：日期测试
 * ClassName: DateTimeUtilTest <br/> 
 * date: 2017年4月10日 上午9:48:35 <br/> 
 * @author chen
 */
public class DateTimeUtilTest {

	// 获取字符串时间对应的date
	@Test
	public void getDateTimeByStr() throws ParseException{
		String dateStr1 = "1993-09-25";
		String dateStr2 = "1993-09-25 06:16:16";
		
		Date actual_date1 = DateTimeUtil.parseDate(dateStr1).toDate();
		Calendar c1 = Calendar.getInstance();
		c1.set(1993, 8, 25,0,0,0);
		Date assert_date1 = c1.getTime();
		
		Assert.assertEquals(assert_date1.toString(), actual_date1.toString());
		
		Date actual_date2 = DateTimeUtil.parseDateTime(dateStr2).toDate();
		Calendar c2 = Calendar.getInstance();
		c2.set(1993, 8, 25, 6, 16, 16);
		Date assert_date2 = c2.getTime();
		
		Assert.assertEquals(assert_date2.toString(), actual_date2.toString());
		
		Date actual_date3 = DateTimeUtil.parseDateTime(dateStr2, "yyyy-MM-dd HH:mm").toDate();
		Calendar c3 = Calendar.getInstance();
		c3.set(1993, 8, 25, 6, 16,0);
		Date assert_date3 = c3.getTime();
		
		Assert.assertEquals(assert_date3.toString(), actual_date3.toString());
	}
	
	// 获取date类型对应的字符串
	@Test
	public void getDateTimeByDate() throws ParseException {
		// 自定义时间
		Calendar c = Calendar.getInstance();
		c.set(1993, 9, 25);
		Date date1 = c.getTime();
		
		
		DateTimeUtil dateTime = new DateTimeUtil(date1);
		
		// yyyy-MM-dd 格式转换 yyyy-MM-dd
		String actual_result1 = dateTime.toDateString();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String assert_result1 = dateFormat.format(date1);
		Assert.assertEquals(assert_result1, actual_result1);
		
		// yyyy-MM-dd 格式转换 yyyy-MM-dd HH:mm:ss
		String actual_result2 = dateTime.toDateTimeString();
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String assert_result2 = dateFormat2.format(date1);
		Assert.assertEquals(assert_result2, actual_result2);
		
		
		// 自定义parttern
		String actual_result3 = dateTime.toDateTimeString("yyyy-MM-dd HH:mm");
		DateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String assert_result3 = dateFormat3.format(date1);
		Assert.assertEquals(assert_result3, actual_result3);
		
	}
	
	
}
