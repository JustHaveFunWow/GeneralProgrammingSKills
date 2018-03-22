package nimdanoob.knight.web.rpc.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import nimdanoob.knight.web.dao.mapper.UcenterOauthMapper;
import nimdanoob.knight.web.dao.model.UcenterOauth;
import nimdanoob.knight.web.dao.model.UcenterOauthExample;
import nimdanoob.knight.web.rpc.api.UcenterOauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UcenterOauthService实现
* Created by shuknight on 2018/3/22.
*/
@Service
@Transactional
@BaseService
public class UcenterOauthServiceImpl extends BaseServiceImpl<UcenterOauthMapper, UcenterOauth, UcenterOauthExample> implements UcenterOauthService {

    private static Logger _log = LoggerFactory.getLogger(UcenterOauthServiceImpl.class);

    @Autowired
    UcenterOauthMapper ucenterOauthMapper;

}