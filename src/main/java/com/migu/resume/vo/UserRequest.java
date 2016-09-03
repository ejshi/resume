package com.migu.resume.vo;

import javax.validation.constraints.Max;
/**
 * 请求参数
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月1日 下午4:42:34
 * @version: V1.0
 */
public class UserRequest {
	private String name;
	private String age;
	@Max(value=3,message="{demo.id.maxvalue}")
	private int sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
}
