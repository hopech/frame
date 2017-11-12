package com.sicilon.frame.sweb.http.invoke;

import com.sicilon.frame.sweb.bean.BaseInput;
import com.sicilon.frame.sweb.bean.BaseOutput;
import com.sicilon.frame.sweb.http.HttpClientUtil;
import com.sicilon.frame.sweb.http.builder.common.HttpConfig;
import com.sicilon.frame.sweb.http.excetpion.HttpProcessException;
import com.sicilon.frame.sweb.util.JsonUtil;

/**
 * Description: <br/>
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年7月5日 上午9:39:48 <br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class HttpInvoker implements Invoker{

	@Override
	public BaseOutput remoteRequest(String url, BaseInput input) throws HttpProcessException {
		
		//http请求
		HttpConfig config = HttpConfig.custom()
				.headers(HttpDefaultConfig.headers)
				.url(url)					
				.json(JsonUtil.toJson(input))
				.encoding(HttpDefaultConfig.ENCODING); 
		String result = HttpClientUtil.post(config);
		
		//接收bean,并返回resultData
		return JsonUtil.jsonToObject(result, BaseOutput.class);
	}

	
	
}
