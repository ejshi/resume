package com.migu.resume.vo;

import javax.validation.constraints.Max;

/**
 * 测试用例
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月5日 下午5:30:46
 * @version: V1.0
 */
public class DemoRequest {
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

