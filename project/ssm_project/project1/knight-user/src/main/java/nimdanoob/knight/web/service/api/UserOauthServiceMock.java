package nimdanoob.knight.web.service.api;

import com.knight.common.base.BaseServiceMock;
import nimdanoob.knight.web.domain.mapper.UserOauthMapper;
import nimdanoob.knight.web.domain.model.UserOauth;
import nimdanoob.knight.web.domain.model.UserOauthExample;

/**
* 降级实现UserOauthService接口
* Created by shuknight on 2018/3/22.
*/
public class UserOauthServiceMock extends BaseServiceMock<UserOauthMapper, UserOauth, UserOauthExample> implements UserOauthService {

}
