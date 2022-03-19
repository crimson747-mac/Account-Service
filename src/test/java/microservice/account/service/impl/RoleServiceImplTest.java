package microservice.account.service.impl;

import microservice.account.entity.Role;
import microservice.account.entity.enums.RoleType;
import microservice.account.service.RoleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RoleServiceImplTest {

    @Autowired RoleService ROLE_SERVICE;

    @Test
    @DisplayName("성공) RoleType으로 Role 조회")
    void findOneByRoleType() {
        //given
        RoleType role = RoleType.USER;

        //when
        Role result = ROLE_SERVICE.findOneByRoleType(role);

        //then
        assertThat(result.getRoleType()).isEqualTo(role);
    }

    @Test
    @DisplayName("성공) 모든 Role 정보 조회")
    void findAll() {
        //given

        //when
        List<Role> roles = ROLE_SERVICE.findAll();

        //then
        assertThat(roles.size()).isEqualTo(3);
    }
}