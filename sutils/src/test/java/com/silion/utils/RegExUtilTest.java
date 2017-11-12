package com.silion.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

import com.sicilon.frame.sutils.RegExUtil;


/** 
* @author : 张博
* @date 创建时间：2017年4月11日 上午10:21:50 
* @version 1.0 
* @parameter  
* @since  
* @return  
*/
public class RegExUtilTest {
	
	
	
	/**
	 * @Title: test_For_mailValidate 
	 * @Description: 测试邮箱正则
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_mailValidate(){
		String qqmail = "123456123@qq.com";
		String sinamail = "123123123@sina.com";
		String sicilonmail = "chenweijia@sicilon.com";
		// true  true  true
		assertTrue(RegExUtil.mailValidate(qqmail));
		assertTrue(RegExUtil.mailValidate(sinamail));
		assertTrue(RegExUtil.mailValidate(sicilonmail));
		///logger.info("qqmail>>>"+RegExUtil.mailValidate(qqmail));
		//logger.info("sinamail>>>"+RegExUtil.mailValidate(sinamail));
		//logger.info("sicilonmail>>>"+RegExUtil.mailValidate(sicilonmail));
		String  Anti_human = "123456123@gaga1.com";// true  
		assertTrue(RegExUtil.mailValidate(Anti_human));
		//logger.info("Anti_human>>>"+RegExUtil.mailValidate(Anti_human));
		String  Anti_human1 = "123456123";//false
		assertEquals(RegExUtil.mailValidate(Anti_human1), false);
		//logger.info("Anti_human1>>>"+RegExUtil.mailValidate(Anti_human1));
	}
	
	
	/**
	 * @Title: test_For_test_For_cellPhoneValidate 
	 * @Description: 测试手机正则
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_test_For_cellPhoneValidate(){
		String phone = "15513485226";//true
		String Anti_human_phone = "1551348";//false
		assertTrue(RegExUtil.cellPhoneValidate(phone));
		assertEquals(RegExUtil.cellPhoneValidate(Anti_human_phone), false);
		//logger.info("phone>>>>>"+RegExUtil.cellPhoneValidate(phone)+" Anti_human_phone>>>"+RegExUtil.cellPhoneValidate(Anti_human_phone));
	}
	
	
	/**
	 * @Title: test_For_relNameValidate 
	 * @Description: 姓名正则验证
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_relNameValidate(){
		String name = "陈卫佳";String Anti_human_name = "chen卫佳";// true   false
		String name_one = "欧阳大嘎嘎";String Anti_human_name_one = "阿弥陀佛达赖嘎嘎嘎";// true   true
		String name_two = "简";String Anti_human_name_two = "阿弥陀佛达赖嘎嘎嘎萨摩二哈";// false   false
		assertTrue(RegExUtil.relNameValidate(name_one));
		assertTrue(RegExUtil.relNameValidate(Anti_human_name_one));
		assertEquals(RegExUtil.relNameValidate(name_two), false);
		assertEquals(RegExUtil.relNameValidate(Anti_human_name_two), false);
		//logger.info("name_two>>>>>"+RegExUtil.relNameValidate(name_two)+" Anti_human_name_two>>>"+RegExUtil.relNameValidate(Anti_human_name_two));
	}
	
	
	/**
	 * @Title: test_For_qqNumbValidate 
	 * @Description: qq正则验证
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_qqNumbValidate(){
		String qq = "12345"; String Anti_human_qq = "1234";String Anti_human_qq_one = "123123a";// true   false  false
		assertTrue(RegExUtil.qqNumbValidate(qq));
		assertEquals(RegExUtil.qqNumbValidate(Anti_human_qq), false);
		assertEquals(RegExUtil.qqNumbValidate(Anti_human_qq_one), false);
		//logger.info("qq>>>"+RegExUtil.qqNumbValidate(qq)+" Anti_human_qq>>>"+RegExUtil.qqNumbValidate(Anti_human_qq)+" Anti_human_qq_one>>>"+RegExUtil.qqNumbValidate(Anti_human_qq_one));
	}
	
	
	/**
	 * @Title: test_For_ereg 
	 * @Description: 匹配正则 第一个参数为正则 第二个参数为字符串
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_ereg(){
		String ereg_one = "[a-zA-Z]";String ereg_two = "aBcDeF";//true
		String ereg_one1 = "[A-Z0-9]";String ereg_two1 = "aaa";//false
		assertTrue(RegExUtil.isFind(ereg_one1, ereg_two));
		assertEquals(RegExUtil.isFind(ereg_one1, ereg_two1), false);
		//logger.info("ereg>>>"+RegExUtil.isFind(ereg_one1, ereg_two1));
	}
	
	
	/**
	 * @Title: test_For_ereg_replace 
	 * @Description: 旧的字符串匹配正则RegEx 成功后匹配到的每一个old 替换成指定的字符串new
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_ereg_replace(){
		String ereg_replace_RegEx = "[a-zA-Z]"; 
		String ereg_replace_new = "HiJk"; 
		String ereg_replace_old = "Ab3";
		assertEquals(RegExUtil.ereg_replace(ereg_replace_RegEx, ereg_replace_new, ereg_replace_old), "HiJkHiJk3");
		//logger.info("ereg_replace>>>"+RegExUtil.ereg_replace(ereg_replace_RegEx, ereg_replace_new, ereg_replace_old));
	}
	
	
	/**
	 * @Title: test_For_splitTags2Vector 
	 * @Description: 匹配字符串  讲字符串中匹配的 放入Vector ，返回一个 Vector
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_splitTags2Vector(){
		String splitTags2Vector_RegEx = "[a-zA-Z]";
		String splitTags2Vector_str = "asfdasfasdfsdfsf123asdfadsfsd";
		Vector vector = RegExUtil.splitTags2Vector(splitTags2Vector_RegEx,splitTags2Vector_str);
		//String 
		for (Object object : vector) {
		}
	}
	
	/**
	 * @Title: test_For_regMatchAll2Vector
	 * @Description: 匹配字符串  讲字符串中匹配的 放入Vector ，返回一个 Vector
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_regMatchAll2Vector(){
		String splitTags2Vector_RegEx = "[a-zA-Z]";
		String splitTags2Vector_str = "asfdasfasdfsdfsf123asdfadsfsd";
		Vector vector = RegExUtil.regMatchAll2Vector(splitTags2Vector_RegEx,splitTags2Vector_str);
		for (Object object : vector) {
		}
	}
	
	
	/**
	 * @Title: test_For_escapeDollarBackslash 
	 * @Description: 针对特殊字符转义
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_escapeDollarBackslash(){				//转义之后
		String str = "asdf@@#!$$%%adasfAASDFXc1239123$";	//asdf@@#!\$\$%%adasfAASDFXc1239123\\\\\$
		assertEquals(RegExUtil.escapeDollarBackslash(str), "asdf@@#!\\$\\$%%adasfAASDFXc1239123\\$");
		//logger.info("regMatchAll2Array>>>"+RegExUtil.escapeDollarBackslash(str));
	}
	
	
	/**
	 * @Title: test_For_matchAll 
	 * @Description: 匹配正则表达式匹对的  返回字符串数组 matchAll选用三个参数的方法，两个参数不好使
	 * @param    
	 * @return void    
	 * @throws
	 */
	@Test
	public void test_For_matchAll(){
		String str = "chenweijia@sicilon.com";String reges = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		String[] matchAll = RegExUtil.matchAll(str,reges,2);
		for (String string : matchAll) {
			assertEquals(string, "sicilon.");
			//logger.info("matchAll>>>"+string);
		}
	}
}
