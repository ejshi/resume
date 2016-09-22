package com.migu.resume.util.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.session.Session;

import com.migu.resume.util.CollectionUtils;
import com.migu.resume.util.JSONUtils;
import com.migu.resume.util.SerializeUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;
/**
 * JedisUtil工具类
 * <p>Company: richinfo</p>
 * @author: jshi
 * @date: 2016年9月5日 下午2:05:36
 * @version: V1.0
 */
public class JedisManager {
    private JedisPool jedisPool;
    
    private static final int DB_INDEX = 0;

    public Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
        } catch (Exception e) {
            throw new JedisConnectionException(e);
        }
        return jedis;
    }

    public void returnResource(Jedis jedis) {
        if (jedis == null) return;
        jedis.close();
//        getJedisPool().close();
    }

    public byte[] getValueByKey(int dbIndex, byte[] key, int expireTime) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            if (jedis.exists(key)) {
                jedis.expire(key, expireTime);
            }
            result = jedis.get(key);
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public byte[] getValueByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        byte[] result = null;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.get(key);
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }
    /**
     * 获取制定key规则的的redis存储数据，如命令：keys session:*
     * @param dbIndex
     * @param pattern
     * @return
     * @throws Exception
     */
    public Collection<Session> AllSession(int dbIndex, byte[] pattern) throws Exception {
        Jedis jedis = null;
        Set<Session> resultSet = new HashSet<Session>();
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            Set<byte[]> keys = jedis.keys(pattern);
            if(!CollectionUtils.isEmpty(keys)){
            	for(byte[] key : keys){
            		Object obj = SerializeUtil.deserialize(jedis.get(key));
            		if(obj instanceof Session){
            			resultSet.add((Session)obj);
            		}
            	}
            }
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
        return resultSet;
    }
    public long deleteByKey(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.del(key);
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public boolean exists(int dbIndex, byte[] key) throws Exception {
        Jedis jedis = null;
        boolean result = false;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            result = jedis.exists(key);
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public void saveValueByKey(int dbIndex, byte[] key, byte[] value, int expireTime) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.select(dbIndex);
            jedis.set(key, value);
            if (expireTime > 0) jedis.expire(key, expireTime);
        } catch (Exception e) {
            throw e;
        } finally {
            returnResource(jedis);
        }
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 存储信息到redis
     *
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    public boolean save(String key, Object value) {
        return saveForExpireTime(key, value, 0);
    }

    public boolean saveForExpireTime(String keyStr, Object value, int expireTime) {
        Jedis jedis = null;
        boolean isBroken = false;
        try {
            jedis = getJedis();
            jedis.select(DB_INDEX);
            jedis.set(keyStr, JSONUtils.toJSONString(value));
            if (expireTime > 0) jedis.expire(keyStr, expireTime);
        } catch (Exception e) {
            isBroken = true;
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return !isBroken;
    }

    /**
     * 在redis中根据key获取值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return getForExpireTime(key, 0);
    }

    public String getForExpireTime(String key, int expireTime) {
        Jedis jedis = null;
        String result = "";
        try {
            jedis = getJedis();
            jedis.select(DB_INDEX);
            result = jedis.get(key);
            if (expireTime > 0) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public long rpush(String key, String[] values, int expireTime) {
        if (key == null || key == "") return 0;

        Jedis jedis = null;
        long result = 0;
        try {
            jedis = getJedis();
            jedis.select(DB_INDEX);
            result = jedis.rpush(key, values);
            if (expireTime > 0) {
                jedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public String rpop(String key) {
        if (key == null || key == "") return null;

        Jedis jedis = null;
        String result = null;
        try {
            jedis = getJedis();
            jedis.select(DB_INDEX);
            result = jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    public List<String> lrange(String key, long start, long end) {
        if (key == null || key == "") return null;

        Jedis jedis = null;
        List<String> result = null;
        try {
            jedis = getJedis();
            jedis.select(DB_INDEX);
            result = jedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
        return result;
    }
}
