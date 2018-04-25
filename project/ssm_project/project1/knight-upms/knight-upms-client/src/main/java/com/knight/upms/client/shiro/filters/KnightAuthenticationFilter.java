package com.knight.upms.client.shiro.filters;

import com.alibaba.fastjson.JSONObject;
import com.knight.common.util.PropertiesFileUtil;
import com.knight.common.util.RedisUtil;
import com.knight.upms.client.shiro.common.UpmsConstants;
import com.knight.upms.client.shiro.util.RequestParameterUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Log4j2
public class KnightAuthenticationFilter extends org.apache.shiro.web.filter.authc.AuthenticationFilter{

    private static Logger getLog() {
        return log;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        //
        String upmsType = PropertiesFileUtil.getInstance("knight-upms-client").get("knight.upms.type");
        if ("client".equals(upmsType)){
            return validateClient(request,response);
        }
        if ("server".equals(upmsType)){
            return subject.isAuthenticated();
        }
        return false;
//        return super.isAccessAllowed(request, response, mappedValue);
    }

    private boolean validateClient(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        Session session = subject.getSession();
        String sessionId = session.getId().toString();
        int timeOut = (int) (session.getTimeout()/100);

        //判断局部会话是否登录
        String cacheClientSession = RedisUtil.get(UpmsConstants.KNIGHT_UPMS_CLIENT_SESSION_ID+sessionId);
        if (StringUtils.isNotBlank(cacheClientSession)){
            //更新code有效期
            RedisUtil.set(UpmsConstants.KNIGHT_UPMS_CLIENT_SESSION_ID+sessionId,cacheClientSession,timeOut);
            Jedis jedis = RedisUtil.getJedis();
            jedis.expire(UpmsConstants.KNIGHT_UPMS_CLIENT_SESSION_IDS+"-"+cacheClientSession,timeOut);
            jedis.close();
            //移除url中的code 参数
            if (null != request.getParameter("code")){
                String backUrl = RequestParameterUtil.getParameterWithOutCode(WebUtils.toHttp(request));
                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
                //直接跳转
                try {
                    httpServletResponse.sendRedirect(backUrl);
                } catch (IOException e){
                    getLog().error("局部会话已登录, 移除code参数跳转出错: ",e);
                }
            }
            return true;
        }
        //判断是否有认证中心code
        String code = request.getParameter("upms_code");
        if (StringUtils.isNotBlank(code)){
            //HttpPost去检验code
            try {
                StringBuffer ssoServerUrl = new StringBuffer(PropertiesFileUtil.getInstance("knight-upms-client").get("knight.upms.sso.server.url")) ;
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(ssoServerUrl.toString() + "/sso/code");
                HttpResponse httpResponse = httpClient.execute(httpPost);
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    HttpEntity httpEntity = httpResponse.getEntity();
                    JSONObject result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
                    if (1 == result.getIntValue("code") && result.getString("data").equals(code)){
                        //code 检验正确，创建局部会话
                        RedisUtil.set(UpmsConstants.KNIGHT_UPMS_CLIENT_SESSION_ID+"_"+sessionId
                        ,code,timeOut);
                        //保存code对应的局部会话sessionId， 方便退出操作
                        RedisUtil.sadd(UpmsConstants.KNIGHT_UPMS_CLIENT_SESSION_IDS + "_"+code,sessionId,timeOut);
                        getLog().debug("当前code ={}，对应的注册系统个数{}个",code,RedisUtil.getJedis().scard(UpmsConstants.KNIGHT_UPMS_CLIENT_SESSION_IDS+"_"+code));
                    }

                }
            } catch (IOException e){
                getLog().error("已拿到code, 移除code参数跳转出错",e);
            }
        }

        return false;

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        StringBuffer ssoServerUrl =  new StringBuffer(PropertiesFileUtil.getInstance("knight-upms-client").get("knight.upms.sso.server.url"));
        //server 需要登录
        String upmsType = PropertiesFileUtil.getInstance("knight-upms-client").get("knight.upms.type");
        if ("server".equals(upmsType)){
            WebUtils.toHttp(response).sendRedirect(ssoServerUrl.append("/sso/login").toString());
            return false;
        }

        ssoServerUrl.append("/sso/index").append("?").append("appid").append("=").append(PropertiesFileUtil.getInstance("knight-upms-client").get("knight.upms.appID"));
        //回调地址
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        StringBuffer backUrl = httpServletRequest.getRequestURL();
        String queryString = httpServletRequest.getQueryString();
        if (StringUtils.isNotBlank(queryString)){
            backUrl.append("?").append(queryString);
        }
        ssoServerUrl.append("&").append("backurl").append("=").append(URLEncoder.encode(backUrl.toString(),"utf-8"));
        WebUtils.toHttp(response).sendRedirect(ssoServerUrl.toString());

        return false;
    }
}
