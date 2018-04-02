package nimdanoob.knight.web.config.shiro;

import nimdanoob.knight.web.domain.model.User;
import nimdanoob.knight.web.domain.model.UserExample;
import nimdanoob.knight.web.service.api.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class KnightShiroRealm extends AuthorizingRealm{
    @Resource
    private UserService userService;

    /**
     * 授权查询回调函数，进行鉴权但缓存中无用户授权信息时调用， 负责在应用程序中决定用用户的访问控制的方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();
        // get user all role ,and put it to AuthenticationInfo's  String permission
//        for(SysRole role:userInfo.getRoleList()){
//            authorizationInfo.addRole(role.getRole());
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }

        return authenticationInfo;
    }

    /**
     * 主要是用来做身份认证的，也就是说验证用户 的身份凭证是否正确
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[])token.getCredentials());
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(username);
        User user = userService.selectFirstByExample(userExample);

        if (user == null)
            throw new UnknownAccountException();
        // if locked return LockedAccountException

        //使用CredentialsMatcher 进行密码匹配
        if (username == null)
            return null;
        return new SimpleAuthenticationInfo(user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName());
    }
}
