package com.migu.resume.vo;
/**
 * 请求响应返回数据集
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月1日 下午5:01:05
 * @version: V1.0
 */
public class RemoteResponse {
	private int code;//code =1表示成功;  code !=1 表示失败
    private String message;
    private Object data;
    
	public RemoteResponse() {
		super();
	}
	public RemoteResponse(int code) {
		super();
		this.code = code;
	}
	
	public RemoteResponse(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}

	public RemoteResponse(int code, String message, Object data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
