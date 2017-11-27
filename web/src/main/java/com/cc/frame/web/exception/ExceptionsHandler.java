package com.cc.frame.web.exception;

import com.cc.frame.web.response.ErrorOutput;
import com.cc.frame.web.response.ResponseCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: ExceptionHandler
 * Description: 异常处理器
 * author: CHENWEIJIA
 * date: 2017年11月16日
 */
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = ExplainException.class)
    @ResponseBody
    public ErrorOutput messageHandler(HttpServletRequest req, ExplainException e) {
        return new ErrorOutput(e.getCode(),e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorOutput defaultErrorHandler(HttpServletRequest req, Exception e) {
    	
        return new ErrorOutput(ResponseCode.UNKNOWN_ERROR,"");
    }
    
}
