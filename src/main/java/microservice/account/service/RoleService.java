package microservice.account.service;

import microservice.account.entity.Role;
import microservice.account.entity.enums.RoleType;

import java.util.List;

public interface RoleService {

    Role findOneByRoleType(RoleType type);

    List<Role> findAll();

}
