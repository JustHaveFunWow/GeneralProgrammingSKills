package nimdanoob.knight.web.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;

public class ShiroSessionFactory implements SessionFactory{
    @Override
    public Session createSession(SessionContext initData) {
        ShiroSession shiroSession = new ShiroSession();
        return shiroSession;
    }
}
