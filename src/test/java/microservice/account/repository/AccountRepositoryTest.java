package microservice.account.repository;

import microservice.account.entity.Account;
import microservice.account.entity.Role;
import microservice.account.entity.enums.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class AccountRepositoryTest {

    @Autowired AccountRepository ACCOUNT_REPOSITORY;
    @Autowired RoleRepository ROLE_REPOSITORY;
    Account account;

    @BeforeEach
    void beforeEach() {
        Role roleUser = ROLE_REPOSITORY.findOneByRoleType(RoleType.USER).orElseThrow();
        account = new Account("admin@admin.com", "1234");
        account.setRole(roleUser);

        ACCOUNT_REPOSITORY.save(account);
    }

    @AfterEach
    void afterEach() {
        ACCOUNT_REPOSITORY.deleteAll();
    }

    @Test
    @DisplayName("성공) 사용자의 ID로 사용자 정보 가져오기")
    void findOneById() {
        //given
        Long id = account.getId();

        //when
        Account account = ACCOUNT_REPOSITORY.findOneById(id).orElseThrow();

        //then
        assertThat(account.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("성공) 사용자의 EMAIL로 사용자 정보 가져오기")
    void findOneByEmail() {
        //given
        String email = account.getEmail();

        //when
        Account account = ACCOUNT_REPOSITORY.findOneByEmail(email).orElseThrow();

        //then
        assertThat(account.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("성공) 사용자의 ROLE_TYPE으로 사용자 정보 가져오기")
    void findAllByRole() {
        //given
        Role role = account.getRole();

        //when
        List<Account> accounts = ACCOUNT_REPOSITORY.findAllByRole(role);

        //then
        assertThat(accounts.contains(account)).isTrue();
    }
}