package nimdanoob.knight.web.service.api;

import com.knight.common.base.BaseServiceMock;
import nimdanoob.knight.web.domain.mapper.UserMapper;
import nimdanoob.knight.web.domain.model.User;
import nimdanoob.knight.web.domain.model.UserExample;

/**
* 降级实现UserService接口
* Created by shuknight on 2018/3/22.
*/
public class UserServiceMock extends BaseServiceMock<UserMapper, User, UserExample> implements UserService {

}
