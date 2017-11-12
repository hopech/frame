package com.silion.utils;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Test;

import com.sicilon.frame.sutils.CharsetUtil;
import com.sicilon.frame.sutils.DebugOut;


/**
 * 
 * description：字符串编码工具类 ClassName: CharsetUtil <br/>
 * date: 2017年4月10日 下午5:01:28 <br/>
 * 
 * @author chen
 */
public class CharsetUtilTest {

	@Test
	public void toISO_8859_1() throws UnsupportedEncodingException {
		String name = "陈";

		// 转换编码后
		DebugOut.println(CharsetUtil.toISO_8859_1(name));
	}

	@Test
	public void toASCII() throws UnsupportedEncodingException {
		String name = "陈";

		// 转换编码前
		DebugOut.println(name);

		// 转换编码后
		DebugOut.println(CharsetUtil.toASCII(name));
	}

	@Test
	public void toUTF_8() throws UnsupportedEncodingException {
		String name = "陈";

		// 转换编码前
		DebugOut.println(name);
		// 转换编码后
		DebugOut.println(CharsetUtil.toUTF_8(name));
	}

	@Test
	public void toGBK() throws UnsupportedEncodingException {
		String name = "陈";

		// 转换编码前
		DebugOut.println(name);

		// 转换编码后
		DebugOut.println(CharsetUtil.toGBK(name));
	}
	
	@Test
	public void gbkToUtf8() throws UnsupportedEncodingException{
		String a="张A中a";
		String name = new String(a.getBytes("utf-8"),"gbk");
		
		String utf8Name = new String(a.getBytes(),"utf-8");
		String cnvert = CharsetUtil.changeCharset(name, "gbk", "utf-8");
		Assert.assertEquals(utf8Name, cnvert);
		
	}
}
