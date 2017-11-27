package com.cc.frame.web.auth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;


/**
 * @ClassName: ACSigner
 * @Description: 签名算法
 * @author: CHENWEIJIA
 * @date: 2017年11月16日
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
	public static String appendBasePararm(String appKey, String accesskey,String method,String version,String nonce,String timestamp) {
		StringBuffer stringToSign = new StringBuffer();
		
		//拼接参数
		stringToSign.append(timestamp).append(appKey).append(accesskey).append(method).append(version).append(nonce);
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
	
}
