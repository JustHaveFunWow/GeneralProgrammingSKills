package com.knight.upms.api;

import com.knight.common.base.BaseService;
import com.knight.upms.dao.model.UpmsLog;
import com.knight.upms.dao.model.UpmsLogExample;

/**
* UpmsLogService接口
* Created by shuknight on 2018/4/9.
*/
public interface UpmsLogService extends BaseService<UpmsLog, UpmsLogExample> {

     String test();
}