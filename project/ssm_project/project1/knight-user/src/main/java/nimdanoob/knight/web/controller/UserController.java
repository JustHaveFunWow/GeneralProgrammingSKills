package nimdanoob.knight.web.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import nimdanoob.knight.web.domain.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {


//    @Autowired UserService userService;

    static Map<Long,User> users = Collections.synchronizedMap(new HashMap<Long, User>());
    @ApiOperation(value = "获取用户列表",notes = "")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<User> getUserList(){
        ArrayList<User> r = new ArrayList<>(UserController.users.values());
        return r;
    }

    @ApiOperation(value = "创建用户",notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user",value = "用户详细实体",required = true,dataType = "User")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user){
        
        users.put(user.getUserId(),user);
        return "success";
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){
        return users.get(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id,@ModelAttribute User user){
        User u = users.get(id);
        u.setUserName(user.getUserName());
        users.put(id,u);
        return "success";
    }

    @RequestMapping(value = "/id",method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable  Long id){
        users.remove(id);
        return "success";
    }


}
