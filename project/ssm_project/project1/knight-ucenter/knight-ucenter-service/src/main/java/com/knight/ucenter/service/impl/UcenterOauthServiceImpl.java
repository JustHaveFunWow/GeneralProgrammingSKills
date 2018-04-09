package com.knight.ucenter.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import com.knight.ucenter.api.UcenterOauthService;
import com.knight.ucenter.dao.mapper.UcenterOauthMapper;
import com.knight.ucenter.dao.model.UcenterOauth;
import com.knight.ucenter.dao.model.UcenterOauthExample;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UcenterOauthService实现
* Created by shuknight on 2018/4/9.
*/

@Service
@Transactional
@BaseService
@Log4j2
public class UcenterOauthServiceImpl extends BaseServiceImpl<UcenterOauthMapper, UcenterOauth, UcenterOauthExample> implements UcenterOauthService {

    @Autowired
    UcenterOauthMapper ucenterOauthMapper;
}