package nimdanoob.knight.web.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import nimdanoob.knight.web.dao.mapper.UserOauthMapper;
import nimdanoob.knight.web.dao.model.UserOauth;
import nimdanoob.knight.web.dao.model.UserOauthExample;
import nimdanoob.knight.web.service.api.UserOauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UserOauthService实现
* Created by shuknight on 2018/3/26.
*/
@Service
@Transactional
@BaseService
public class UserOauthServiceImpl extends BaseServiceImpl<UserOauthMapper, UserOauth, UserOauthExample> implements UserOauthService {

    private static Logger _log = LoggerFactory.getLogger(UserOauthServiceImpl.class);

    @Autowired
    UserOauthMapper userOauthMapper;

}