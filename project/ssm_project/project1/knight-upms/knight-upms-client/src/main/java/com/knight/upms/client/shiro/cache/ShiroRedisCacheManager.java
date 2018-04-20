package com.knight.upms.client.shiro.cache;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroRedisCacheManager extends AbstractCacheManager {
	private RedisTemplate<byte[], Object> redisTemplate;
	
	public ShiroRedisCacheManager(RedisTemplate<byte[], Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	protected Cache<byte[], Object> createCache(String name) throws CacheException {
		return new ShiroRedisCache<>(redisTemplate, name);
	}
}
