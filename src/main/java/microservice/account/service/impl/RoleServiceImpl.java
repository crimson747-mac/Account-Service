package microservice.account.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.account.entity.Role;
import microservice.account.entity.enums.RoleType;
import microservice.account.repository.RoleRepository;
import microservice.account.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    public final RoleRepository roleRepository;

    @Override
    public Role findOneByRoleType(RoleType type) {
        log.info("[SERVICE]_[findOneByRoleType: type = {}]", type);
        return roleRepository.findOneByRoleType(type).orElseThrow();
    }

    @Override
    public List<Role> findAll() {
        log.info("[SERVICE]_[findAll]");
        return roleRepository.findAll();
    }
}
