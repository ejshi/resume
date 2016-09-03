package com.migu.resume.Exception;
/**
 * 数据校验错误处理类
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月1日 下午4:56:47
 * @version: V1.0
 */
public class FieldValidationError {
	private String field;
	private String msg;
	
	public FieldValidationError() {
		super();
	}
	
	public FieldValidationError(String field, String msg) {
		super();
		this.field = field;
		this.msg = msg;
	}

	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}	
