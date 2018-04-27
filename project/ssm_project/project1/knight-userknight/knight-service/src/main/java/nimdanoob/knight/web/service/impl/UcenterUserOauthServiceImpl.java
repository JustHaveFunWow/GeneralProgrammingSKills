package nimdanoob.knight.web.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import nimdanoob.knight.web.dao.mapper.UcenterUserOauthMapper;
import nimdanoob.knight.web.dao.model.UcenterUserOauth;
import nimdanoob.knight.web.dao.model.UcenterUserOauthExample;
import nimdanoob.knight.web.api.UcenterUserOauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UcenterUserOauthService实现
* Created by shuknight on 2018/4/27.
*/
@Service
@Transactional
@BaseService
public class UcenterUserOauthServiceImpl extends BaseServiceImpl<UcenterUserOauthMapper, UcenterUserOauth, UcenterUserOauthExample> implements UcenterUserOauthService {

    private static Logger _log = LoggerFactory.getLogger(UcenterUserOauthServiceImpl.class);

    @Autowired
    UcenterUserOauthMapper ucenterUserOauthMapper;

}