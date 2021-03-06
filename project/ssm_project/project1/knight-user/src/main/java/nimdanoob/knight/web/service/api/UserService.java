package nimdanoob.knight.web.service.api;

import com.knight.common.base.BaseService;
import nimdanoob.knight.web.dao.model.User;
import nimdanoob.knight.web.dao.model.UserExample;

/**
* UserService接口
* Created by shuknight on 2018/3/26.
*/
public interface UserService extends BaseService<User, UserExample> {
    User authUserAndPwd(String userName, String pwd);
    boolean authUserAndPwd(User dbUser,String pwd);
}