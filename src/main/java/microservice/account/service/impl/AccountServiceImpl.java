package microservice.account.service.impl;

import lombok.RequiredArgsConstructor;
import microservice.account.entity.Access;
import microservice.account.entity.Account;
import microservice.account.entity.Role;
import microservice.account.params.LoginParam;
import microservice.account.params.LogoutParam;
import microservice.account.params.RegisterParam;
import microservice.account.repository.AccessRepository;
import microservice.account.repository.AccountRepository;
import microservice.account.repository.RoleRepository;
import microservice.account.service.AccountService;
import microservice.account.validate.account.AccountValidate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository ACCOUNT_REPOSITORY;
    private final RoleRepository ROLE_REPOSITORY;
    private final AccessRepository ACCESS_REPOSITORY;
    private final AccountValidate validate;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Account register(RegisterParam param) {
        validate.checkRegister(param);
        String encryptPassword = passwordEncoder.encode(param.getPassword());
        Role role = ROLE_REPOSITORY.findOneByRoleType(param.getRoleType());
        Account account = createAccountAndSetRole(param.getEmail(), encryptPassword, role);
        return ACCOUNT_REPOSITORY.save(account);
    }

    private Account createAccountAndSetRole(String email, String encryptPassword, Role role) {
        Account account = new Account(email, encryptPassword);
        account.setRole(role);
        return account;
    }

    @Override
    public Account login(LoginParam param) {
        Account account = validate.checkLogin(param);
        account.setIsLogin(true);
        createAndSaveAccess(param, account);
        return account;
    }

    @Override
    public Account logout(LogoutParam param) {
        Account account = validate.checkLogout(param);
        account.setIsLogin(false);

        Access access = ACCESS_REPOSITORY.findOneByToken(param.getToken()).orElseThrow();
        access.setLogoutDateTime(LocalDateTime.now());

        return account;
    }

    private void createAndSaveAccess(LoginParam param, Account account) {
        Access access = new Access(
            account,
            LocalDateTime.now(),
            UUID.randomUUID().toString(),
            param.getLatitude(),
            param.getLongitude()
        );
        ACCESS_REPOSITORY.save(access);
    }

    @Override
    public Account getAccount(Long id) {
        return ACCOUNT_REPOSITORY.findOneById(id).orElseThrow();
    }

}
