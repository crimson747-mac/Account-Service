package microservice.account.repository;

import microservice.account.entity.Access;
import microservice.account.entity.Account;
import microservice.account.entity.Role;
import microservice.account.entity.enums.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class AccessRepositoryTest {

    @Autowired RoleRepository ROLE_REPOSITORY;
    @Autowired AccessRepository ACCESS_REPOSITORY;
    @Autowired AccountRepository ACCOUNT_REPOSITORY;
    Account account;
    Access access;

    @BeforeEach
    void beforeEach() {
        //create Account
        Role roleUser = ROLE_REPOSITORY.findOneByRoleType(RoleType.USER);
        account = new Account("admin@admin.com", "1234");
        account.setRole(roleUser);

        ACCOUNT_REPOSITORY.save(account);

        //create Access
        access = new Access(
                account,
                LocalDateTime.now(),
                UUID.randomUUID().toString(),
                37.5,
                126.34);
        ACCESS_REPOSITORY.save(access);
    }

    @AfterEach
    void afterEach() {
        ACCESS_REPOSITORY.deleteAll();
        ACCOUNT_REPOSITORY.deleteAll();
    }

    @Test
    void findAllByAccount() {
        //given
        Account account = this.account;

        //when
        List<Access> accessList = ACCESS_REPOSITORY.findAllByAccount(account);

        //then
        assertThat(accessList.size()).isEqualTo(1);
    }

    @Test
    void findOneByToken() {
        //given
        String token = access.getToken();

        //when
        Access access = ACCESS_REPOSITORY.findOneByToken(token).orElseThrow();

        //then
        assertThat(access.getToken()).isEqualTo(token);
    }

    @Test
    void findAllByLoginDateTimeAfter() {
        //given
        LocalDateTime pastDateTime = LocalDateTime.of(2022, 3, 1, 20, 30, 45);

        //when
        List<Access> accessList = ACCESS_REPOSITORY.findAllByLoginDateTimeAfter(pastDateTime);

        //then
        assertThat(accessList.size()).isEqualTo(1);
    }

    @Test
    void findAllByLogoutDateTimeAfter() {
        //given
        LocalDateTime pastDateTime = LocalDateTime.of(2022, 3, 1, 20, 30, 45);
        access.setLogoutDateTime(LocalDateTime.now());

        //when
        List<Access> accessList = ACCESS_REPOSITORY.findAllByLogoutDateTimeAfter(pastDateTime);

        //then
        assertThat(accessList.size()).isEqualTo(1);
    }

    @Test
    void findAccessByLoginDateTimeBetween() {
        //given
        LocalDateTime pastDateTime = LocalDateTime.of(2022, 3, 1, 20, 30, 45);
        LocalDateTime now = LocalDateTime.now();

        //when
        List<Access> accessList = ACCESS_REPOSITORY.findAccessByLoginDateTimeBetween(pastDateTime, now);

        //then
        assertThat(accessList.size()).isEqualTo(1);
    }

    @Test
    void findAccessByLogoutDateTimeBetween() {
        //given
        LocalDateTime pastDateTime = LocalDateTime.of(2022, 3, 1, 20, 30, 45);
        access.setLogoutDateTime(LocalDateTime.of(2022, 3, 3, 20, 30, 45));

        //when
        List<Access> accessList = ACCESS_REPOSITORY.findAccessByLogoutDateTimeBetween(pastDateTime, LocalDateTime.now());

        //then
        assertThat(accessList.size()).isEqualTo(1);
    }
}