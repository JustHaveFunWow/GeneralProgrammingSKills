package nimdanoob.knight.web.service.api;

import com.knight.common.base.BaseServiceMock;
import nimdanoob.knight.web.domain.mapper.UserLogMapper;
import nimdanoob.knight.web.domain.model.UserLog;
import nimdanoob.knight.web.domain.model.UserLogExample;

/**
* 降级实现UserLogService接口
* Created by shuknight on 2018/3/22.
*/
public class UserLogServiceMock extends BaseServiceMock<UserLogMapper, UserLog, UserLogExample> implements UserLogService {

}
