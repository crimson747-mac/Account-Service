package microservice.account.service;

import microservice.account.entity.Access;

import java.time.LocalDateTime;
import java.util.List;

public interface AccessService {

    List<Access> findAllByAccount(Long accountId);

    Access findOneByToken(String token);

    List<Access> findAllByLoginDateTimeAfter(LocalDateTime loginDateTime);

    List<Access> findAllByLogoutDateTimeAfter(LocalDateTime logoutDateTime);

    List<Access> findAccessByLoginDateTimeBetween(LocalDateTime startLoginDateTime, LocalDateTime endLoginDateTime);

    List<Access> findAccessByLogoutDateTimeBetween(LocalDateTime startLogoutDateTime, LocalDateTime endLogoutDateTime);

}
