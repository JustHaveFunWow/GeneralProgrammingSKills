package com.knight.upms.client.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ShiroSessionListener implements SessionListener{

    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionListener.class);

    @Autowired
    private ShiroSessionDao shiroSessionDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void onStart(Session session) {
        logger.debug("session {} onStart",session.getId());

    }

    @Override
    public void onStop(Session session) {
        shiroSessionDao.delete(session);
        Jedis jedis = jedisPool.getResource();
        jedis.publish("shiro,session.uncache",(String)session.getId());
        logger.debug("session {} onStop",session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        shiroSessionDao.delete(session);
        Jedis jedis = jedisPool.getResource();
        jedis.publish("shiro.session.uncache",(String)session.getId());
        logger.debug("session {} onExpiration",session.getId());
    }
}
