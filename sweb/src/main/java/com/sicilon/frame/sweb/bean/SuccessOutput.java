package com.sicilon.frame.sweb.bean;

import com.sicilon.frame.sweb.config.ResponseCode;
import com.sicilon.frame.sweb.util.JsonUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: BaseOutput
 * @Description: 公共输出定义
 * @author chen
 * @date 2017年3月31日 下午11:27:48
 */
@ApiModel(value="统一输出格式")
public class SuccessOutput<T> extends BaseOutput{

	
	@ApiModelProperty(value="实体数据")
	private T resultData; // 数据
	

	/**
	 * 成功操作,正常响应数据
	 * @param data 响应数据
	 */
	public SuccessOutput(T resultData){
		super.setCode(ResponseCode.SUCCESS);
		this.resultData = resultData;
	}
	
	/**
	 * 自定义编码,及数据
	 * @param code 编码
	 * @param data 数据
	 */
	public SuccessOutput(Integer code,T resultData){
		super.setCode(code);
		this.resultData = resultData;
	}


	@Override
	public String toString() {
		return JsonUtil.toJson(this);
	}

	public T getResultData() {
		return resultData;
	}

	public void setResultData(T resultData) {
		this.resultData = resultData;
	}



}
