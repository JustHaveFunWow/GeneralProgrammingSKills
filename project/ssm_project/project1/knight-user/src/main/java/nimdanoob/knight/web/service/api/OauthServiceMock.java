package nimdanoob.knight.web.service.api;

import com.knight.common.base.BaseServiceMock;
import nimdanoob.knight.web.domain.mapper.OauthMapper;
import nimdanoob.knight.web.domain.model.Oauth;
import nimdanoob.knight.web.domain.model.OauthExample;

/**
* 降级实现OauthService接口
* Created by shuknight on 2018/3/22.
*/
public class OauthServiceMock extends BaseServiceMock<OauthMapper, Oauth, OauthExample> implements OauthService {

}
