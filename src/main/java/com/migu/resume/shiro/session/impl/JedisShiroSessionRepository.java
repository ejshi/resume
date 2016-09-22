package com.migu.resume.shiro.session.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;

import com.migu.resume.shiro.session.ShiroSessionRepository;
import com.migu.resume.util.LoggerUtils;
import com.migu.resume.util.SerializeUtil;
import com.migu.resume.util.cache.JedisManager;
/**
 * redis实现session管理
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月22日 上午10:56:45
 * @version: V1.0
 */
public class JedisShiroSessionRepository implements ShiroSessionRepository {
	private final static String REDIS_SHIRO_SESSION = "resume_shiro_session:";//redis存储key前缀
	 public static final String REDIS_SHIRO_SESSION_ALL = "*resume_shiro_session:*";
    private static final int SESSION_VAL_TIME_SPAN = 18000;
    private static final int DB_INDEX = 0;
	
    @Resource
	private JedisManager jedisManager;
	
	@Override
	public void create(Session session) {
		if(null==session || null==session.getId()){
			throw new NullPointerException("session is null");
		}
		try {
			byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));
			long sessionTimeOut = session.getTimeout() / 1000;
			Long expireTime = sessionTimeOut + SESSION_VAL_TIME_SPAN + (5 * 60);
			jedisManager.saveValueByKey(DB_INDEX, key, SerializeUtil.serialize(session), expireTime.intValue());
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(), e, "save session error，id:[%s]",session.getId());
		}
	}

	@Override
	public Session readSession(Serializable sessionId) {
		if(sessionId==null){
			throw new NullPointerException("sessionId is null");
		}
		try {
			byte[] key = SerializeUtil.serialize(buildRedisSessionKey(sessionId));
			Session session = SerializeUtil.deserialize(jedisManager.getValueByKey(DB_INDEX, key),Session.class);
			return session;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Serializable sessionId) {
		if(sessionId==null){
			throw new NullPointerException("session is null");
		}
		try {
			byte[] key = SerializeUtil.serialize(buildRedisSessionKey(sessionId));
			jedisManager.deleteByKey(DB_INDEX, key);
		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtils.fmtError(getClass(), e, "删除session出现异常，sessionId:[%s]",sessionId);
		}
	}

	@Override
	public Collection<Session> getActiveSessions() {
		try {
			return jedisManager.AllSession(DB_INDEX, SerializeUtil.serialize(REDIS_SHIRO_SESSION_ALL));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 构建redis key
	 * @param sessionId
	 * @return
	 */
	private static String buildRedisSessionKey(Serializable sessionId) {
        return REDIS_SHIRO_SESSION + sessionId;
    }
}
