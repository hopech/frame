package com.sicilon.frame.sweb.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.sicilon.frame.sweb.log.BaseLog;
import com.sicilon.frame.sweb.util.ConvertUtil;
import com.sicilon.frame.sweb.util.PropertiesUtil;
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	/**
	 * 是否开启ip控制访问策略
	 */
	public static final boolean IP_CONTROLL;
	
	/**
	 * 是否开启 拦截非法渗透攻击
	 */
	public static final boolean ILLEGAL_ATTACK;
	
	/**
	 * 是否 开启输出debug信息
	 */
	public static final boolean DEBUG_LOG;
	
	/**
	 * 是否 开启签名验证 线上版本使用true
	 */
	public static final boolean SIGN_VALI;

	/**
	 * 是否开启service aop
	 */
	public static final boolean SERVICE_INTERCEP;
	
	/**签名时间戳过期时间 **/
	public static final Integer TIME_EXPIRES;
	
	/** 当前环境(开发环境OR真实环境) **/
	public static final String CURRENT_ENVIRONMENT = PropertiesUtil.getClasspathPropValue("web.properties", "current_environment"); 
	 
	/** 验证token的地址 **/
	public static final String TOKEN_VALIDATE_URL = PropertiesUtil.getClasspathPropValue("web.properties", "token_validate_url");
	
	/** 接口访问日志记录url **/
	public static final String API_ACCESS_LOG_RECORD_URL = PropertiesUtil.getClasspathPropValue("web.properties", "api_access_log_record_url");
	
	/** 接口异常日志记录url **/
	public static final String API_ACCESS_EXCEPTION_LOG_RECORD_URL = PropertiesUtil.getClasspathPropValue("web.properties", "api_access_exception_log_record_url");
	
	/** api接口访问日志开关 默认值false**/
	public static final boolean API_ACCESS_LOG_RECORD_OPEN;
	
	static{
		boolean ipControll = false;
		boolean ilegalAttack = false;
		boolean debugLog = false;
		boolean signVali = false;
		boolean serviceIntercep = false;
		Integer timeExpires = 1;
		
		boolean defaultRecordAccessLog = false;
		
		try {
			ipControll = ConvertUtil.stringToBoolean(PropertiesUtil.getClasspathPropValue("web.properties", "ip_controll"));
			ilegalAttack = ConvertUtil.stringToBoolean(PropertiesUtil.getClasspathPropValue("web.properties", "illegal_attack"));
			debugLog = ConvertUtil.stringToBoolean(PropertiesUtil.getClasspathPropValue("web.properties", "debug_log"));
			signVali = ConvertUtil.stringToBoolean(PropertiesUtil.getClasspathPropValue("web.properties", "sign_vali"));
			serviceIntercep = ConvertUtil.stringToBoolean(PropertiesUtil.getClasspathPropValue("web.properties", "service_intercep"));
			
			if(null != PropertiesUtil.getClasspathPropValue("web.properties", "time_expires")){
				timeExpires = Integer.valueOf(PropertiesUtil.getClasspathPropValue("web.properties", "time_expires"));
			}
			
			defaultRecordAccessLog = ConvertUtil.stringToBoolean(PropertiesUtil.getClasspathPropValue("web.properties", "api_access_log_record_open"));
			
		} catch (Exception e) {
			BaseLog.error(e);
		}
		
		IP_CONTROLL = ipControll;
		ILLEGAL_ATTACK = ilegalAttack;
		DEBUG_LOG = debugLog;
		SIGN_VALI = signVali;
		SERVICE_INTERCEP = serviceIntercep;
		TIME_EXPIRES = timeExpires;
		
		API_ACCESS_LOG_RECORD_OPEN = defaultRecordAccessLog;
	}
	
	
}
