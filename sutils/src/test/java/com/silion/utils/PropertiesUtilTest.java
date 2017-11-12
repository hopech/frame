package com.silion.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.sicilon.frame.sutils.CharsetUtil;
import com.sicilon.frame.sutils.DebugOut;
import com.sicilon.frame.sutils.PropertiesUtil;


/**
 * 
 * description：配置文件操作工具测试类
 * ClassName: PropertiesUtil <br/> 
 * date: 2017年4月12日 上午9:29:13 <br/> 
 * @author chen
 */
public class PropertiesUtilTest {

	// 读取配置文件的键
	@Test
	public void getPropValue() throws Exception{ 
//		String fileName = "web.properties";
		String fileName = "src/test/java/test.properties";
		String result = PropertiesUtil.getClasspathPropValue(fileName, "key");
		DebugOut.print(result);
	}
	
	// 修改配置文件信息
	@Test
	public void setValue(){
		String fileName = "log/test.properties";
		PropertiesUtil.setValueByFile(new File(fileName), "server.port", "8082","aa");
	}
}
