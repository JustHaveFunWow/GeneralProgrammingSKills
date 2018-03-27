package nimdanoob.knight.web.controller;


import com.knight.common.result.BaseServerResponse;
import net.sf.ehcache.search.impl.BaseResult;
import nimdanoob.knight.web.domain.model.User;
import nimdanoob.knight.web.domain.model.UserExample;
import nimdanoob.knight.web.service.api.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.soap.SOAPBinding;

@Controller
@RequestMapping(value = "/user")
public class LoginController {

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
    public BaseServerResponse login(@ModelAttribute User user){
        if (user.getUserName() == null || user.getPassword() == null)
            return BaseServerResponse.createByErrorCodeMessage(0,"账号或密码不能为空");
        //case
        return null;
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){


        return null;
    }


}
