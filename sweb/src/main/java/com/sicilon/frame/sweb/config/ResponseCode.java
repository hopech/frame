package com.sicilon.frame.sweb.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 响应编码
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年6月27日 上午9:19:55.<br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class ResponseCode {
	
	/**
	 *  1~400 系统级错误 
	 *  400以上业务级错误 
	 */

	public static Map<Integer, String> codes = new HashMap<Integer, String>();
	
	
	/** 请求成功 **/
	public static final Integer SUCCESS = 200;
	
	/** 未知异常 **/
	public static final Integer UNKNOWN_ERROR = 1;
	
	/** 服务暂时不可用 **/
	public static final Integer SERVICE_PAUSE_USE = 2;
	
	
	/** 缺失appKey参数 **/
	public static final Integer APPKEY_MISS = 11;
	
	/** 缺失accessKey参数 **/
	public static final Integer ACCESSKEY_MISS = 12;
	
	/** 缺失method参数 **/
	public static final Integer METHOD_MISS = 13;
	
	/** 缺失version参数 **/
	public static final Integer VERSION_MISS = 14;
	
	/** 缺失nonce参数 **/
	public static final Integer NONCE_MISS = 15;
	
	/** 缺失timestamp参数 **/
	public static final Integer TIMESTAMP_MISS = 16;
	
	/** 缺失sign参数 **/
	public static final Integer SIGN_MISS = 17;
	
	/** 缺失tokenId参数 **/
	public static final Integer TOKENID_MISS = 18;
	
	/** 无效的appKey参数 **/
	public static final Integer APPKEY_ERROR= 19;
	
	/** 无效的accessKey参数 **/
	public static final Integer ACCESSKEY_ERROR = 20;
	
	/** 不存在的方法名 **/
	public static final Integer METHOD_NO_EXIST = 21;
	
	/** 不支持的版本号 **/
	public static final Integer VERSION_ERROR = 22;
	
	/** 非法的时间戳 **/
	public static final Integer TIMESTAMP_ERROR = 23;
	
	/** 不正确的签名 **/
	public static final Integer SIGN_ERROR = 24;
	
	/** tokenId无效或过期 **/
	public static final Integer TOKEN_ERROR = 25;
	
	/** 请求被禁止 **/
	public static final Integer REQUSET_FORBID = 30;
	
	/** 用户调用次数超限 **/
	public static final Integer USER_REQUEST_COUNT_OVER = 31;
	
	/** 会话调用次数超限 **/
	public static final Integer SESSION_REQUEST_COUNT_OVER = 32;
	
	/** 应用调用次数超限 **/
	public static final Integer APP_REQUEST_COUNT_OVER = 33;
	
	/** 应用调用频率超限 **/
	public static final Integer APP_REQUEST_FREQUENCY_OVER = 34;
	
	/** HTTP方法被禁止 **/
	public static final Integer HTTP_METHOD_FORBID = 35;
	
	
	/** 缺少必填参数 **/
	public static final Integer MUST_PARARM_MISS = 100; 
	
	/** 字段长度超出限制 **/
	public static final Integer DATA_LENGTH_TOOLONG = 101;
	
	/** 日期格式不正确 **/
	public static final Integer DATE_FORMAT_ERROR = 102; 
	
	
	/** 远程连接错误 **/
	public static final Integer REMOTE_LINK_ERROR = 300; 
	
	/** 远程服务连接超时 **/
	public static final Integer REMOTE_LINK_TIMEOUT = 301; 
	
	/** 远程服务错误 **/
	public static final Integer REMOTE_SERVICE_ERROR = 302; 
	
	
	/**
	 * 上期版本遗留
	 */
	public static Integer _200 = 200;
	
	//300系统捕获异常错误
	public static Integer _300 = 300;
	
	//400由项目自定义
	public static Integer _401 = 401;
	public static Integer _402 = 402;
	public static Integer _403 = 403;
	public static Integer _405 = 405;
	public static Integer _406 = 406;
	public static Integer _407 = 407;
	public static Integer _408 = 408;
	public static Integer _409 = 409;
	public static Integer _410 = 410;
	//500为架构定义错误信息
	public static Integer _501 = 501;
	public static Integer _502 = 502;
	
	
	
	
	
	//加入内存map中
	static{
		codes.put(ResponseCode.SUCCESS,"请求成功");
		codes.put(ResponseCode.UNKNOWN_ERROR,"服务器内部异常，请稍后重试");
		
		codes.put(ResponseCode.SERVICE_PAUSE_USE,"服务暂时不可用");
		codes.put(ResponseCode.APPKEY_MISS,"缺失appKey参数");
		codes.put(ResponseCode.ACCESSKEY_MISS,"缺失accessKey参数");
		codes.put(ResponseCode.METHOD_MISS,"缺失method参数");
		codes.put(ResponseCode.VERSION_MISS,"缺失version参数");
		codes.put(ResponseCode.NONCE_MISS,"缺失nonce参数");
		codes.put(ResponseCode.TIMESTAMP_MISS,"缺失timestamp参数");
		codes.put(ResponseCode.SIGN_MISS,"缺失sign参数");
		codes.put(ResponseCode.TOKENID_MISS,"缺失tokenId参数");
		codes.put(ResponseCode.APPKEY_ERROR,"无效的appKey参数");
		codes.put(ResponseCode.ACCESSKEY_ERROR,"无效的accessKey参数");
		codes.put(ResponseCode.METHOD_NO_EXIST,"不存在的方法名");
		codes.put(ResponseCode.VERSION_ERROR,"不支持的版本号");
		codes.put(ResponseCode.TIMESTAMP_ERROR,"非法的时间戳");		
		codes.put(ResponseCode.SIGN_ERROR,"不正确的签名");
		codes.put(ResponseCode.TOKEN_ERROR,"tokenId无效或过期");
		
		codes.put(ResponseCode.REQUSET_FORBID,"请求被禁止");
		codes.put(ResponseCode.USER_REQUEST_COUNT_OVER,"用户调用次数超限");
		codes.put(ResponseCode.SESSION_REQUEST_COUNT_OVER,"会话调用次数超限");
		codes.put(ResponseCode.APP_REQUEST_COUNT_OVER,"应用调用次数超限");
		codes.put(ResponseCode.APP_REQUEST_FREQUENCY_OVER,"应用调用频率超限");
		codes.put(ResponseCode.HTTP_METHOD_FORBID,"HTTP方法被禁止");
		
		codes.put(ResponseCode.MUST_PARARM_MISS,"缺少必填参数");
		codes.put(ResponseCode.DATA_LENGTH_TOOLONG,"字段长度超出限制");
		
		codes.put(ResponseCode.DATE_FORMAT_ERROR,"日期格式不正确");
		
		codes.put(ResponseCode.REMOTE_LINK_ERROR,"远程连接错误");
		codes.put(ResponseCode.REMOTE_LINK_TIMEOUT,"远程服务连接超时");
		codes.put(ResponseCode.REMOTE_SERVICE_ERROR,"远程服务错误");
		
		
		/**
		 * 上期版本遗留
		 */
		
		codes.put(200, "请求成功");
		
		codes.put(300, "服务器内部错误");
		
		codes.put(401, "参数输入不合法");
		codes.put(402, "业务异常");
		codes.put(403, "输入信息不完整");
		codes.put(405, "查询失败");
		codes.put(406, "服务间通信异常");
		
		codes.put(407, "添加失败");
		codes.put(408, "修改失败");
		
		//500为架构定义错误信息
		codes.put(501, "信息验证无效");
		codes.put(502, "签名不合法");
		codes.put(503, "必须为POST方式提交");
		codes.put(504, "资源无效");
	}
}
