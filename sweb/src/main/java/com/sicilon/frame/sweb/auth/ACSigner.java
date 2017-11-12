package com.sicilon.frame.sweb.auth;

import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;



/**
 * Description: 生成签名算法
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年5月10日 下午4:08:04.<br/>
 * <br/>
 * UpdateTime： <br/>
 * UpdateUser： <br/>
 * UpdateNote： <br/>
 * ------------------------------
 */
public class ACSigner {

	private static final String ENCODING = "UTF-8";
	private static final String HASH = "HmacSHA256";

	/**
	 * 拼接公共参数
	 * @param appKey 应用key
	 * @param accesskey 接收key
	 * @param method 方法名
	 * @param version 版本号
	 * @param nonce 盐
	 * @param timestamp 时间戳 
	 * @return 拼接后公共参数
	 */
	public static String appendBasePararm(String appKey, String accesskey,String method,String version,String nonce,long timestamp) {
		StringBuffer stringToSign = new StringBuffer();
		
		//拼接参数
		stringToSign.append(String.valueOf(timestamp)).append(appKey).append(accesskey).append(method).append(version).append(nonce);
		return stringToSign.toString();
	}

	/**
	 * 生成签名方法
	 * 
	 * @param sk
	 *            secretKey
	 * @param basePararm
	 *            拼接好的公共参数
	 * @return 签名
	 * @throws Exception
	 *             异常信息
	 */
	public static String getSignature(String sk, String basePararm) throws Exception {
		String signature = "";
		String encodedSign = URLEncoder.encode(basePararm, ENCODING);
		Mac mac = Mac.getInstance(HASH);
		mac.init(new SecretKeySpec(sk.getBytes(ENCODING), HASH));
		byte[] hashData = mac.doFinal(encodedSign.getBytes(ENCODING));
		StringBuilder sb = new StringBuilder(hashData.length * 2);
		for (byte data : hashData) {
			String hex = Integer.toHexString(data & 0XFF);
			if (hex.length() == 1) {
				sb.append("0");
			}
			sb.append(hex);
		}
		signature = sb.toString().toLowerCase();
		return signature;
	}
	
	
	public static void main(String[] args) throws Exception {
//		String appKey = "ucenter";
//		String accessKey = "yrVqD9sp4$Fb%f4@w$TU@mes0pr2!*^k";
//		String method = "add";
//		String version = "1.0";
//		String nonce = "aaa";
//		long timestamp = DateTimeUtil.getNowUtcDate().getTime();
//		System.out.println(timestamp);
//		
//		String appendBasePararm = appendBasePararm(appKey, accessKey, method, version, nonce, timestamp);
//		System.out.println(getSignature(SignKey.getSk(accessKey), appendBasePararm));
		
//		String signature = getSignature("7PBE*O5M@tc3xy4PPfgoU@r04L4Gx7uR", "1504762975524ycweb@JZcc14L^PN6NjZ%PlSZsitH3xkDBoh8finance.receipt.spotnotpay.query1.0461043");
		
		
		String result = getSignature("7PBE*O5M@tc3xy4PPfgoU@r04L4Gx7uR", "1504762975524ycweb@JZcc14L^PN6NjZ%PlSZsitH3xkDBoh8finance.receipt.spotnotpay.query1.0461043");
		
		System.out.println(result);
		
	}
}
