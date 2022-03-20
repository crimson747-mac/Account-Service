package microservice.account.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        log.info("[SERVICE]_[register: email = {}, role={}]", param.getEmail(), param.getRoleType());

        validate.checkRegister(param);
        String encryptPassword = passwordEncoder.encode(param.getPassword());
        Role role = ROLE_REPOSITORY.findOneByRoleType(param.getRoleType()).orElseThrow();
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
        log.info("[SERVICE]_[login: email = {}, latitude={}, longitude={}]",
                param.getEmail(), param.getLatitude(), param.getLongitude());

        Account account = validate.checkLogin(param);
        Access access = createAndSaveAccess(param, account);
        account.setIsLogin(true);
        account.setToken(access.getToken());

        return account;
    }

    @Override
    public Account logout(LogoutParam param) {
        log.info("[SERVICE]_[logout: id = {}, token={}]", param.getId(), param.getToken());

        Access access = ACCESS_REPOSITORY.findOneByToken(param.getToken()).orElseThrow();
        access.setLogoutDateTime(LocalDateTime.now());

        Account account = validate.checkLogout(param);
        account.setIsLogin(false);
        account.setToken(null);

        return account;
    }

    private Access createAndSaveAccess(LoginParam param, Account account) {
        return ACCESS_REPOSITORY.save(new Access(
                account,
                LocalDateTime.now(),
                UUID.randomUUID().toString(),
                param.getLatitude(),
                param.getLongitude()
        ));
    }

    @Override
    public Account getAccount(Long id) {
        log.info("[SERVICE]_[getAccount: id = {}]", id);

        return ACCOUNT_REPOSITORY.findOneById(id).orElseThrow();
    }

}
