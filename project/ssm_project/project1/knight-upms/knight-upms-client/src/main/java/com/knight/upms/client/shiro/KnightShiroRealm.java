package com.knight.upms.client.shiro;

import com.knight.common.util.PropertiesFileUtil;
import com.knight.upms.api.UpmsApiService;
import com.knight.upms.dao.model.UpmsUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.knight.upms.client.shiro.common.AuthUtils;

import javax.annotation.PostConstruct;

public class KnightShiroRealm extends AuthorizingRealm{


    @PostConstruct
    public void initCredentialsMatcher(){
        setCredentialsMatcher(new KnightCredentialsMatcher());
    }

    @Autowired
    private UpmsApiService upmsApiService;

    /**
     * 授权查询回调函数，进行鉴权但缓存中无用户授权信息时调用， 负责在应用程序中决定用用户的访问控制的方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //设置权限
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();

        String username = (String) principalCollection.getPrimaryPrincipal();
        // get user all role ,and put it to AuthenticationInfo's  String permission
//        for(SysRole role:userInfo.getRoleList()){
//            authorizationInfo.addRole(role.getRole());
//            for(SysPermission p:role.getPermissions()){
//                authorizationInfo.addStringPermission(p.getPermission());
//            }
//        }
        authenticationInfo.addRole("loginedUser");
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
        String password = new String( (char[]) token.getCredentials());

        //client 无密码认证
        String upmsType = PropertiesFileUtil.getInstance("knight-upms-client").get("knight.upms.type");

        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);

        if (upmsUser == null)
            throw new UnknownAccountException();
        if (upmsUser.getLocked() == 1)
            throw new LockedAccountException();
        //根据 upmsType  不同的平台有不同的认证方式，提供不同的 princial
        if ("client".equals(upmsType)){
            return new SimpleAuthenticationInfo(username,password,getName());
        }




        return new SimpleAuthenticationInfo(upmsUser,
                password,
                ByteSource.Util.bytes(upmsUser.getPassword()),
                getName());
    }


    @Override
    public CredentialsMatcher getCredentialsMatcher() {
        return super.getCredentialsMatcher();
    }

    class KnightCredentialsMatcher implements CredentialsMatcher{

        @Override
        public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
            UpmsUser upmsUser = (UpmsUser) token.getPrincipal();

            String password = new String((char[])token.getCredentials());



            return AuthUtils.encryptPassword(password,upmsUser.getSalt()).equals(upmsUser.getPassword());
        }
    }
}
