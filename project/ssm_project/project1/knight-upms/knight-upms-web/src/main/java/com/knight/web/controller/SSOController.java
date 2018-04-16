package com.knight.web.controller;


import com.knight.common.result.BaseServerResponse;
import com.knight.common.util.RedisUtil;
import com.knight.upms.api.UpmsSystemService;
import com.knight.upms.api.UpmsUserService;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shiro.ShiroSessionDao;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/sso")

public class SSOController {
    //全局会话Key
    private final static String KNIGHT_UPMS_SERVER_SESSION_ID = "knight-upms-server-session-id";

    //全局会话key 列表
    private final static String KNIGHT_UPMS_SERVER_SESSION_IDS = "knight-upms-server-session-ids";

    private final static String KNIGHT_UPMS_SERVER_CODE = "knight-upms-server-code";

    @Autowired
    UpmsSystemService upmsSystemService;

    @Autowired
    UpmsUserService upmsUserService;

    @Autowired
    ShiroSessionDao shiroSessionDao;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseServerResponse login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        if (StringUtils.isBlank(username)){
            return BaseServerResponse.createByErrorCodeMessage(1,"账号不能为空");
        }
        if (StringUtils.isBlank(password)){
            return BaseServerResponse.createByErrorCodeMessage(1,"密码不能为空");
        }

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        //如果已经登录，则回调，防止重复登录
        String hashCode = RedisUtil.get(KNIGHT_UPMS_SERVER_SESSION_ID +"_"+sessionId);
        if (StringUtils.isBlank(hashCode)){
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            try {
                if (BooleanUtils.toBoolean(rememberMe))
                    usernamePasswordToken.setRememberMe(true);
                else
                    usernamePasswordToken.setRememberMe(false);
            } catch (UnknownAccountException e){
                return BaseServerResponse.createByErrorCodeMessage(1,"账号不存在");
            } catch (IncorrectCredentialsException e){
                return BaseServerResponse.createByErrorCodeMessage(1,"凭证错误");
            }catch (LockedAccountException e){
                return BaseServerResponse.createByErrorCodeMessage(1,"账号已存在");
            }
        }



    }

}
