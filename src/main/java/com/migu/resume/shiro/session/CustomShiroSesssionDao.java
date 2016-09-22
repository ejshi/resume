package com.migu.resume.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import com.migu.resume.util.LoggerUtils;
/**
 * 自定义shiro session管理dao
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月22日 上午10:20:39
 * @version: V1.0
 */
public class CustomShiroSesssionDao extends AbstractSessionDAO{
    private static Logger logger = Logger.getLogger(CustomShiroSesssionDao.class);  
	//存放session信息
	public static Map<Serializable,Session> sessionMap = new HashMap<Serializable,Session>();
	@Override
	public void update(Session session) throws UnknownSessionException {
		logger.info("update session now");
		sessionMap.put(session.getId(), session);
	}
	@Override
	public void delete(Session session) {
		logger.info("delete session now");
		sessionMap.remove(session.getId());
	}

	@Override
	public Collection<Session> getActiveSessions() {
		return sessionMap.values();
	}

	@Override
	protected Serializable doCreate(Session session) {
		logger.info("create session now");
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);  
		sessionMap.put(sessionId, session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		logger.info("read session now");
		return sessionMap.get(sessionId);
	}

}
