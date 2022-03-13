package microservice.account.repository;

import microservice.account.entity.Access;
import microservice.account.entity.Account;
import microservice.account.entity.Role;
import microservice.account.entity.enums.RoleType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

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
        Access access = new Access(
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

        //when

        //then
    }

    @Test
    void findOneByToken() {
        //given

        //when

        //then
    }

    @Test
    void findAllByLoginDateTimeAfter() {
        //given

        //when

        //then
    }

    @Test
    void findAllByLogoutDateTimeAfter() {
        //given

        //when

        //then
    }

    @Test
    void findAccessByLoginDateTimeBetween() {
        //given

        //when

        //then
    }

    @Test
    void findAccessByLogoutDateTimeBetween() {
        //given

        //when

        //then
    }
}