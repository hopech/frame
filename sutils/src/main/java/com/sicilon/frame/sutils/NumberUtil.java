package com.sicilon.frame.sutils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Description: 数值计算工具类<br/>
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年7月13日 上午9:02:12 <br/>
 * <br/>
 * UpdateTime： <br/>
 * UpdateUser： <br/>
 * UpdateNote： <br/>
 * ------------------------------
 */
public class NumberUtil {

	/**
	 * 两数相加
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 两数相减
	 * @param v1 被减数
	 * @param v2 减数
	 * @return
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 两数相乘
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mul(double v1, double v2) {// 乘法
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 两数相除
	 * @param v1 被除数
	 * @param v2 除数
	 * @param round 保留的位数
	 * @return
	 */
	public static double div(double v1, double v2, int round) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, round, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 
	 * @param v 需要计算的数
	 * @param round 保留的位数
	 * @return
	 */
	public static double round(double v, int round) {
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, round, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 去除小数点
	 * @param value
	 * @return
	 */
	public static String decimalBlankFormat(double value) {
		return new DecimalFormat("0").format(value);
	}
	
}
