package microservice.account.validate.account;

import lombok.RequiredArgsConstructor;
import microservice.account.entity.Account;
import microservice.account.params.account.LoginParam;
import microservice.account.params.account.LogoutParam;
import microservice.account.params.account.RegisterParam;
import microservice.account.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountValidate {

    private final AccountRepository ACCOUNT_REPOSITORY;
    private final PasswordEncoder passwordEncoder;

    public void checkRegister(RegisterParam param) {
        isPasswordMatch(param);
        isUniqueEmail(param);
    }

    private void isPasswordMatch(RegisterParam param) {
        if(!param.getPassword().equals(param.getPassword2()))
            throw new IllegalArgumentException("input password is not matched!");
    }

    private void isUniqueEmail(RegisterParam param) {
        if(ACCOUNT_REPOSITORY.findOneByEmail(param.getEmail()).isPresent())
            throw new IllegalArgumentException("already exist email");
    }

    public Account checkLogin(LoginParam param) {
        Account account = isCoincidePassword(param);
        isLoggedIn(account, param);
        return account;
    }

    private Account isCoincidePassword(LoginParam param) {
        return ACCOUNT_REPOSITORY.findOneByEmail(param.getEmail()).orElseThrow();
    }

    private void isLoggedIn(Account account, LoginParam param) {
        boolean isMatch = passwordEncoder.matches(param.getPassword(), account.getPassword());
        if(!isMatch)
            throw new IllegalArgumentException("please check your input information");
    }

    public Account checkLogout(LogoutParam param) {
        Account account = ACCOUNT_REPOSITORY.findOneById(param.getId()).orElseThrow();
        if(!account.isLogin())
            throw new IllegalArgumentException("user is not logged in");

        return account;
    }

}
