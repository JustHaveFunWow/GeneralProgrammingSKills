package com.knight.web.interceptor;

import com.knight.upms.api.UpmsApiService;
import com.knight.upms.dao.model.UpmsUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpmsInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    UpmsApiService upmsApiService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (null != request.getHeader("X-Requested-with") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))){
            return true;
        }
        //登录信息
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UpmsUser upmsUser = upmsApiService.selectUpmsUserByUsername(username);
        request.setAttribute("upmsUser",upmsUser);
        return true;
    }
}
