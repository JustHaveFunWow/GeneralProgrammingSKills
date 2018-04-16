package com.knight.upms.service.impl;

import com.knight.upms.api.UpmsApiService;
import com.knight.upms.dao.mapper.UpmsUserMapper;
import com.knight.upms.dao.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UpmsApiServiceImpl implements UpmsApiService{

    @Autowired
    UpmsUserMapper upmsUserMapper;

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        return null;
    }

    @Override
    public List<UpmsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        return null;
    }

    @Override
    public List<UpmsSystem> selectUpmsSystemByExample(UpmsSystemExample upmsSystemExample) {
        return null;
    }

    @Override
    public List<UpmsOrganization> selectUpmsOrganizationByExample(UpmsOrganizationExample upmsOrganizationExample) {
        return null;
    }

    @Override
    public UpmsUser selectUpmsUserByUsername(String username) {
        UpmsUserExample upmsUserExample = new UpmsUserExample();
        upmsUserExample.createCriteria()
                .andUsernameEqualTo(username);
        List<UpmsUser> upmsUsers = upmsUserMapper.selectByExample(upmsUserExample);
        if (null != upmsUsers && upmsUsers.size() > 0) {
            return upmsUsers.get(0);
        }
        return null;
    }

    @Override
    public int insertUpmsLogSelective(UpmsLog record) {
        return 0;
    }
}
