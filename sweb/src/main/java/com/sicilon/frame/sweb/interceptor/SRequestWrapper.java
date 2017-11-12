package com.sicilon.frame.sweb.interceptor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import jodd.io.StreamUtil;

/**
 * 
 * description：自定义request
 * ClassName: SRequestWrapper <br/> 
 * date: 2017年4月18日 下午3:10:14 <br/> 
 * @author chen
 */
public class SRequestWrapper extends HttpServletRequestWrapper {

	private final byte[] body; // 用于保存读取body中数据

	public SRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		body = StreamUtil.readBytes(request.getReader(), "UTF-8");
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(body);
		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return bais.read();
			}

			// 暂未实现
			@Override
			public boolean isFinished() {
				return false;
			}

			// 暂未实现
			@Override
			public boolean isReady() {
				return false;
			}

			// 暂未实现
			@Override
			public void setReadListener(ReadListener arg0) {
			}
		};
	}

}
