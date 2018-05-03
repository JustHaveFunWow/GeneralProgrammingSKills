package com.knight.ids.service.impl;

import com.knight.common.annotation.BaseService;
import com.knight.common.base.BaseServiceImpl;
import com.knight.ids.dao.mapper.KnightIdsMapper;
import com.knight.ids.dao.model.KnightIds;
import com.knight.ids.dao.model.KnightIdsExample;
import com.knight.ids.api.KnightIdsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* KnightIdsService实现
* Created by shuknight on 2018/5/2.
*/
@Service
@Transactional
@BaseService
public class KnightIdsServiceImpl extends BaseServiceImpl<KnightIdsMapper, KnightIds, KnightIdsExample> implements KnightIdsService {

    private static Logger _log = LoggerFactory.getLogger(KnightIdsServiceImpl.class);

    @Autowired
    KnightIdsMapper knightIdsMapper;

    @Transactional
    @Override
    public int getNextId(Integer systemId,String businessName) {
        KnightIds knightIds = knightIdsMapper.selectByPrimaryKey(systemId);
        if (knightIds == null){
            knightIds = new KnightIds();
            knightIds.setIdsSystemId(systemId);
            knightIds.setIdsBusinessId(1);
            knightIds.setIdsBusinessName(businessName);
            knightIdsMapper.insert(knightIds);
            return 1;
        }
        knightIds.setIdsBusinessId(knightIds.getIdsSystemId()+1);
        knightIdsMapper.updateByPrimaryKey(knightIds);
        return knightIds.getIdsBusinessId();
    }
}