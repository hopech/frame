package com.cc.frame.web.auth;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SignKey
 * @Description: 保存accessKey和secretKey的对应关系
 * @author: CHENWEIJIA
 * @date: 2017年11月16日
 */
public class SignKey {

	
	private static Map<String, String> keyData = new HashMap<String, String>();

	static {
		
		keyData.put("yrVqD9sp4$Fb%f4@w$TU@mes0pr2!*^k", "*vW&aBYwy52wIR6NdrObYG@YwAe4y$%8");
		keyData.put("@JZcc14L^PN6NjZ%PlSZsitH3xkDBoh8", "7PBE*O5M@tc3xy4PPfgoU@r04L4Gx7uR");
		keyData.put("LwviWnToLXdzHk727fIbnD0V*mhCVhYx", "JmSC6E$LcC6ANYJLB1#CyKLc*Qd0*vrY");
		keyData.put("fHmkdLsTmYMTNHj$#pKT@PEb8IOCGLUI", "$YzSyCe%Bmas*4onYMScrdPYoMOw*Pk!");
		keyData.put("&uVLbmfd9XH79BjCstu2NvTCa7nOm@AD", "Ktq%qN6yaIsN%KEsEPlLp2ZL3wlRgg*o");
		keyData.put("R@tlTutGkJ4lIQvmNdmn@Atst6M9fkBJ", "kKGQcpx3UGjiGBS5PrFLRVRCjeEj!eqI");
		keyData.put("zdhULeUp31ezp49&YU9yva*I4ys*WQK@", "q*tAxre4o75btejBO!W*0tqD483WhCXK");
		keyData.put("B2LV9fsmcww5bxN^iSGrYQqT7i9uQrTv", "BB&koh0urmS8gs^ONzOXVLa9BELuTuE$");
		keyData.put("U3Pe2D28Qk40LWEFjXq!Dj2Y^5oaDVD@", "gcN2&RS@LQzVcTgtU7@L6PsIamqMa%18");
		keyData.put("!lsUo7Px8H7J7Mlml5uv5XKNdPAhTZeJ", "4SjCku6nhXE44*cn6smXImfYSqWl19ya");
	}

	/**
	 * 获取secretKey
	 * @param accessKey 
	 * @return secretKey
	 */
	public static String getSk(String accessKey) {
		return keyData.get(accessKey);
	}
}
