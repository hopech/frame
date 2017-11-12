package com.sicilon.frame.sweb.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sicilon.frame.sweb.auth.ACSigner;
import com.sicilon.frame.sweb.auth.SignKey;
import com.sicilon.frame.sweb.bean.BaseInput;
import com.sicilon.frame.sweb.bean.BaseOutput;
import com.sicilon.frame.sweb.bean.ErrorOutput;
import com.sicilon.frame.sweb.bean.StringInput;
import com.sicilon.frame.sweb.bean.SuccessOutput;
import com.sicilon.frame.sweb.config.ResponseCode;
import com.sicilon.frame.sweb.config.WebConfig;
import com.sicilon.frame.sweb.enums.EnvironmentCode;
import com.sicilon.frame.sweb.http.excetpion.HttpProcessException;
import com.sicilon.frame.sweb.http.invoke.HttpInvoker;
import com.sicilon.frame.sweb.log.BaseLog;
import com.sicilon.frame.sweb.util.DateTimeUtil;
import com.sicilon.frame.sweb.util.JsonUtil;
import com.sicilon.frame.sweb.util.StringUtil;

/**
 * 
 * Description: 公共参数处理 过滤器 <br/>
 * Author: CHENWEIJIA<br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年8月30日 下午4:04:07 <br/>
  <br/>UpdateTime：
  <br/>UpdateUser：
  <br/>UpdateNote：
  <br/>------------------------------
 */
public class BaseParamFilter implements Filter {

	private String excludeUri; //排除的uri
	
	private static HttpInvoker httpInvoker = new HttpInvoker();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludeUri = filterConfig.getInitParameter("excludeUri");
		if(excludeUri==null){
			excludeUri = "";
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		//根据不同环境作不同拦截
		if(EnvironmentCode.REAL.getEnvirCode().equals(WebConfig.CURRENT_ENVIRONMENT)) {
			this.dealReal(httpRequest, httpResponse, chain);
		}else if(EnvironmentCode.DEV.getEnvirCode().equals(WebConfig.CURRENT_ENVIRONMENT)) {
			this.dealDev(httpRequest, httpResponse, chain);
		}else {
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		
	}
	
	
	/**
	 * 真实环境拦截处理
	 * @param request 
	 * @param response
	 * @param chain
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void dealReal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) throws IOException, ServletException {
		
		//只接收POST请求
//		if(!httpRequest.getMethod().equals("POST")){
//			return;
//		}
		
		String servletPath = httpRequest.getServletPath();
		
		/*
		 * 如果没有开启签名验证,或者在排除的uri之内,不进行过滤操作
		 */
		if(!WebConfig.SIGN_VALI || excludeUri.contains(servletPath)){
			chain.doFilter(httpRequest, httpResponse);
			return;
		}
		
		/*
		 * 替换原request
		 */
		ServletRequest requestWrapper =  new SRequestWrapper(httpRequest); 

		/*
		 * 获取出body中的请求参数
		 */
		String param = getBodyString(requestWrapper.getReader());
		
		/*
		 * 参数校验
		 */
		if(this.validateBaseParam(param)){
			chain.doFilter(requestWrapper, httpResponse);
		}else{
			//输出响应信息
			httpResponse.setContentType("application/json;charset=utf-8");
			SuccessOutput<String> baseOutput = new SuccessOutput<String>(ResponseCode.ACCESSKEY_ERROR,"");
			httpResponse.getWriter().write(JsonUtil.toJson(baseOutput));
		}
		return;
	}

	@Override
	public void destroy() {
	}

	/**
	 * 获取body中的请求参数
	 * @param br bufferReader 字符reader
	 * @return body中的请求参数
	 * @throws IOException io异常
	 */
	private String getBodyString(BufferedReader br) throws IOException {
		
		String inputLine;
		StringBuffer params = new StringBuffer(); // 参数
		
		/*
		 * 遍历流信息,为params赋值
		 */
		try {
			while ((inputLine = br.readLine()) != null) {
				params.append(inputLine);
			}
			br.close();
		} catch (IOException e) {
			throw new IOException();
		}
		return params.toString();
	}
	
	/**
	 * 验证公共参数正确性
	 * @param baseParam 公共参数拼接字符串
	 * @return 真假值
	 * @throws Exception 
	 */
	private boolean validateBaseParam(String baseParam){
		
		//参数为空，返回false
		if(StringUtil.isBlank(baseParam)){
			return false;
		}
		
		//将json转换为实体
		BaseInput baseInput = JsonUtil.jsonToObject(baseParam, BaseInput.class);
		
		//签名为空返回fasle
		if(StringUtil.isBlank(baseInput.getBaseParam().getSign())){
			return false;
		}
		
		//验证是否过期
		long timestamp = baseInput.getBaseParam().getTimestamp();
		if(new Date().getTime() > timestamp + WebConfig.TIME_EXPIRES*60*1000){
			return false;
		}
		
		//对公共参数值进行拼接
		String appendBaseParam = ACSigner.appendBasePararm(
				baseInput.getBaseParam().getAppKey(), baseInput.getBaseParam().getAccesskey(), baseInput.getBaseParam().getMethod(),
				baseInput.getBaseParam().getVersion(), baseInput.getBaseParam().getNonce(), baseInput.getBaseParam().getTimestamp());
		
		//签名比对
		try {
			
			
			
			return baseInput.getBaseParam().getSign().equals(ACSigner.getSignature(SignKey.getSk(baseInput.getBaseParam().getAccesskey()), appendBaseParam));
		} catch (Exception e) {
			BaseLog.error(e);
			return false;
		}
		
		
	}
	
	
	/**
	 * 开发环境拦截处理
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void dealDev(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

		//get请求放过
		if(!request.getMethod().equals("POST")){
			chain.doFilter(request, response);
			return;
		}
		
		
		/*
		 * 验证token有效性
		 */
		SRequestWrapper sRequestWrapper = new SRequestWrapper(request);//request掉包
		String bodyString = getBodyString(sRequestWrapper.getReader());
		//将json转换为实体
		BaseInput baseInput = JsonUtil.jsonToObject(bodyString, BaseInput.class);
		
		try {
			BaseOutput baseOutput = httpInvoker.remoteRequest(WebConfig.TOKEN_VALIDATE_URL, new StringInput(baseInput.getBaseParam().getTokenId()));
			
			//根据code判断是否验证成功
			if(null != baseOutput && ResponseCode.SUCCESS.equals(baseOutput.getCode())) {
				chain.doFilter(sRequestWrapper, response);
				return;
			}else {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(JsonUtil.toJson(new ErrorOutput(ResponseCode.TOKEN_ERROR,"")));
				return;
			}
			
		} catch (HttpProcessException e) {
			ErrorOutput errorOutput = new ErrorOutput(ResponseCode.REMOTE_LINK_ERROR,"");
			response.getWriter().write(JsonUtil.toJson(errorOutput));
			return;
		}
	}
	
	

	
}
