package com.knight.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.knight.common.result.BaseServerResponse;
import com.knight.common.util.PropertiesFileUtil;
import com.knight.common.util.RedisUtil;
import com.knight.upms.api.UpmsSystemService;
import com.knight.upms.api.UpmsUserService;

import com.knight.upms.dao.model.UpmsSystem;
import com.knight.upms.dao.model.UpmsSystemExample;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.knight.upms.client.shiro.ShiroSession;
import com.knight.upms.client.shiro.ShiroSessionDao;
import com.knight.upms.client.shiro.common.UpmsConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/sso")
public class SSOController {
    //全局会话Key
    private final static String KNIGHT_UPMS_SERVER_SESSION_ID = UpmsConstants.KNIGHT_UPMS_SERVER_SESSION_ID;

    //全局会话key 列表
    private final static String KNIGHT_UPMS_SERVER_SESSION_IDS = UpmsConstants.KNIGHT_UPMS_SERVER_SESSION_IDS;

    private final static String KNIGHT_UPMS_SERVER_CODE = UpmsConstants.KNIGHT_UPMS_SERVER_CODE;

    @Reference(version = "1.0.0")
    UpmsSystemService upmsSystemService;

    @Reference(version = "1.0.0")
    UpmsUserService upmsUserService;

    @Reference(version = "1.0.0")
    ShiroSessionDao shiroSessionDao;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request) throws Exception{
        String appid = request.getParameter("appid");
        String backurl = request.getParameter("backurl");
        if (StringUtils.isBlank(appid)){
            throw new RuntimeException("无效访问");
        }
        //判断请求认证系统是否注册
        UpmsSystemExample upmsSystemExample = new UpmsSystemExample();
        upmsSystemExample.createCriteria()
                .andNameEqualTo(appid);
        int count  =upmsSystemService.countByExample(upmsSystemExample);
        if (0 == count){
            throw new RuntimeException(String.format("未注册的系统 %s",appid));
        }
        return "redirect:/sso/login?backUrl=" + URLEncoder.encode(backurl,"utf-8");
    }

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
                subject.login(usernamePasswordToken);
            } catch (UnknownAccountException e){
                return BaseServerResponse.createByErrorCodeMessage(1,"账号不存在");
            } catch (IncorrectCredentialsException e){
                return BaseServerResponse.createByErrorCodeMessage(1,"凭证错误");
            }catch (LockedAccountException e){
                return BaseServerResponse.createByErrorCodeMessage(1,"账号已被锁定");
            }
            //更新session状态
            shiroSessionDao.updateStatus(sessionId, ShiroSession.OnlineStatus.on_line);
            // 全局会话sessionId列表，供会话管理
            RedisUtil.lpush(KNIGHT_UPMS_SERVER_SESSION_IDS, sessionId);
            //todo 验证账号密码是否正确，如果正确创建code

            String code = UUID.randomUUID().toString();
            //全局会话的code
            RedisUtil.set(KNIGHT_UPMS_SERVER_SESSION_ID+"_"+sessionId,code,(int)subject.getSession().getTimeout() /1000);
            //code 检验值
            RedisUtil.set(KNIGHT_UPMS_SERVER_CODE+"_"+code,code,(int)subject.getSession().getTimeout()/1000);

        }

        //回调登录前地址
        String backurl = request.getParameter("backurl");
        if (!StringUtils.isBlank(backurl)){
            UpmsSystem upmsSystem = upmsSystemService.selectUpmsSystemByName(PropertiesFileUtil.getInstance().get("app.name"));
            backurl = null == upmsSystem?"/":upmsSystem.getBasepath();
            HashMap<String, String> map = new HashMap<>();
            map.put("backurl",backurl);
            return BaseServerResponse.createBySuccess("登录成功",map);
        }else {
            return BaseServerResponse.createBySuccess("登录成功");
        }



    }


    @RequestMapping(value = "/code",method = RequestMethod.POST)
    @ResponseBody
    public Object code(HttpServletRequest request){
        String codeParam = request.getParameter("code");
        String code = RedisUtil.get(UpmsConstants.KNIGHT_UPMS_SERVER_CODE+"_"+codeParam);
        if (StringUtils.isBlank(codeParam) || !codeParam.equalsIgnoreCase(code)){
            return BaseServerResponse.createByErrorMessage("无效code");
        }
        return BaseServerResponse.createBySuccessMessage(code);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        //shiro 退出登录
        SecurityUtils.getSubject().logout();
        //退回原地址
        String redirectUrl = request.getHeader("Referer");
        if (null == redirectUrl){
            redirectUrl = "/";
        }
        return "redirect:"+redirectUrl;
    }
}
