package nimdanoob.knight.web.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import nimdanoob.knight.web.domain.mapper.UserLogMapper;
import nimdanoob.knight.web.domain.model.UserLog;
import nimdanoob.knight.web.domain.model.UserLogExample;
import nimdanoob.knight.web.service.api.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UserLogService实现
* Created by shuknight on 2018/3/22.
*/
@Service
@Transactional
@BaseService
public class UserLogServiceImpl extends BaseServiceImpl<UserLogMapper, UserLog, UserLogExample> implements UserLogService {

    private static Logger _log = LoggerFactory.getLogger(UserLogServiceImpl.class);

    @Autowired
    UserLogMapper userLogMapper;

}