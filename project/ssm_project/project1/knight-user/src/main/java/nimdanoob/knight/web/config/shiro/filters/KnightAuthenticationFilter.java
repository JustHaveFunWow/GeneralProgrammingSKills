package nimdanoob.knight.web.config.shiro.filters;

import nimdanoob.knight.web.common.Constants;
import com.knight.common.util.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class KnightAuthenticationFilter extends org.apache.shiro.web.filter.authc.AuthenticationFilter{


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        return validateClient(request,response);
//        return super.isAccessAllowed(request, response, mappedValue);
    }

    private boolean validateClient(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) (session.getTimeout()/100);

        String cacheClientSession = RedisUtil.get(Constants.SESSION_ID_PREFIX+sessionId);
        if (StringUtils.isNotBlank(cacheClientSession)){
            //更新code有效期
            RedisUtil.set(Constants.SESSION_ID_PREFIX+sessionId,cacheClientSession,timeOut);
            Jedis jedis = RedisUtil.getJedis();
            jedis.expire(cacheClientSession,timeOut);
            jedis.close();
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
