package nimdanoob.knight.web.rpc.api;

import com.knight.common.base.BaseServiceMock;
import nimdanoob.knight.web.dao.mapper.UcenterUserDetailsMapper;
import nimdanoob.knight.web.dao.model.UcenterUserDetails;
import nimdanoob.knight.web.dao.model.UcenterUserDetailsExample;

/**
* 降级实现UcenterUserDetailsService接口
* Created by shuknight on 2018/3/22.
*/
public class UcenterUserDetailsServiceMock extends BaseServiceMock<UcenterUserDetailsMapper, UcenterUserDetails, UcenterUserDetailsExample> implements UcenterUserDetailsService {

}
