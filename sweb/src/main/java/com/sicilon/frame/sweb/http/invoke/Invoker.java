package com.sicilon.frame.sweb.http.invoke;

import com.sicilon.frame.sweb.bean.BaseInput;
import com.sicilon.frame.sweb.bean.BaseOutput;
import com.sicilon.frame.sweb.http.excetpion.HttpProcessException;

/**
 * Description: 调用接口<br/>
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年7月5日 上午9:31:22 <br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public interface Invoker {

	/**
	 * 远程请求方法封装
	 * @param url 请求地址
	 * @param input 输入参数
	 * @return 响应信息
	 * @throws HttpProcessException 
	 */
	BaseOutput remoteRequest(String url,BaseInput input) throws HttpProcessException;
}
