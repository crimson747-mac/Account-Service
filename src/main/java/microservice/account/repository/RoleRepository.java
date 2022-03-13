package microservice.account.repository;

import microservice.account.entity.Role;
import microservice.account.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneByRoleType(RoleType type);

}
