package com.silion.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sicilon.frame.sutils.ChinesUtil;


/** 
* @author : 张博
* @date 创建时间：2017年4月10日 下午1:38:30 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class ChinesUtilTest {
	
	/**
	 * @Title: test_for_Pinyin 
	 * @Description: 测试ChinesUtil getPingYin方法 获取汉字的拼音
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void  test_for_Pinyin (){
		String name = "陈卫佳";
		String pingYin = ChinesUtil.getPingYin(name);
		assertTrue(pingYin.equals("chenweijia"));
		//logger.info(pingYin);
		//2017-04-10 13:46:09.733 [main] INFO  com.sicilon.myspringboot.test.util.ChinesUtilTest - chenweijia
	}
	/**
	 * @Title: test_for_FirstSpell 
	 * @Description: 测试ChinesUtil getFirstSpell方法 获取每个字首字母
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void  test_for_FirstSpell (){
		String name = "陈卫佳";
		String pingYin = ChinesUtil.getFirstSpell(name);
		assertTrue(pingYin.equals("cwj"));
		//logger.info(pingYin);
		//2017-04-10 13:47:41.702 [main] INFO  com.sicilon.myspringboot.test.util.ChinesUtilTest - cwj
	}
	/**
	 * @Title: test_for_FullSpell 
	 * @Description: 测试ChinesUtil getFullSpell方法 获取全拼
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void  test_for_FullSpell (){
		String name = "陈卫佳";
		String pingYin = ChinesUtil.getFullSpell(name);
		assertTrue(pingYin.equals("chenweijia"));
		//logger.info(pingYin);
		//2017-04-10 13:49:37.242 [main] INFO  com.sicilon.myspringboot.test.util.ChinesUtilTest - chenweijia
	}
	/**
	 * @Title: test_for_isChinese 
	 * @Description:测试ChinesUtil isChinese方法 判断有没有中文
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void  test_for_isChinese (){
		String wholename = "陈卫佳";//true
		String sbhname = "ab";//false
		boolean result_wholename = ChinesUtil.isChinese(wholename);
		boolean result_sbhname = ChinesUtil.isChinese(sbhname);
		assertTrue(result_wholename);
		assertEquals(result_sbhname, false);
		//assertTrue(result_sbhname);
		//System.out.println(result_wholename);
		//System.out.println(result_sbhname);
	}
	/**
	 * @Title: test_for_isChineseByREG 
	 * @Description:测试ChinesUtil isChineseByREG方法 判断有没有中文
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void  test_for_isChineseByREG (){
		String wholename = "陈卫佳";//true
		String sbhname = "a卫b";//true
		String engname = "asb";//false
		String noname = "啊啊";//true
		boolean result_wholename = ChinesUtil.isChineseByREG(wholename);
		boolean result_sbhname = ChinesUtil.isChineseByREG(sbhname);
		boolean result_engname = ChinesUtil.isChineseByREG(engname);
		boolean result_noname = ChinesUtil.isChineseByREG(noname);
		assertTrue(result_wholename);
		assertTrue(result_sbhname);
		//assertTrue(result_engname);
		assertEquals(result_engname, false);
		assertTrue(result_noname);
		//System.out.println(result_wholename);
		//System.out.println(result_sbhname);
		//System.out.println(result_engname);
		//System.out.println(result_noname);
	}
}
