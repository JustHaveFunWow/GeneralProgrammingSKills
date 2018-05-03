package com.knight.ids.client;

import com.knight.common.util.SpringContextUtil;
import com.knight.ids.api.KnightIdsService;
import com.knight.ids.dao.mapper.KnightIdsMapper;
import com.knight.ids.dao.model.KnightIds;
import org.mybatis.generator.config.xml.MyBatisGeneratorConfigurationParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class IdsClient {
    //性能肯定很差，有时间改成 id池

    public static int getNextId(Integer systemId,String businessName){

        KnightIdsMapper knightIdsMapper = SpringContextUtil.getBean(KnightIdsMapper.class);
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
