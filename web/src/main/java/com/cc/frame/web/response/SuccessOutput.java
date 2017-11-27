package com.cc.frame.web.response;


/**
 * @ClassName: SuccessOutput
 * @Description: 请求成功响应信息
 * @author: CHENWEIJIA
 * @date: 2017年11月16日
 */
public class SuccessOutput<T> extends BaseOutput{

	
	private T resultData; // 数据
	

	/**
	 * 成功操作,正常响应数据
	 * @param resultData 响应数据
	 */
	public SuccessOutput(T resultData){
		super.setCode(ResponseCode.SUCCESS);
		this.resultData = resultData;
	}
	
	/**
	 * 自定义编码,及数据
	 * @param code 编码
	 * @param resultData 数据
	 */
	public SuccessOutput(Integer code,T resultData){
		super.setCode(code);
		this.resultData = resultData;
	}


	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}



}
