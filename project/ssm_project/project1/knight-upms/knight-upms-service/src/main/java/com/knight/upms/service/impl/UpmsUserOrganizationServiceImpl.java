package com.knight.upms.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import com.knight.upms.dao.mapper.UpmsUserOrganizationMapper;
import com.knight.upms.dao.model.UpmsUserOrganization;
import com.knight.upms.dao.model.UpmsUserOrganizationExample;
import com.knight.upms.api.UpmsUserOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsUserOrganizationService实现
* Created by shuknight on 2018/4/9.
*/
@Service
@Transactional
@BaseService
public class UpmsUserOrganizationServiceImpl extends BaseServiceImpl<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserOrganizationServiceImpl.class);

    @Autowired
    UpmsUserOrganizationMapper upmsUserOrganizationMapper;

}