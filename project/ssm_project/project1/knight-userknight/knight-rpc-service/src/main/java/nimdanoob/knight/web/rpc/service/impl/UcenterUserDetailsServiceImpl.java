package nimdanoob.knight.web.rpc.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import nimdanoob.knight.web.dao.mapper.UcenterUserDetailsMapper;
import nimdanoob.knight.web.dao.model.UcenterUserDetails;
import nimdanoob.knight.web.dao.model.UcenterUserDetailsExample;
import nimdanoob.knight.web.rpc.api.UcenterUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UcenterUserDetailsService实现
* Created by shuknight on 2018/3/22.
*/
@Service
@Transactional
@BaseService
public class UcenterUserDetailsServiceImpl extends BaseServiceImpl<UcenterUserDetailsMapper, UcenterUserDetails, UcenterUserDetailsExample> implements UcenterUserDetailsService {

    private static Logger _log = LoggerFactory.getLogger(UcenterUserDetailsServiceImpl.class);

    @Autowired
    UcenterUserDetailsMapper ucenterUserDetailsMapper;

}