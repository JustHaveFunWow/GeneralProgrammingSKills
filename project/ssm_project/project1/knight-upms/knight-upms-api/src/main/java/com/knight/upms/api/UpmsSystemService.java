package com.knight.upms.api;

import com.knight.common.base.BaseService;
import com.knight.upms.dao.model.UpmsSystem;
import com.knight.upms.dao.model.UpmsSystemExample;

/**
* UpmsSystemService接口
* Created by shuknight on 2018/4/9.
*/
public interface UpmsSystemService extends BaseService<UpmsSystem, UpmsSystemExample> {
    /**
     * 根据name获取UpmsSystem
     * @param name
     * @return
     */
    UpmsSystem selectUpmsSystemByName(String name);
}