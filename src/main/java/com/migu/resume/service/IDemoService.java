package com.migu.resume.service;

import java.util.List;

import com.migu.resume.persistence.demo.module.Demo;
import com.migu.resume.persistence.demo.module.DemoExample;

public interface IDemoService {
	public Demo getDemoById(int demoId);
	 public List<Demo> selectByExample(DemoExample example);
}
