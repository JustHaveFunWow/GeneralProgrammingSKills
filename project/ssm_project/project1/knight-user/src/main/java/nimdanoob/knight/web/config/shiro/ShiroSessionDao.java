package nimdanoob.knight.web.config.shiro;

import nimdanoob.knight.web.common.Constants;
import nimdanoob.knight.web.utils.SerializeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;


@Service
public class ShiroSessionDao extends CachingSessionDAO{

    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionDao.class);

    private String prefix = Constants.SESSION_ID_PREFIX;

    //会话的过期时间
    private int expireTime = 3000 * 1000;

    //特殊配置 用于在没有redis时，将session放到 Ehcache 中
    private Boolean onlyEhcache;

    @Autowired
    private JedisPool jedisPool;

    @Override
    protected void doUpdate(Session session) {
        try {
            if (session instanceof ValidatingSession &&!((ValidatingSession)session).isValid()){
                return;
            }
        }catch (Exception e){
            logger.error("ValidatingSession");
        }


    }

    @Override
    protected void doDelete(Session session) {
        logger.debug("begin doDelete {}",session);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(prefix + session.getId());
            this.uncache(session);
            logger.debug("shiro session id {} 被删除",session.getId());
        }catch (Exception e){
            logger.warn("删除 session 失败",session.getId());
        } finally {
            if (jedis !=null){
                jedis.close();
            }
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        assignSessionId(session,sessionId);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            session.setTimeout(expireTime);
            jedis.setex(prefix + sessionId,expireTime, SerializeUtils.serializaToString(((ShiroSession) session)));

        } catch (Exception e){
            logger.error("创建session 失败",e);
        } finally {
            jedis.close();
        }

        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("begin doReadSession {}",sessionId);
        Jedis jedis = jedisPool.getResource();
        Session session = null;
        try {
            String key = prefix + sessionId;
            String value = jedis.get(key);
            if (StringUtils.isNotBlank(value)){
                session = SerializeUtils.deserializeFromString(value);
                logger.info("sessionId {} ttl {}",sessionId, jedis.ttl(key));
            }
        } catch (Exception e){
            logger.error("读取sessionshib");
        }finally {
            jedis.close();
        }
//        Session session = getCachedSession(sessionId);
//        if (session == null || session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null){
//            session = this.doReadSession(sessionId);
//            if (session == null){
//                throw new UnknownSessionException("There is no session with id ["+sessionId+"]");
//            }else {
//                cache(session,session.getId());
//            }
//        }
        return session;

    }
}
