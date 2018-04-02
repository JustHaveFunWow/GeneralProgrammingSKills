package nimdanoob.knight.web.config.shiro;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;


@Service
public class ShiroSessionDao extends CachingSessionDAO{

    private static final Logger logger = LoggerFactory.getLogger(ShiroSessionDao.class);

    //保存到Redis中 key的前缀
    private String prefix = "";
    //会话的过期时间
    private int expireTime = 3000 * 1000;

    //特殊配置 用于在没有redis时，将session放到 Ehcache 中
    private Boolean onlyEhcache;

    @Autowired
    private JedisPool jedisPool;

    @Override
    protected void doUpdate(Session session) {

    }

    @Override
    protected void doDelete(Session session) {

    }

    @Override
    protected Serializable doCreate(Session session) {
        return null;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = getCachedSession(sessionId);
        if (session == null || session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY) == null){
            session = this.doReadSession(sessionId);
            if (session == null){
                throw new UnknownSessionException("There is no session with id ["+sessionId+"]");
            }else {
                cache(session,session.getId());
            }
        }
        return session;

    }
}
