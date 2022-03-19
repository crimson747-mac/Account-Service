package microservice.account.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.account.entity.Access;
import microservice.account.entity.Account;
import microservice.account.repository.AccessRepository;
import microservice.account.repository.AccountRepository;
import microservice.account.service.AccessService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccessServiceImpl implements AccessService {

    private final AccountRepository accountRepository;
    private final AccessRepository accessRepository;

    @Override
    public List<Access> findAllByAccount(Long accountId) {
        log.info("[SERVICE]_[findAllByAccount: accountId = {}]", accountId);
        Account account = accountRepository.findOneById(accountId).orElseThrow();
        return accessRepository.findAllByAccount(account);
    }

    @Override
    public Access findOneByToken(String token) {
        log.info("[SERVICE]_[findOneByToken: token = {}]", token);
        return accessRepository.findOneByToken(token).orElseThrow();
    }

    @Override
    public List<Access> findAllByLoginDateTimeAfter(LocalDateTime loginDateTime) {
        log.info("[SERVICE]_[findAllByLoginDateTimeAfter: loginDateTime = {}]", loginDateTime);
        return accessRepository.findAllByLoginDateTimeAfter(loginDateTime);
    }

    @Override
    public List<Access> findAllByLogoutDateTimeAfter(LocalDateTime logoutDateTime) {
        log.info("[SERVICE]_[findAllByLogoutDateTimeAfter: logoutDateTime = {}]", logoutDateTime);
        return accessRepository.findAllByLogoutDateTimeAfter(logoutDateTime);
    }

    @Override
    public List<Access> findAccessByLoginDateTimeBetween(LocalDateTime startLoginDateTime, LocalDateTime endLoginDateTime) {
        log.info("[SERVICE]_[findAccessByLoginDateTimeBetween: startLoginDateTime = {}, endLoginDateTime = {}]",
                startLoginDateTime, endLoginDateTime);
        return accessRepository.findAccessByLoginDateTimeBetween(startLoginDateTime, endLoginDateTime);
    }

    @Override
    public List<Access> findAccessByLogoutDateTimeBetween(LocalDateTime startLogoutDateTime, LocalDateTime endLogoutDateTime) {
        log.info("[SERVICE]_[findAccessByLogoutDateTimeBetween: startLogoutDateTime = {}, endLogoutDateTime = {}]",
                startLogoutDateTime, endLogoutDateTime);
        return accessRepository.findAccessByLoginDateTimeBetween(startLogoutDateTime, endLogoutDateTime);
    }
}
