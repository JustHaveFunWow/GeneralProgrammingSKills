package nimdanoob.knight.web.service.api;

import com.knight.common.result.BaseServerResponse;
import nimdanoob.knight.web.domain.User;

public interface UserAccountService {

    public BaseServerResponse register(User user);

    public BaseServerResponse login(User user);

    public BaseServerResponse preRegister(User user);

}
