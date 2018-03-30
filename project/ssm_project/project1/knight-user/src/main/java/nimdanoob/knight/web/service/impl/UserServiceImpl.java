package nimdanoob.knight.web.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import nimdanoob.knight.web.common.AuthUtils;
import nimdanoob.knight.web.domain.mapper.UserMapper;
import nimdanoob.knight.web.domain.model.User;
import nimdanoob.knight.web.domain.model.UserExample;
import nimdanoob.knight.web.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UserService实现
* Created by shuknight on 2018/3/26.
*/
@Service
@Transactional
@BaseService
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {

    private static Logger _log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    UserMapper userMapper;

    /**
     *
     * @param userName
     * @param pwd  未加密的 pwd
     * @return
     */
    @Override
    public User authUserAndPwd(String userName, String pwd) {
        User user = userMapper.selectByUserName(userName);
        if (user == null)
            return null;
        String salt = user.getSalt();
        String password = user.getPassword();
        if (password.equals(AuthUtils.encryptPassword(pwd,salt)))
            return user;
        return null;
    }
}