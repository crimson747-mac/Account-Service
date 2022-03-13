package microservice.account.repository;

import microservice.account.entity.Access;
import microservice.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccessRepository extends JpaRepository<Access, Long> {

    List<Access> findAllByAccount(Account account);

    Optional<Access> findOneByToken(String token);

    List<Access> findAllByLoginDateTimeAfter(LocalDateTime loginDateTime);

    List<Access> findAllByLogoutDateTimeAfter(LocalDateTime logoutDateTime);

    List<Access> findAccessByLoginDateTimeBetween(LocalDateTime startLoginDateTime, LocalDateTime endLoginDateTime);

    List<Access> findAccessByLogoutDateTimeBetween(LocalDateTime startLogoutDateTime, LocalDateTime endLogoutDateTime);

}
