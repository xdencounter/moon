package com.xddal.moon.common.utils.json;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON请求数据
 * 
 * @author xuedong
 * 
 */
public class JsonRequest extends JsonMessage {

	/**
	 * 默认构造函数
	 */
	public JsonRequest() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param header
	 * @param data
	 */
	public JsonRequest(JSONObject header, JSONObject data) {
		super(header, data);
	}
}