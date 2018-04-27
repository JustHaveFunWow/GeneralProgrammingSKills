package com.knight.upms.client.shiro;

import com.knight.common.util.RedisUtil;
import com.knight.upms.client.shiro.common.UpmsConstants;
import com.knight.upms.client.shiro.util.SerializableUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.Set;


@Service
public class ShiroSessionDao extends CachingSessionDAO{

    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionDao.class);


    // 会话key
    private final static String KNIGHT_UPMS_SHIRO_SESSION_ID = "knight-upms-shiro-session-id";
    // 全局会话key
    private final static String KNIGHT_UPMS_SERVER_SESSION_ID = "knight-upms-server-session-id";
    // 全局会话列表key
    private final static String KNIGHT_UPMS_SERVER_SESSION_IDS = "knight-upms-server-session-ids";
    // code key
    private final static String KNIGHT_UPMS_SERVER_CODE = "knight-upms-server-code";
    // 局部会话key
    private final static String KNIGHT_UPMS_CLIENT_SESSION_ID = "knight-upms-client-session-id";
    // 单点同一个code所有局部会话key
    private final static String KNIGHT_UPMS_CLIENT_SESSION_IDS = "knight-upms-client-session-ids";

    //会话的过期时间
    private int expireTime = 3000 * 1000;

    //特殊配置 用于在没有redis时，将session放到 Ehcache 中
    private Boolean onlyEhcache;


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        assignSessionId(session,sessionId);
        RedisUtil.set(KNIGHT_UPMS_SHIRO_SESSION_ID+"_"+sessionId,SerializableUtil.serialize(session),
                (int) session.getTimeout() / 1000);

        logger.debug("doCreate >>>>> sessionId={}",sessionId);
        return sessionId;
    }

    @Override
    protected void doUpdate(Session session) {
        try {
            if (session instanceof ValidatingSession &&!((ValidatingSession)session).isValid()){
                return;
            }
        }catch (Exception e){
            logger.error("ValidatingSession");
        }
        ShiroSession shiroSession = (ShiroSession) session;
        ShiroSession cachedShiroSession = (ShiroSession)doReadSession(shiroSession.getId());
        if (null != cachedShiroSession){
            cachedShiroSession.setStatus(cachedShiroSession.getStatus());
            cachedShiroSession.setAttribute("FORCE_LOGOUT",cachedShiroSession.getAttribute("FORCE_LOGOUT"));
        }
        RedisUtil.set(KNIGHT_UPMS_SHIRO_SESSION_ID +"_" +session.getId(),SerializableUtil.serialize(session),
                (int) (session.getTimeout()/1000));

    }

    @Override
    protected void doDelete(Session session) {
        logger.debug("begin doDelete {}",session);
        String sessionId = session.getId().toString();
        String upmsType = ObjectUtils.toString(session.getAttribute(UpmsConstants.UPMS_TYPE));
        if ("client".equals(upmsType)){
            RedisUtil.get(KNIGHT_UPMS_CLIENT_SESSION_ID+"_"+sessionId);
            Jedis jedis = RedisUtil.getJedis();
            jedis.del(KNIGHT_UPMS_CLIENT_SESSION_ID+"_"+sessionId);
            jedis.srem(KNIGHT_UPMS_CLIENT_SESSION_ID +"_"+sessionId);
            jedis.close();
        }

        if ("server".equals(upmsType)){
            //当前全局会话code
            String code = RedisUtil.get(KNIGHT_UPMS_SERVER_SESSION_ID+"_"+sessionId);
            //清楚全局会话
            RedisUtil.remove(KNIGHT_UPMS_SERVER_SESSION_ID+"_"+sessionId);
            // 清楚所有局部会话
            Jedis jedis = RedisUtil.getJedis();
            Set<String> clientSessionIds = jedis.smembers(KNIGHT_UPMS_CLIENT_SESSION_IDS + "_" + code);
            for (String clientSessionId : clientSessionIds) {
                jedis.del(KNIGHT_UPMS_CLIENT_SESSION_ID + "_" + clientSessionId);
                jedis.srem(KNIGHT_UPMS_CLIENT_SESSION_IDS + "_" + code, clientSessionId);
            }
            logger.debug("当前code={}，对应的注册系统个数：{}个", code, jedis.scard(KNIGHT_UPMS_CLIENT_SESSION_IDS + "_" + code));
            jedis.close();
            // 维护会话id列表，提供会话分页管理
            RedisUtil.lrem(KNIGHT_UPMS_SERVER_SESSION_IDS, 1, sessionId);
        }
        // 删除session
        RedisUtil.remove(KNIGHT_UPMS_SHIRO_SESSION_ID + "_" + sessionId);
        logger.debug("doUpdate >>>>> sessionId={}", sessionId);

    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("begin doReadSession {}",sessionId);
        Session session = null;

        String value =RedisUtil.get(UpmsConstants.KNIGHT_UPMS_SHIRO_SESSION_ID +"_" +sessionId);
        if (StringUtils.isNotBlank(value)){
            session = SerializableUtil.deserialize(value);
            logger.info("sessionId {} ",sessionId);
        }
        return session;

//        Session session = getCachedSession(sessionId);
//        if (session == null || session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null){
//            session = this.doReadSession(sessionId);
//            if (session == null){
//                throw new UnknownSessionException("There is no session with id ["+sessionId+"]");
//            }else {
//                cache(session,session.getId());
//            }
//        }
    }

    public void updateStatus(Serializable sessionId, ShiroSession.OnlineStatus onlineStatus){
        ShiroSession session = (ShiroSession) doReadSession(sessionId);
        if (null == session)
            return;
        session.setStatus(onlineStatus);
        RedisUtil.set(KNIGHT_UPMS_SHIRO_SESSION_ID +"_" +session.getId(), SerializableUtil.serialize(session),(int) session.getTimeout() / 1000);
    }
}
