package com.migu.resume.shiro.session;

import java.io.Serializable;
import java.util.Collection;

import org.apache.shiro.session.Session;

/**
 * session管理接口
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月22日 上午10:48:51
 * @version: V1.0
 */
public interface ShiroSessionRepository {
	/**
	 * 存储session
	 * @param session
	 * @return
	 */
	void create(Session session);
	/**
	 * 读取session
	 * @param sessionId
	 * @return
	 */
	Session readSession(Serializable sessionId);
	/**
	 * 删除session
	 * @param session
	 */
	void delete(Serializable sessionId);
	/**
	 * 获取所有session
	 * @return
	 */
	Collection<Session> getActiveSessions();
}
