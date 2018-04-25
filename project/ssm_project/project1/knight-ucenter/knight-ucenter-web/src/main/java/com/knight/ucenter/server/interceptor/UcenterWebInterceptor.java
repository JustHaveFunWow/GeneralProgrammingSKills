package com.knight.ucenter.server.interceptor;

import com.knight.common.util.PropertiesFileUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class UcenterWebInterceptor implements HandlerInterceptor{
    public static Logger getLog() {
        return log;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //过滤ajax
        if (null != request.getHeader("X-Requested-With") && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))){
            return true;
        }
        //静态资源配置信息
        String appName = PropertiesFileUtil.getInstance().get("app.name");
        request.setAttribute("appName",appName);

        return true;
    }
}
