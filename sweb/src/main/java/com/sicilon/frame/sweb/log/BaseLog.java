package com.sicilon.frame.sweb.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * description：全局log定义
 * ClassName: BaseLog <br/> 
 * date: 2017年4月6日 下午2:13:48 <br/> 
 * @author chen
 */
public class BaseLog {
	
	private final static Logger logger = LoggerFactory.getLogger(BaseLog.class);

	public static void debug(String message) {
		logger.debug(message);
	}

	public static void info(String message) {
		logger.info(message);
	}

	public static void error(String message) {
		logger.error(message);
	}
	public static void error(Exception e){
		logger.error("",e);
	}
	public static void error(String msg,Exception e){
		logger.error(msg, e);
	}
	

	public static void trace(String message) {
		logger.trace(message);
	}

	public static void warn(String message) {
		logger.warn(message);
	}
}
