package com.migu.resume.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.migu.resume.persistence.demo.dao.DemoDao;
import com.migu.resume.persistence.demo.module.Demo;
import com.migu.resume.persistence.demo.module.DemoExample;
import com.migu.resume.service.IDemoService;
import com.migu.resume.util.SerializeUtil;
import com.migu.resume.util.cache.JedisManager;
/**
 * 测试用例service
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月5日 下午5:26:52
 * @version: V1.0
 */
@Service("demoService")
public class DemoServiceImpl implements IDemoService {
	@Autowired
	private DemoDao demoDao;
	
	@Autowired
	private JedisManager jedisManager;
	
	@Override
	public Demo getDemoById(int demoId) {
		Demo demo = null;
		try {//@TODO 只是测试缓存的使用方法，实际开发根据需要添加
			byte[] key = (demoId+"").getBytes("UTF-8");
			boolean isExist = jedisManager.exists(0, key);
			if(!isExist){
				demo = demoDao.selectByPrimaryKey(demoId);
				jedisManager.saveValueByKey(0, key, SerializeUtil.serialize(demo), 2*60);
			}else{
				byte[] value = jedisManager.getValueByKey(0, key);
				demo = SerializeUtil.deserialize(value, Demo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return demo;
	}

	@Override
	public List<Demo> selectByExample(DemoExample example) {
		return demoDao.selectByExample(example);
	}

}
