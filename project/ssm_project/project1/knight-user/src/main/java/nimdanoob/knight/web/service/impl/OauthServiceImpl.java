package nimdanoob.knight.web.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import nimdanoob.knight.web.domain.mapper.OauthMapper;
import nimdanoob.knight.web.domain.model.Oauth;
import nimdanoob.knight.web.domain.model.OauthExample;
import nimdanoob.knight.web.service.api.OauthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* OauthService实现
* Created by shuknight on 2018/3/26.
*/
@Service
@Transactional
@BaseService
public class OauthServiceImpl extends BaseServiceImpl<OauthMapper, Oauth, OauthExample> implements OauthService {

    private static Logger _log = LoggerFactory.getLogger(OauthServiceImpl.class);

    @Autowired
    OauthMapper oauthMapper;

}