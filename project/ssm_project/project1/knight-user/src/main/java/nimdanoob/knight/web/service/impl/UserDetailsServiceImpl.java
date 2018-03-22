package nimdanoob.knight.web.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import nimdanoob.knight.web.domain.mapper.UserDetailsMapper;
import nimdanoob.knight.web.domain.model.UserDetails;
import nimdanoob.knight.web.domain.model.UserDetailsExample;
import nimdanoob.knight.web.service.api.UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UserDetailsService实现
* Created by shuknight on 2018/3/22.
*/
@Service
@Transactional
@BaseService
public class UserDetailsServiceImpl extends BaseServiceImpl<UserDetailsMapper, UserDetails, UserDetailsExample> implements UserDetailsService {

    private static Logger _log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserDetailsMapper userDetailsMapper;

}