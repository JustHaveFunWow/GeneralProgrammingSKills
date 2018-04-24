package com.knight.upms.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import com.knight.upms.dao.mapper.UpmsLogMapper;
import com.knight.upms.dao.model.UpmsLog;
import com.knight.upms.dao.model.UpmsLogExample;
import com.knight.upms.api.UpmsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsLogService实现
* Created by shuknight on 2018/4/9.
*/
//@Component
//@Transactional
@org.springframework.stereotype.Service("upmsLogService")
@BaseService
public class UpmsLogServiceImpl extends BaseServiceImpl<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

    private static Logger _log = LoggerFactory.getLogger(UpmsLogServiceImpl.class);

    @Autowired
    UpmsLogMapper upmsLogMapper;

    @Override
    public String test() {
        _log.error("远程调用测试成功");
        return null;
    }
}