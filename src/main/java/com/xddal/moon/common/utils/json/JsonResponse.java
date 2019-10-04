package com.xddal.moon.common.utils.json;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON响应数据
 * 
 * @author 江成
 * 
 */
public class JsonResponse extends JsonMessage {

	/**
	 * 默认构造函数
	 */
	private JsonResponse() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param header
	 * @param data
	 */
	private JsonResponse(JSONObject header, JSONObject data) {
		super(header, data);
	}
	
	public static JsonResponse getResponse(JsonRequest jsonRequest){
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setHeader("requestHead", jsonRequest.getHeaders());
		return jsonResponse;
	}
	
	
	@Override
	public void setHeaders(JSONObject header) {
		header.put("requestHead",getHeader("requestHead"));
		super.setHeaders(header);
	}
	

	/**
	 * 设置响应码
	 * 
	 * @return
	 */
	public String getRetCode() {
		return (String) super.getHeader("retCode");
	}

	public void setRetCode(Object object) {
		super.setHeader("retCode", object);
	}

	public String getRetMsg() {
		return (String) super.getHeader("retMsg");
	}

	public void setRetMsg(String retMsg) {
		super.setHeader("retMsg", retMsg);
	}
}