package com.migu.resume.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 封装fastjson的常用方法
 * 
 * @author zhangpanfu
 */
public class JSONUtils {

	/**
	 * 将Java对象转成JSON字符串
	 * @param object
	 * @return
	 */
	public static String toJSONString(Object object) {
		SerializeWriter writer = new SerializeWriter(SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteDateUseDateFormat);
		writer.config(SerializerFeature.QuoteFieldNames, true);
		JSONSerializer json = new JSONSerializer(writer);
		json.write(object);
		return json.toString();
	}
	

	
	/**
	 * 将Map<String, Object>转换成Map<String, String>即将Map的value序列化成JSON字符串
	 * @param dataMap
	 * @return
	 */
	public static Map<String, String> toJSON(Map<String, Object> dataMap) {
		
		if (null==dataMap || dataMap.isEmpty()){
			return null;
		}
		Map<String, String> result = new HashMap<String, String>();
		for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
			result.put(entry.getKey(), toJSONString(entry.getValue()));
		}
		return result;
	}
	
	/**
	 * 将JSON字符串解析成JSONObject对象
	 * @param text
	 * @return JSONObject
	 */
	public static JSONObject parseObject(String text) {
		return JSON.parseObject(text);
	}
	
	/**
	 * 将JSON字符串解析成Java对象
	 * @param text
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String text, Class<T> clazz) {
		return (T) JSON.parseObject(text, clazz);
	}
	
	/**
	 * 解析成List对象
	 * @param text
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseList(String text, Class<T> clazz) {
		return (List<T>) JSONObject.parseArray(text, clazz);
	}
	
	/**
	 * 解析成List对象
	 * @param texts
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseList(List<String> texts, Class<T> clazz) {
		if (null==texts || texts.isEmpty()) {
			return null;
		}
		List<T> result = new ArrayList<T>();
		for (String text : texts) {
			if (null==text || "".equals(text)) {
				continue;
			}
			T obj = parseObject(text, clazz);
			if (obj != null) {
				result.add(obj);
			}
		}
		return result;
	}
	
	/**
	 * 解析成List集合对象,解析对象为Map<String, String>的value
	 * @param textMap
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseList(Map<String, String> textMap, Class<T> clazz) {
		if (null==textMap || textMap.isEmpty()) {
			return null;
		}
		List<T> result = new ArrayList<T>();
		for (Map.Entry<String, String> entry : textMap.entrySet()) {
			T object = parseObject(entry.getValue(), clazz);
			if(object != null){
				result.add(object);
			}
		}
		return result;
	}
}
