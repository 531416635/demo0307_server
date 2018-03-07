package com.xiao.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Resource;
import java.util.*;

@Component("jedisManager")
public class JedisManager {

	private static final Logger logger = LoggerFactory.getLogger(JedisManager.class);

	@Resource
	private JedisPool jedisPool;

	@Value("${spring.redis.database}")
	private int dbIndex;
	
	/**
	 * 获取redis连接
	 * @return
	 */
	public Jedis getJedis() {
		Jedis jedis;
		try {
			logger.info("开始获取redis连接。。。。");
			jedis = jedisPool.getResource();
			logger.info("获取redis连接成功。。。。");
		} catch (Exception e) {
			logger.info("获取redis连接失败。。。。");
			throw new JedisConnectionException(e);
		}
		return jedis;
	}

	/**
	 * 释放redis连接
	 * @param jedis
	 * @param isBroken
	 */
	public void returnResource(Jedis jedis, boolean isBroken) {
		if (jedis == null){
			logger.info("redis连接为空。。。。");
			return;
		}
		/**
		 * @deprecated starting from Jedis 3.0 this method will not be exposed.
		 *             Resource cleanup should be done using @see
		 *             {@link Jedis#close()} if (isBroken){
		 *             getJedisPool().returnBrokenResource(jedis); }else{
		 *             getJedisPool().returnResource(jedis); }
		 */
		jedis.close();
		logger.info("关闭redis连接成功。。。。");
	}

	/**
	 * 根据key在dbIndex获取redis中的值
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public byte[] getValueByKey(byte[] key) throws Exception {
		Jedis jedis = null;
		byte[] result;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			result = jedis.get(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 根据key删除
	 * @param key
	 * @throws Exception
	 */
	public void deleteByKey(byte[] key) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			Long result = jedis.del(key);
			logger.info("删除key值为{}的结果为{}",key,result);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 保存数据（可设置过期时间）
	 * @param key
	 * @param value
	 * @param expireTime 设置过期时间，单位为秒
	 * @throws Exception
	 */
	public void saveValueByKey( byte[] key, byte[] value,
			int expireTime) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.set(key, value);
			if (expireTime > 0)
				jedis.expire(key, expireTime);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 获取数据
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getValueByKey(String key) {
		Jedis jedis = null;
		String result;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			result = jedis.get(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 将 key 中储存的数字加上指定的增量值
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void incrBy(String key, Long value) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.incrBy(key, value);
			//设置自增键失效时间
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 24);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date date = cal.getTime();
			Date date1 = new Date();
			Integer time = (int) ((date.getTime() - date1.getTime())/1000);
			jedis.expire(key, time);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 删除数据
	 * @param key
	 * @throws Exception
	 */
	public void deleteByKey(String key) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			Long result = jedis.del(key);
			logger.info("删除key值为{}的结果为{}",key,result);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 保存数据（可设置过期时间）
	 * @param key
	 * @param value
	 * @param expireTime  设置过期时间，单位为秒
	 * @throws Exception
	 */
	public void saveValueByKey(String key, String value,
			int expireTime) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.set(key, value);
			if (expireTime > 0)
				jedis.expire(key, expireTime);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 将一个或多个成员元素加入到集合中，已经存在于集合的成员元素将被忽略（可设置过期时间）
	 * @param key
	 * @param value
	 * @param expireTime
	 * @throws Exception
	 */
	public void sadd( String key, String[] value, int expireTime)
			throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.sadd(key, value);
			if (expireTime > 0)
				jedis.expire(key, expireTime);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 返回集合中的所有的成员
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Set<String> smember(String key) throws Exception {
		Set<String> s;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			s = jedis.smembers(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return s;
	}

	/**
	 * 判断 member 元素是否集合 key 的成员
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public boolean sismember( String key, String value)
			throws Exception {
		boolean s;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			s = jedis.sismember(key, value);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return s;
	}

	/**
	 * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
	 * @param key
	 * @param members
	 * @throws Exception
	 */
	public void sdel( String key, String... members)
			throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.srem(key, members);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 返回集合中元素的数量。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public long scount(String key) throws Exception {
		long s;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			s = jedis.scard(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return s;
	}

	/**
	 * 为哈希表中的字段赋值 
	 * @param key
	 * @param propkey
	 * @param value
	 * @param expireTime
	 * @throws Exception
	 */
	public void hset( String key, String propkey, String value,
			int expireTime) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.hset(key, propkey, value);
			if (expireTime > 0)
				jedis.expire(key, expireTime);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 为哈希表中的字段值加上指定增量值
	 * @param key
	 * @param propkey
	 * @param value
	 * @throws Exception
	 */
	public void hincrBy(String key, String propkey, Long value)
			throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.hincrBy(key, propkey, value);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 用于同时将多个 field-value (字段-值)对设置到哈希表中
	 * @param key
	 * @param pv
	 * @param expireTime
	 * @throws Exception
	 */
	public void hmset( String key, Map<String, String> pv,
			int expireTime) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.hmset(key, pv);
			if (expireTime > 0)
				jedis.expire(key, expireTime);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 用于返回哈希表中指定字段的值
	 * @param key
	 * @param propkey
	 * @return
	 * @throws Exception
	 */
	public String hget( String key, String propkey)
			throws Exception {
		String s;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			s = jedis.hget(key, propkey);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return s;
	}

	/**
	 * 用于返回哈希表中，一个或多个给定字段的值
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<String> hmget( String key) throws Exception {
		List<String> l;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			l = jedis.hmget(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return l;
	}

	/**
	 * 用于检查给定 key 是否存在
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public boolean existsKey( String key) throws Exception {
		boolean l;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			l = jedis.exists(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return l;
	}

	/**
	 * 用于查找所有符合给定模式 pattern 的 key 
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public Set<String> keys(String pattern) throws Exception {
		Set<String> l;
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			l = jedis.keys(pattern);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return l;
	}
	
	/**
	 * 设置键的到期时间
	 * 如果成功地为该键设置了超时时间，返回 1,如果键不存在或无法设置超时时间，返回 0
	 * @param key
	 * @param seconds
	 * @return
	 */
	public void expire(final String key, final int seconds) {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.select(dbIndex);
			jedis.expire(key,seconds);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		
	}

}
