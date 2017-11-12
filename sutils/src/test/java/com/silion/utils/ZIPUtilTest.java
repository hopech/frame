package com.silion.utils;

import java.io.File;

import org.junit.Test;

import com.sicilon.frame.sutils.ZIPUtil;


public class ZIPUtilTest {
	
	
	
	
	/**
	 * 压缩
	 */
	@Test
	public void test_For_deCompress(){
		String  targer = "C:/Users/Administrator/Desktop/java规范.zip";
		try {
			ZIPUtil.deCompress(new File("C:/Users/Administrator/Desktop/java规范"), targer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 解压缩
	 */
	@Test
	public void test_For_unCompress(){
		String  targer = "C:/Users/Administrator/Desktop";
		try {
			ZIPUtil.unCompress(new File("C:/Users/Administrator/Desktop/java规范.zip"), targer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
