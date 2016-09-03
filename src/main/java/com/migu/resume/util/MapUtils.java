package com.migu.resume.util;

import java.util.Collections;
import java.util.Map;

public abstract class MapUtils {
	public static int getInt(Object key, Map<?, ?> map) {
		Object value = map.get(key);
		return StringUtils.toInt(value == null ? null : value.toString());
	}

	public static String getString(Object key, Map<?, ?> map) {
		Object value = map.get(key);
		return value.toString();
	}

	public static int getInt(Object key, int defaultValue, Map<?, ?> map) {
		Object value = map.get(key);
		return StringUtils.toInt(value == null ? null : value.toString(), defaultValue);
	}

	public static long getLong(Object key, Map<?, ?> map) {
		Object value = map.get(key);
		return StringUtils.toLong(value == null ? null : value.toString());
	}

	public static long getLong(Object key, long defaultValue, Map<?, ?> map) {
		Object value = map.get(key);
		return StringUtils.toLong(value == null ? null : value.toString(), defaultValue);
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(Object key, Map<?, ?> map, Class<T> clazz) {
		return (T) map.get(key);
	}

	@SuppressWarnings("rawtypes")
	public static Map nullToEmpty(Map map) {
		return map == null ? Collections.EMPTY_MAP : map;
	}

	public static void removeKeys(Map<?, ?> map, Object[] keys) {
		if (keys == null) {
			return;
		}
		for (Object key : keys) {
			map.remove(key);
		}
	}
}
