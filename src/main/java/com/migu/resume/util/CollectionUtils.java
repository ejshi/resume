package com.migu.resume.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 * CollectUtils
 *
 * @author zhangpanfu
 */
public class CollectionUtils {

	public static <T> boolean isEmpty(Collection<T> collection) {
		return collection == null ? true : collection.isEmpty();
	}
	
	public static <T> boolean isNotEmpty(Collection<T> collection) {
		return (collection != null && !collection.isEmpty());
	}
	
	public static <T> int size(Collection<T> collection) {
		return collection == null ? 0 : collection.size();
	}
	
	public static String wrap(Collection<?> collection, String sepeator) {
		return CollectionUtils.wrap(collection, "", "", sepeator);
	}
	
	public static String wrap(Collection<?> collection, String prefixWrapper, String suffixWrapper, String sepeator) {
		if (collection == null) {
			return "";
		}
		return CollectionUtils.wrap(collection.iterator(), prefixWrapper, suffixWrapper, sepeator);
	}
	
	private static String wrap(Iterator<?> objects, String prefixWrapper, String suffixWrapper, String sepeator) {
		if (objects == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		if (objects.hasNext()) {
			sb.append(prefixWrapper).append(objects.next().toString()).append(suffixWrapper);
		}
		while (objects.hasNext()) {
			sb.append(sepeator).append(prefixWrapper).append(objects.next().toString()).append(suffixWrapper);
		}
		return sb.toString();
	} 
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		String str = CollectionUtils.wrap(list, ",");
		System.out.println("####:::"+str);
	}
}
