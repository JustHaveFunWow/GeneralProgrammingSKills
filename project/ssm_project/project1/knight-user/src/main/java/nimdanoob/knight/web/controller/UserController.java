package nimdanoob.knight.web.controller;

import com.knight.common.result.BaseServerResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import nimdanoob.knight.web.dao.model.User;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {


//    @Autowired UserService userService;

    static Map<Integer,User> users = Collections.synchronizedMap(new HashMap<Integer, User>());
    @ApiOperation(value = "获取用户列表",notes = "")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    @RequiresAuthentication
    @RequiresRoles("loginedUser")
    public BaseServerResponse getUserList(){
        ArrayList<User> r = new ArrayList<>(UserController.users.values());
        return BaseServerResponse.createBySuccessMessage("users success");
    }

    @ApiOperation(value = "创建用户",notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user",value = "用户详细实体",required = true,dataType = "User")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public String postUser(@ModelAttribute User user){

        users.put(user.getUserId(),user);
        return "success";
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable Integer id){
        return "success";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String putUser(@PathVariable Integer id,@ModelAttribute User user){
        User u = users.get(id);
        u.setUserName(user.getUserName());
        users.put(id,u);
        return "success";
    }

    @RequestMapping(value = "/id",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable  Integer id){
        users.remove(id);
        return "success";
    }


}
