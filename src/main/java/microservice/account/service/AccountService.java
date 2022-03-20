package microservice.account.service;

import microservice.account.entity.Account;
import microservice.account.params.account.LoginParam;
import microservice.account.params.account.LogoutParam;
import microservice.account.params.account.RegisterParam;

public interface AccountService {

    Account register(RegisterParam param);

    Account login(LoginParam param);

    Account logout(LogoutParam param);

    Account getAccount(Long id);

}
