package nimdanoob.knight.web.rpc.api;

import com.knight.common.base.BaseServiceMock;
import nimdanoob.knight.web.dao.mapper.UcenterUserMapper;
import nimdanoob.knight.web.dao.model.UcenterUser;
import nimdanoob.knight.web.dao.model.UcenterUserExample;

/**
* 降级实现UcenterUserService接口
* Created by shuknight on 2018/3/22.
*/
public class UcenterUserServiceMock extends BaseServiceMock<UcenterUserMapper, UcenterUser, UcenterUserExample> implements UcenterUserService {

}
