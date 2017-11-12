package com.sicilon.frame.sweb.interceptor;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sicilon.frame.sweb.annotation.DataLength;
import com.sicilon.frame.sweb.annotation.DateFormat;
import com.sicilon.frame.sweb.bean.BaseInput;
import com.sicilon.frame.sweb.bean.BaseOutput;
import com.sicilon.frame.sweb.bean.NAccessApiExceptionLog;
import com.sicilon.frame.sweb.bean.NAccessApiLogInfo;
import com.sicilon.frame.sweb.config.ResponseCode;
import com.sicilon.frame.sweb.config.WebConfig;
import com.sicilon.frame.sweb.exception.ArgsGetTokenException;
import com.sicilon.frame.sweb.exception.MessageException;
import com.sicilon.frame.sweb.http.excetpion.HttpProcessException;
import com.sicilon.frame.sweb.http.invoke.HttpInvoker;
import com.sicilon.frame.sweb.log.BaseLog;
import com.sicilon.frame.sweb.util.DateTimeUtil;
import com.sicilon.frame.sweb.util.DateTypeEnumUtil;
import com.sicilon.frame.sweb.util.JsonUtil;
import com.sicilon.frame.sweb.util.StringUtil;

import io.swagger.annotations.ApiModelProperty;

@Aspect
@Component
public class ControllerAop {
	@Pointcut("execution(public * com.sicilon.ycsys.sales.controller.*.*.*.*(..))")
	public void interceptor() {
	}

	@Before("interceptor()")
	public void doBefore(JoinPoint joinPoint) throws Exception {
//
//		// 获取方法参数
//		Object[] args = joinPoint.getArgs();
//
//		// 对方法每一个参数做处理
//		for (int i = 0; i < args.length; i++) {
//			Class<?> clazz = args[0].getClass();
//
//			// 只处理被swagger标注的注解类
//			if (!clazz.isAnnotationPresent(ApiModel.class)) {
//				continue;
//			}
//
//			// swagger声明的属性
//			Field[] declaredFields = args[0].getClass().getDeclaredFields();
//			this.blankValidate(declaredFields, clazz, args[i]);
//
//		}
	}

	@AfterReturning(pointcut = "interceptor()", returning = "returnObj")
	public void doAfter(JoinPoint joinPoint, Object returnObj) {
	}

