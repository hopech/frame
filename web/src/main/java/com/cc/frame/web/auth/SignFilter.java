package com.cc.frame.web.auth;

import com.cc.frame.web.config.WebConfig;
import com.cc.frame.web.response.ErrorOutput;
import com.cc.frame.web.response.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: SignFilter
 * @Description: 签名过滤器
 * @author: CHENWEIJIA
 * @date: 2017年11月16日
 */
public class SignFilter implements Filter{

    private String excludes; // 不需要拦截的URI
	
    @Override
	public void init(FilterConfig filterConfig) throws ServletException {
		 excludes = filterConfig.getInitParameter("excludes");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
		
        //不在拦截范围内url或者没有开启签名验证
		if(!WebConfig.openSignAuth) {
			chain.doFilter(request, response);
			return;
		}else if(excludes != null && request.getRequestURI().matches(excludes)) {
			chain.doFilter(request, response);
			return;
		}
		
		//获取公共参数
		String appKey = request.getHeader("appKey");
		String accesskey = request.getHeader("accesskey");
		String method = request.getHeader("method");
		String version = request.getHeader("version");
		String nonce = request.getHeader("nonce");
		String timestamp = request.getHeader("timestamp");
		String sign = request.getHeader("sign");
		
		
		String appendBaseParam = ACSigner.appendBasePararm(appKey, accesskey, method,version, nonce, timestamp);
		try {
			if(sign != null && sign.equals(ACSigner.getSignature(SignKey.getSk(accesskey), appendBaseParam))) {
				chain.doFilter(request, response);
				return;
			}else {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorOutput(ResponseCode.SIGN_ERROR,"")));
				return;
			}
		} catch (Exception e) {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorOutput(ResponseCode.SIGN_ERROR,"")));
			return;
		}
		
	}


	
	@Override
	public void destroy() {
	}

}
