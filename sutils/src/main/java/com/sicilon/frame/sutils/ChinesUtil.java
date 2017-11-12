package com.sicilon.frame.sutils;

import java.util.regex.Pattern;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.*;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class ChinesUtil
{

	public ChinesUtil()
	{
	}

	public static String getPingYin(String inputString)
	{
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		char input[] = inputString.trim().toCharArray();
		String output = "";
		try
		{
			for (int i = 0; i < input.length; i++)
				if (Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+"))
				{
					String temp[] = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
					output = (new StringBuilder()).append(output).append(temp[0]).toString();
				} else
				{
					output = (new StringBuilder()).append(output).append(Character.toString(input[i])).toString();
				}

		}
		catch (BadHanyuPinyinOutputFormatCombination e)
		{
			e.printStackTrace();
		}
		return output;
	}

	public static String getFirstSpell(String chinese)
	{
		StringBuffer pybf = new StringBuffer();
		char arr[] = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++)
			if (arr[i] > '\200')
				try
				{
					String temp[] = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
					if (temp != null)
						pybf.append(temp[0].charAt(0));
				}
				catch (BadHanyuPinyinOutputFormatCombination e)
				{
					e.printStackTrace();
				}
			else
				pybf.append(arr[i]);

		return pybf.toString().replaceAll("\\W", "").trim();
	}

	public static String getFullSpell(String chinese)
	{
		StringBuffer pybf = new StringBuffer();
		char arr[] = chinese.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < arr.length; i++)
			if (arr[i] > '\200')
				try
				{
					pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
				}
				catch (BadHanyuPinyinOutputFormatCombination e)
				{
					e.printStackTrace();
				}
			else
				pybf.append(arr[i]);

		return pybf.toString();
	}

	private static boolean isChinese(char c)
	{
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
	}

	public static boolean isChinese(String strName)
	{
		char ch[] = strName.toCharArray();
		for (int i = 0; i < ch.length; i++)
		{
			char c = ch[i];
			if (isChinese(c))
				return true;
		}

		return false;
	}

	public static boolean isChineseByREG(String str)
	{
		if (str == null)
		{
			return false;
		} else
		{
			Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
			return pattern.matcher(str.trim()).find();
		}
	}

	/*public static boolean isChineseByName(String str)
	{
		if (str == null)
		{
			return false;
		} else
		{
			String reg = "\\p{InCJK Unified Ideographs}&&\\P{Cn}";
			Pattern pattern = Pattern.compile(reg);
			return pattern.matcher(str.trim()).find();
		}
	}*/
	

	
//	public static void main(String args[]){
//		
//		String name1 = "san";
//		System.out.println(isChineseByName(name1));
//
//		String strArr[] = { "www.micmiu.com", "!@#$%^&*()_+{}[]|\"'?/:;<>,.", "！￥……（）——：；“”‘’《》，。？、", "不要啊", "やめて",
//				"韩佳人", "???" };
//		String arr$[] = strArr;
//		int len$ = arr$.length;
//		for (int i$ = 0; i$ < len$; i$++) {
//			String str = arr$[i$];
//			System.out.println((new StringBuilder()).append("===========> 测试字符串：").append(str).toString());
//			System.out.println((new StringBuilder()).append("正则判断结果：").append(isChineseByREG(str)).append(" -- ")
//					.append(isChineseByName(str)).toString());
//			System.out.println((new StringBuilder()).append("Unicode判断结果 ：").append(isChinese(str)).toString());
//			System.out.println("详细判断列表：");
//			char ch[] = str.toCharArray();
//			for (int i = 0; i < ch.length; i++) {
//				char c = ch[i];
//				System.out.println(
//						(new StringBuilder()).append(c).append(" --> ").append(isChinese(c) ? "是" : "否").toString());
//			}
//
//		}
//
//	}
}