	/**
	 * 异常通知
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(pointcut = "interceptor()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Throwable ex) {
		//记录接口访问日志开关
		if(!WebConfig.API_ACCESS_LOG_RECORD_OPEN ) {
			return;
		}
		
		NAccessApiExceptionLog exLog = new NAccessApiExceptionLog(); //异常信息日志实体
		String originIp;//源ip
		String clientIp;//客户端ip
		String accessApiUrl;//接口访问url
		StringBuffer inputJsonStr;//输入参数json
		Date accessTime; //访问时间
		String exceptionType; //异常类型
		String exceptionDetail; //异常详情
		Long accessUserId; //访问用户id
		
		//接口访问url
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		accessApiUrl = sra.getRequest().getRequestURL().toString();
		
		//ip
		clientIp = getClientIp(sra.getRequest());
		originIp = getOriginIp(sra.getRequest());
		
		//获取输入参数json
		inputJsonStr = new StringBuffer();
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			inputJsonStr.append(JsonUtil.toJson(arg)).append(",");
		}
		inputJsonStr.deleteCharAt(inputJsonStr.length()-1);
		
		//获取访问用户id
		try {
			accessUserId = getArgsLoginUserId(args);
		} catch (ArgsGetTokenException e) {
			BaseLog.error("从参数中获取token失败");
			accessUserId = 0L;
		}
		
		//访问时间
		accessTime = DateTimeUtil.getNowTime(DateTypeEnumUtil.LOCAL.getValue());
		
		//异常类型
		exceptionType = ex.getClass().getSimpleName();
		
		//异常详情
		exceptionDetail = getExceptionStackTrace(ex);
		
		//实体赋值
		exLog.setAccessApiUrl(accessApiUrl);
		exLog.setAccessTime(accessTime);
		exLog.setAccessUserId(accessUserId);
		exLog.setOriginIp(originIp);
		exLog.setClientIp(clientIp);
		exLog.setExceptionDetail(exceptionDetail);
		exLog.setExceptionType(exceptionType);
		exLog.setInputJsonStr(inputJsonStr.toString());
	
		//远程调用记录
		recordAccessApiExceptionLog(exLog);
	}
	
	
	/**
	 * 获取异常信息内容
	 * @param e
	 * @return
	 */
	private String getExceptionStackTrace(Throwable e) {
		StringBuffer exceptionStackTraceStr = new StringBuffer();
		exceptionStackTraceStr.append(e.getMessage()).append("\r");
		
		for (StackTraceElement stackTrace: e.getStackTrace()) {
			exceptionStackTraceStr.append(stackTrace.toString()).append("\r");
		}
		return exceptionStackTraceStr.toString();
		
	}
	
	
	/**
	 * 环绕通知
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around(value="interceptor()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		
		//记录接口访问日志开关
		if(!WebConfig.API_ACCESS_LOG_RECORD_OPEN ) {
			return point.proceed();
		}
		
		NAccessApiLogInfo logInfo; //日志记录实体
		String originIp; //源ip
		String clientIp;//客户端ip
		String accessUrl;//接口访问url
		Date accessTime;//访问时间
		Long accessUserId;//访问用户id
		StringBuffer inputJsonStr;//输入参数json
		String responseJson;//响应结果json
		Long spendTime;//花费时间
		
		//接口访问url
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        accessUrl = sra.getRequest().getRequestURL().toString();
        
		//记录接口访问日志开关
		if(accessUrl.contains(WebConfig.API_ACCESS_LOG_RECORD_URL)) {
			return point.proceed();
		}
        
        //ip
		clientIp = getClientIp(sra.getRequest());
		originIp = getOriginIp(sra.getRequest());
		
		//获取输入参数json
		inputJsonStr = new StringBuffer();
		Object[] args = point.getArgs();
		for (Object arg : args) {
			inputJsonStr.append(JsonUtil.toJson(arg)).append(",");
		}
		inputJsonStr.deleteCharAt(inputJsonStr.length()-1);
		
		//获取访问用户id
		accessUserId = getArgsLoginUserId(args);
		
		//访问时间
		accessTime = DateTimeUtil.getNowTime(DateTypeEnumUtil.LOCAL.getValue());
		
		//执行处理过程
		Object proceedResult = point.proceed();
		
		//花费时间
		spendTime = System.currentTimeMillis() - accessTime.getTime();
		
		//获取响应结果json
		responseJson = JsonUtil.toJson(proceedResult);
		
		//赋值
		logInfo = new NAccessApiLogInfo();
		logInfo.setClientIp(clientIp)
		             .setOriginIp(originIp)
					 .setAccessTime(new DateTimeUtil(accessTime).toDateTimeString())
					 .setAccessUserId(accessUserId)
					 .setApiUrl(accessUrl)
					 .setInputJsonStr(inputJsonStr.toString())
					 .setResponseJsonStr(responseJson)
					 .setSpendTime(spendTime);
		
		recordAccessLog(logInfo);
		
		return proceedResult;
	}
	
	
	
	/**
	 * 远程调用记录日志接口
	 * @param logInfo 日志信息
	 * @throws HttpProcessException 
	 */
	private void recordAccessLog(NAccessApiLogInfo logInfo) {
		HttpInvoker invoker = new HttpInvoker();
		BaseOutput baseOutput = new BaseOutput();
		try {
			baseOutput = invoker.remoteRequest(WebConfig.API_ACCESS_LOG_RECORD_URL, logInfo);
		} catch (HttpProcessException e) {
			BaseLog.error(e);
		}
		if(!ResponseCode.SUCCESS .equals(baseOutput.getCode())) {
			BaseLog.error("接口访问日志记录失败");
		}
	}
	
	
	private void recordAccessApiExceptionLog(NAccessApiExceptionLog logInfo) {
		HttpInvoker invoker = new HttpInvoker();
		BaseOutput baseOutput = new BaseOutput();
		try {
			baseOutput = invoker.remoteRequest(WebConfig.API_ACCESS_EXCEPTION_LOG_RECORD_URL, logInfo);
		} catch (HttpProcessException e) {
			BaseLog.error(e);
		}
		if(!ResponseCode.SUCCESS .equals(baseOutput.getCode())) {
			BaseLog.error("接口访问日志记录失败");
		}
	}
	
	
	/**
	 * 获取访问用户id，暂不做实现
	 * @param args
	 * @return
	 * @throws ArgsGetTokenException
	 */
	private Long getArgsLoginUserId(Object[] args) throws ArgsGetTokenException {
		return 1L;
	}

	
	/**
	 * 字段验证
	 * @param declaredFields
	 * @param clazz
	 * @param obj
	 * @throws Exception
	 */
	private void blankValidate(Field[] declaredFields,Class<?> clazz, Object obj) throws Exception {
		
		for (Field field : declaredFields) {
			
			Object result = clazz.getMethod(getFieldMethodName(field.getName())).invoke(obj);
			//如果字段类型为实体类继承自BaseInput
			if(BaseInput.class.isAssignableFrom(field.getType())){
				blankValidate(field.getType().getDeclaredFields(),field.getType(),result);
			}

			// 获取字段注解
			ApiModelProperty apiModelPro = field.getAnnotation(ApiModelProperty.class);
			
			//非空验证
			if (apiModelPro.required() == true) {
				//String类型进行空串校验
				if (String.class == field.getType()) {
					result = (String) clazz.getMethod(getFieldMethodName(field.getName())).invoke(obj);
					if (StringUtil.isBlank(result)) {
						throw new MessageException(ResponseCode.MUST_PARARM_MISS,
								"This filed:\"" + field.getName() + "\" is can't blank");
					}
				}else{
					//普通类型字段 直接做null的校验
					if (null == result) {
						throw new MessageException(ResponseCode.MUST_PARARM_MISS, ("This filed:\"" + field.getName() + "\" is can't blank").toString());
					}
				}
			}
			
			//日期格式验证
			if(field.isAnnotationPresent(DateFormat.class)){
				if(!this.isDate(String.valueOf(result))){
					throw new MessageException(ResponseCode.DATE_FORMAT_ERROR, ("This filed:\"" + field.getName() + "\" Incorrect format").toString());
				}
			}
			
			//长度验证
			if(field.isAnnotationPresent(DataLength.class)){
				if (String.valueOf(result).length() > field.getAnnotation(DataLength.class).value()) {
					throw new MessageException(ResponseCode.DATA_LENGTH_TOOLONG,("This filed:\"" + field.getName() + "\" length too long").toString());
				}
			}
			
		}
		

	}
	
	/**
	 * 根据字段名获取get方法名称
	 * @param fieldName
	 * @return
	 */
	public String getFieldMethodName(String fieldName) {
		StringBuffer result = new StringBuffer();
		result.append("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
		return result.toString();
	}
	
	

	/**
	 * 获取源ip
	 * @param request
	 * @return
	 */
	public String getOriginIp(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	

	/**
	 * 获取客户端ip地址
	 * @param request
	 * @return
	 */
	public String getClientIp(HttpServletRequest request) {
		String ip =  request.getRemoteAddr();
		return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	
	
	/**
	 * 判断是否是日期格式
	 * @param str 要检查的字符串
	 * @return 真假值
	 */
	public boolean isDate(String str) {
		String reg = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		return Pattern.compile(reg).matcher(str).find();
	}
}
