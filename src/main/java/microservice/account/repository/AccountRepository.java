package microservice.account.repository;

import microservice.account.entity.Account;
import microservice.account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {



    Optional<Account> findOneById(Long id);

    Optional<Account> findOneByEmail(String email);

    List<Account> findAllByRole(Role role);

}
