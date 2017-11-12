package com.sicilon.frame.sweb.http.excetpion;


/**
 * 
 * Description: http远程调用异常<br/>
 * Author: CHENWEIJIA <br/>
 * Version: 1.0 <br/>
 * CreateTime: 2017年7月7日 上午9:32:17 <br/>
	<br/>UpdateTime：
	<br/>UpdateUser：
	<br/>UpdateNote：
	<br/>------------------------------
 */
public class HttpProcessException  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2749168865492921426L;

	public HttpProcessException(Exception e){
		super(e);
	}

	/**
	 * @param string
	 */
	public HttpProcessException(String msg) {
		super(msg);
	}
	
	/**
	 * @param message
	 * @param e
	 */
	public HttpProcessException(String message, Exception e) {
		super(message, e);
	}
	
}
