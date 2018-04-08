package nimdanoob.knight.web.controller;


import com.knight.common.result.BaseServerResponse;
import nimdanoob.knight.web.common.ErrorCode;
import nimdanoob.knight.web.domain.model.User;
import nimdanoob.knight.web.domain.model.UserExample;
import nimdanoob.knight.web.service.api.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    private static final String ALGORITHM_NAME = "md5";
    private static final int HASH_ITERATIONS =2;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public BaseServerResponse register(@ModelAttribute User user){
        String userName = user.getUserName();
        String pwd = user.getPassword();
        if (userName == null || pwd == null)
            return BaseServerResponse.createByErrorCodeMessage(103,"账号或密码不能为空");
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);

        if (userService.selectFirstByExample(userExample) != null) {
            return BaseServerResponse.createByErrorCodeMessage(103,"该账号已存在");
        }
        User createUser = new User();
        createUser.setUserName(user.getUserName());

        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        String hashedPwd = new SimpleHash(ALGORITHM_NAME, pwd, salt, HASH_ITERATIONS).toHex();
        createUser.setPassword(hashedPwd);
        createUser.setSalt(salt);
        int insertedID = userService.insert(createUser);
        createUser.setUserId(insertedID);
        return BaseServerResponse.createBySuccess(createUser);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public BaseServerResponse login(@RequestParam String userName,@RequestParam String password){
        if (userName == null || password == null)
            return BaseServerResponse.createByErrorCodeMessage(0,"账号或密码不能为空");
        //case
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
//        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e){
            logger.error("=========登录失败==========");
            return BaseServerResponse.createByErrorMessage("账号或密码错误");
            //抛出异常
        }

//        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        User user = userService.authUserAndPwd(userName, password);
        if (user == null) {
            return BaseServerResponse.createByErrorCodeMessage(ErrorCode.AUTH_ERROR,"账号或密码错误");
        }
        // todo  生成 token 并存储到redis
        return BaseServerResponse.createBySuccess(user);
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    @ResponseBody
    public BaseServerResponse logout(){
        //退出登录
        SecurityUtils.getSubject().logout();

        return BaseServerResponse.createBySuccess();
    }


}
