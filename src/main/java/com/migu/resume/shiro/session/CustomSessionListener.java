package com.migu.resume.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
/**
 * 自定义session监听
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月22日 下午2:35:01
 * @version: V1.0
 */
public class CustomSessionListener implements SessionListener{

	@Override
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		System.out.println("listen onstart");
	}

	@Override
	public void onStop(Session session) {
		// TODO Auto-generated method stub
		System.out.println("listen onStop");
	}

	@Override
	public void onExpiration(Session session) {
		//删除session
		System.out.println("session过期，删除session");
		CustomShiroSesssionDao.sessionMap.remove(session.getId());
	}
}
