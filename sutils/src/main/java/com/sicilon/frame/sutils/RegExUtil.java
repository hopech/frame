package com.sicilon.frame.sutils;

import java.util.*;
import java.util.regex.*;

/**
 * @author 张博
 * @date 2017年4月11日 下午4:04:10
 * @Description: 正则工具类
 */
public class RegExUtil {
	
	/**
	 * 判断传入字符串是否与规则全部匹配
	 * @param str 要判断的字符串
	 * @param reg 规则
	 * @return
	 */
	public static boolean isMatche(String reg,String str) {
		Pattern pattern = Pattern.compile(reg);
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}

	/**
	 * 判断传入字符串是否与规则部分匹配
	 * @param str 要判断的字符串
	 * @param reg 规则
	 * @return
	 * @throws PatternSyntaxException
	 */
	public static final boolean isFind(String reg, String str) throws PatternSyntaxException {
		try {
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(str);
			return m.find();
		} catch (PatternSyntaxException e) {
			throw e;
		}
	}
	
	/**
	 * @Title: test_For_mailValidate 
	 * @Description: 邮箱正则
	 * @param    
	 * @return void    
	 * @throws
	 */
	public static boolean mailValidate(String mail) {
		return Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$")
				.matcher(mail).find();
	}

	/**
	 * @Title: test_For_test_For_cellPhoneValidate 
	 * @Description: 手机正则
	 * @param    
	 * @return void    
	 * @throws
	 */
	public static boolean cellPhoneValidate(String cellPhone) {
		return Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$").matcher(cellPhone).find();
	}
	
	
	/**
	 * @Title: relNameValidate 
	 * @Description: 真实姓名正则验证
	 * @param @param relName
	 * @param @return   
	 * @return boolean    
	 * @throws
	 */
	public static boolean relNameValidate(String relName) {
		return Pattern.compile("^[\u4e00-\u9fa5]{2,10}$").matcher(relName)
				.find();
	}

	/**
	 * @Title: qqNumbValidate 
	 * @Description: QQ 正则验证
	 * @param @param qqNumb
	 * @param @return   
	 * @return boolean    
	 * @throws
	 */
	public static boolean qqNumbValidate(String qqNumb) {
		return Pattern.compile("^[1-9][0-9]{4,9}$").matcher(qqNumb).find();
	}
	
	/**
	 * 验证是否为全英文
	 * @param str 要检查的字符串
	 * @return 真假值
	 */
	public static boolean isAllEnglish(String str){
		String reg = "^[A-Za-z]+$";
		return Pattern.compile(reg).matcher(str).find();
	}
	
	/**
	 * 验证是否包含中文字符
	 * @param str 要检查的字符串
	 * @return 真假值
	 */
	public static boolean hasChinese(String str){
		String reg = "[\u4e00-\u9fa5]";
		return Pattern.compile(reg).matcher(str).find();
	}
	
	/**
	 * 判断是否为邮政编码
	 * @param str 要检查的字符串
	 * @return 真假值
	 */
	public static boolean isPostCode(String str){
		String reg = "[1-9]d{5}(?!d)";
		return Pattern.compile(reg).matcher(str).find();
	}
	
	/**
	 * 判断是否是身份证规则
	 * @param str 要检查的字符串
	 * @return 真假值
	 */
	public static boolean isIdCard(String str){
		String reg = "d{15}|d{18}";
		return Pattern.compile(reg).matcher(str).find();
	}
	
	/**
	 * 判断是否是ip地址
	 * @param str 要检查的字符串
	 * @return 真假值
	 */
	public static boolean isIP(String str){
		String reg = "d+.d+.d+.d+";
		return Pattern.compile(reg).matcher(str).find();
		
	}
	
	/**
	 * 判断是否是非负整数
	 * @param str 要检查的字符串
	 * @return 真假值
	 */
	public static boolean isNonNegativeInteger(String str){
		String reg = "^[1-9]d*|0$";
		return Pattern.compile(reg).matcher(str).find();
	}
	
	/**
	 * 判断是否是日期格式
	 * @param str 要检查的字符串
	 * @return 真假值
	 */
	public static boolean isDate(String str) {
		String reg = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		return Pattern.compile(reg).matcher(str).find();
	}
	

  /**
   * 
   * @Title: ereg_replace 
   * @Description: 匹配且替换字串 第一个参数为 正则  将要替换的  匹配的字符串
   * @param @param pattern 正则
   * @param @param newstr 要替换的字符串
   * @param @param str 需要被替换的字符串
   * @param @return
   * @param @throws PatternSyntaxException   
   * @return String    
   * @throws
   */
  public static final String ereg_replace(String pattern, String newstr, String str)  throws PatternSyntaxException
  {
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      return m.replaceAll(newstr);
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }

  /**
   * @Title: splitTags2Vector 
   * @Description: 主要用于模板中模块标记分析函数 把查找到的元素加到vector中
   * @param @param pattern 正则
   * @param @param str 需要匹配的字符串
   * @param @return
   * @param @throws PatternSyntaxException   
   * @return Vector   
   * @throws
   */
  public static final Vector splitTags2Vector(String pattern, String str) throws PatternSyntaxException
  {
    Vector vector = new Vector();
    try {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      while (m.find())
      {
        vector.add(ereg_replace("(\\[\\#)|(\\#\\])", "", m.group()));
      }
      return vector;
    }
    catch (PatternSyntaxException e) {
      throw e;
    }
  }


  /**
   * @Title: regMatchAll2Vector 
   * @Description: 匹配所有符合模式要求的字串并加到矢量vector数组中
   * @param @param pattern 正则表达式
   * @param @param str 原始字串
   * @param @return
   * @param @throws PatternSyntaxException   
   * @return Vector    数组
   * @throws
   */
  public static final Vector regMatchAll2Vector(String pattern, String str) throws PatternSyntaxException
  {
    Vector vector = new Vector();
    try
    {
      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(str);
      while (m.find())
      {
        vector.add(m.group());
      }
      return vector;
    }
    catch (PatternSyntaxException e)
    {
      throw e;
    }
  }

 
  /**
   * @Title: escapeDollarBackslash 
   * @Description: 转义特殊字符(之所以需要将\和$字符用escapeDollarBackslash方法的方式是因为用repalceAll是不行的，简单的试试"$".repalceAll("\\$","\\\\$")你会发现这个调用会导致数组越界错误)
   * @param @param original
   * @param @return   
   * @return String  返回转义后的字符串 例（前：@@#!$$ 后：@@#!\$\$）
   * @throws
   */
  public static String escapeDollarBackslash(String original) {
      StringBuffer buffer=new StringBuffer(original.length());
      for (int i=0;i<original.length();i++) {
        char c=original.charAt(i);
        if (c=='\\'||c=='$') {
          buffer.append("\\").append(c);
        } else{
          buffer.append(c);
        }
      }
      return buffer.toString();
    }



	/**
	 * @Title: matchAll 
	 * @Description: 正则表达式匹配
	 * @param @param str 需要匹配的字符串
	 * @param @param reges 要匹配的正则
	 * @param @param group 获取组的指标
	 * @param @return   
	 * @return String[] 返回匹配对应的值
	 * @throws
	 */
	public static String[] matchAll(String str, String reges, int group) throws IndexOutOfBoundsException{
		Pattern p = Pattern.compile(reges);
		Matcher m = p.matcher(str);
		List<String> list = new ArrayList<String>();
		while (m.find()) {
			list.add(m.group(group));
		}
		return list.toArray(new String[list.size()]);
	}
	
	
	
}
