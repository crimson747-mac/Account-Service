package microservice.account.service;

import microservice.account.dto.AccountDto;
import microservice.account.entity.Account;
import microservice.account.params.LoginParam;
import microservice.account.params.LogoutParam;
import microservice.account.params.RegisterParam;

public interface AccountService {

    Account register(RegisterParam param);

    Account login(LoginParam param);

    Account logout(LogoutParam param);

    Account getAccount(Long id);

}
