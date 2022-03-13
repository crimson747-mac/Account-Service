package microservice.account.repository;

import microservice.account.entity.Role;
import microservice.account.entity.enums.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository ROLE_REPOSITORY;

    @Test
    @DisplayName("성공) RoleType과 일치하는 Role 가져오기")
    void findOneByRoleType() {
        //given
        RoleType type = RoleType.USER;

        //when
        Role role = ROLE_REPOSITORY.findOneByRoleType(type);

        //then
        assertThat(role.getRoleType()).isEqualTo(type);
    }
}