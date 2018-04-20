package com.knight.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/sso")

public class SSOController {
    //全局会话Key
    private final static String KNIGHT_UPMS_SERVER_SESSION_ID = "knight-upms-server-session-id";

    //全局会话key 列表
    private final static String KNIGHT_UPMS_SERVER_SESSION_IDS = "knight-upms-server-session-ids";

    private final static String KNIGHT_UPMS_SERVER_CODE = "knight-upms-server-code";
//
//    @Autowired
//    UpmsSystemService upmsSystemService;
//
//    @Autowired
//    UpmsUserService upmsUserService;
//
//    @Autowired
//    ShiroSessionDao shiroSessionDao;
//
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    @ResponseBody
//    public BaseServerResponse login(HttpServletRequest request, HttpServletResponse response){
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String rememberMe = request.getParameter("rememberMe");
//        if (StringUtils.isBlank(username)){
//            return BaseServerResponse.createByErrorCodeMessage(1,"账号不能为空");
//        }
//        if (StringUtils.isBlank(password)){
//            return BaseServerResponse.createByErrorCodeMessage(1,"密码不能为空");
//        }
//
//        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//        String sessionId = session.getId().toString();
//        //如果已经登录，则回调，防止重复登录
//        String hashCode = RedisUtil.get(KNIGHT_UPMS_SERVER_SESSION_ID +"_"+sessionId);
//        if (StringUtils.isBlank(hashCode)){
//            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
//            try {
//                if (BooleanUtils.toBoolean(rememberMe))
//                    usernamePasswordToken.setRememberMe(true);
//                else
//                    usernamePasswordToken.setRememberMe(false);
//            } catch (UnknownAccountException e){
//                return BaseServerResponse.createByErrorCodeMessage(1,"账号不存在");
//            } catch (IncorrectCredentialsException e){
//                return BaseServerResponse.createByErrorCodeMessage(1,"凭证错误");
//            }catch (LockedAccountException e){
//                return BaseServerResponse.createByErrorCodeMessage(1,"账号已被锁定");
//            }
//            //更新session装填
//
//            RedisUtil.lpush(KNIGHT_UPMS_SERVER_SESSION_IDS,sessionId.toString());
//            //todo 验证账号密码是否正确，如果正确创建code
//            String code = UUID.randomUUID().toString();
//            //全局会话的code
//            RedisUtil.set(KNIGHT_UPMS_SERVER_SESSION_ID+"_"+sessionId,code,(int)subject.getSession().getTimeout() /1000);
//
//        }
//
//        //回调登录前地址
//        String backurl = request.getParameter("backurl");
//        if (!StringUtils.isBlank(backurl)){
//            UpmsSystem upmsSystem = upmsSystemService.selectUpmsSystemByName(PropertiesFileUtil.getInstance().get("app.name"));
//            backurl = null == upmsSystem?"/":upmsSystem.getBasepath();
//            HashMap<String, String> map = new HashMap<>();
//            map.put("backurl",backurl);
//            return BaseServerResponse.createBySuccess("登录成功",map);
//        }else {
//            return BaseServerResponse.createBySuccess("登录成功");
//        }
//
//
//
//    }

}
