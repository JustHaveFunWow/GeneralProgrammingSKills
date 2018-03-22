package nimdanoob.knight.web.rpc.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import nimdanoob.knight.web.dao.mapper.UcenterUserLogMapper;
import nimdanoob.knight.web.dao.model.UcenterUserLog;
import nimdanoob.knight.web.dao.model.UcenterUserLogExample;
import nimdanoob.knight.web.rpc.api.UcenterUserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UcenterUserLogService实现
* Created by shuknight on 2018/3/22.
*/
@Service
@Transactional
@BaseService
public class UcenterUserLogServiceImpl extends BaseServiceImpl<UcenterUserLogMapper, UcenterUserLog, UcenterUserLogExample> implements UcenterUserLogService {

    private static Logger _log = LoggerFactory.getLogger(UcenterUserLogServiceImpl.class);

    @Autowired
    UcenterUserLogMapper ucenterUserLogMapper;

}