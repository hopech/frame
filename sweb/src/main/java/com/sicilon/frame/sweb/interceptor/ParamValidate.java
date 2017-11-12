package com.sicilon.frame.sweb.interceptor;
/**
 * Description: 输入参数验证类
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月16日 上午9:45:57.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class ParamValidate {

	/**
	 * id合法性验证
	 * @param ids id可以为多个
	 * @return 真假值
	 */
	public static boolean idValidate(Long ...ids){
		
		//循环验证
		for (int i = 0; i < ids.length; i++) {
			if(ids[i] == null || ids[i] == 0){
				return false;
			}
		}
		return true;
	}
	
	
	
}
