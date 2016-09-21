package com.migu.resume.Exception;
/**
 * 自定义异常
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月14日 下午2:59:37
 * @version: V1.0
 */
public class CustomException extends Exception{
	private static final long serialVersionUID = 1L;

	public CustomException(String message){
		super(message);
	}
	
}
