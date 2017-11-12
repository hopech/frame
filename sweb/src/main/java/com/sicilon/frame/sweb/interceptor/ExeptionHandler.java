package com.sicilon.frame.sweb.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sicilon.frame.sweb.bean.ErrorOutput;
import com.sicilon.frame.sweb.config.ResponseCode;
import com.sicilon.frame.sweb.exception.MessageException;
import com.sicilon.frame.sweb.log.BaseLog;
/**
 * 
 * Description: 异常处理拦截
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月7日 上午11:05:37.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
@ControllerAdvice  
public class ExeptionHandler {
	
	@ExceptionHandler(value = MessageException.class)
	@ResponseBody
    public ErrorOutput messageHandler(HttpServletRequest req, MessageException e) {
		//记录日志
		return new ErrorOutput(e.getCode(),e.getMessage());
    }

	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
    public ErrorOutput defaultErrorHandler(HttpServletRequest req, Exception e) {
		//记录日志
		BaseLog.error(e);
		return new ErrorOutput(ResponseCode._300,"");
    }
	


	
	
}
