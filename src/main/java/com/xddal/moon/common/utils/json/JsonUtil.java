package com.xddal.moon.common.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuedong
 *
 */
public class JsonUtil {
	/**
	 * JSONArray型key值转驼峰
	 * @param arry
	 * @return
	 */
	public static JSONArray changeKey (JSONArray arry){
		return changeKey(arry, false);
	}
	
	/**
	 * JSONArray型key值转下划线
	 * @param arry
	 * @return
	 */
	public static JSONArray changeKey (JSONArray arry, Boolean flag){
		JSONArray newArry = new JSONArray();
		for(int i=0;i<arry.size();i++){
			if(flag){
				newArry.add(changeKey(arry.getJSONObject(i),true));
			}else{
				newArry.add(changeKey(arry.getJSONObject(i)));
			}
		}
		return newArry;
	}
	
	
	/**
	 * JSONObject型key值转驼峰
	 * @param map
	 * @return
	 */
	public static JSONObject changeKey (JSONObject map){
		return changeKey(map,false);
	}
	
	/**
	 * JSONObject型key值转大写下划线
	 * @param map
	 * @return
	 */
	public static JSONObject changeKey (JSONObject map , Boolean flag){
		JSONObject newMap = new JSONObject();
		Set<String> keySet = map.keySet();
		for(String key : keySet){
			if(flag){
				newMap.put(humpToLine(key), map.get(key));
			}else{
				newMap.put(lineToHump(key), map.get(key));
			}
			
		}
		return newMap;
	}

	//下划线转驼峰
	private static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = Pattern.compile("_(\\w)").matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	} 
	
	//驼峰转大写字母和下划线
	private static String humpToLine(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
            } 
            sb.append(Character.toUpperCase(c));
        }
        return sb.toString();
	}
	/**
	 * 列表转JSON数组
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static <T> JSONArray toJSONArray(List<T> data){
		return JSON.parseObject(JSON.toJSON(data).toString(), JSONArray.class);
	}
	
	/**
	 * 转JSONArray对象
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static JSONArray toJSONArray(Object data){
		return JSON.parseObject(JSON.toJSON(data).toString(), JSONArray.class);
	}
	
	/**
	 * 转JSON对象
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static JSONObject toJSONObject(Object data){
		return JSON.parseObject(JSON.toJSON(data).toString(), JSONObject.class);
	}
	
	/**
	 * JSON数组元素添加属性
	 * @param newJSONArray
	 * @param name
	 * @param objectData
	 * @return
	 */
	public static JSONArray addData(JSONArray newJSONArray,String name,Object objectData){
		for(int i=0;i<newJSONArray.size();i++){
			JSONObject jsonData = newJSONArray.getJSONObject(i);
			jsonData.put(name, objectData);
			newJSONArray.add(i, jsonData);
		}
		return newJSONArray;
	}
	
	/**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }
	
	
}
