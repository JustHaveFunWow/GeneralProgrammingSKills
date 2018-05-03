package com.knight.ids.api;

import com.knight.common.base.BaseService;
import com.knight.ids.dao.model.KnightIds;
import com.knight.ids.dao.model.KnightIdsExample;

/**
* KnightIdsService接口
* Created by shuknight on 2018/5/2.
*/
public interface KnightIdsService extends BaseService<KnightIds, KnightIdsExample> {
    int getNextId(Integer systemId,String businessName);

}