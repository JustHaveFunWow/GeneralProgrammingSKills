package nimdanoob.knight.web.service.api;

import com.knight.common.base.BaseServiceMock;
import nimdanoob.knight.web.domain.mapper.UserDetailsMapper;
import nimdanoob.knight.web.domain.model.UserDetails;
import nimdanoob.knight.web.domain.model.UserDetailsExample;

/**
* 降级实现UserDetailsService接口
* Created by shuknight on 2018/3/26.
*/
public class UserDetailsServiceMock extends BaseServiceMock<UserDetailsMapper, UserDetails, UserDetailsExample> implements UserDetailsService {

}
