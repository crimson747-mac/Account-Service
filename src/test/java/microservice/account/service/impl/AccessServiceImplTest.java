package microservice.account.service.impl;

import microservice.account.entity.Access;
import microservice.account.entity.Account;
import microservice.account.entity.enums.RoleType;
import microservice.account.params.LoginParam;
import microservice.account.params.LogoutParam;
import microservice.account.params.RegisterParam;
import microservice.account.service.AccessService;
import microservice.account.service.AccountService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccessServiceImplTest {

    @Autowired AccountService ACCOUNT_SERVICE;
    @Autowired AccessService ACCESS_SERVICE;
    Account account;
    String token;

    @BeforeEach
    void beforeEach() throws InterruptedException {
        String email = "zenon8485@fsn.co.kr";
        String password = "12345";
        String password2 = "12345";
        account = ACCOUNT_SERVICE.register(
            new RegisterParam(email, password, password2, RoleType.USER));

        account = ACCOUNT_SERVICE.login(
                new LoginParam(email, password, 37.5, 126.3));

        token = account.getToken();

        Thread.sleep(100);
        ACCOUNT_SERVICE.logout(
                new LogoutParam(this.account.getId(), this.account.getToken()));
    }

    @Test
    @DisplayName("성공) 계정 정보로 모든 접근정보 가져오기")
    void findAllByAccount() {
        //given
        Account willAccessAccount = account;

        //when
        List<Access> result = ACCESS_SERVICE.findAllByAccount(willAccessAccount.getId());

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("성공) 토큰으로 접근 정보 가져오기")
    void findOneByToken() {
        //given
        String willUseToken = token;

        //when
        Access result = ACCESS_SERVICE.findOneByToken(willUseToken);

        //then
        assertThat(result.getToken()).isEqualTo(willUseToken);
    }

    @Test
    @DisplayName("성공) 로그인 날짜를 기준으로 접근정보 가져오기")
    void findAllByLoginDateTimeAfter() {
        //given
        LocalDateTime param = LocalDateTime.of(2022, 3, 5, 23, 30);

        //when
        List<Access> result = ACCESS_SERVICE.findAllByLoginDateTimeAfter(param);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("성공) 로그아웃 날짜를 기준으로 접근정보 가져오기")
    void findAllByLogoutDateTimeAfter() {
        //given
        LocalDateTime param = LocalDateTime.of(2022, 3, 5, 23, 30);

        //when
        List<Access> result = ACCESS_SERVICE.findAllByLogoutDateTimeAfter(param);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("성공) 로그인 날짜의 범위를 기준으로 접근정보 가져오기")
    void findAccessByLoginDateTimeBetween() {
        //given
        LocalDateTime startParam = LocalDateTime.of(2022, 3, 5, 23, 30);
        LocalDateTime endParam = LocalDateTime.of(2100, 3, 5, 23, 30);

        //when
        List<Access> result = ACCESS_SERVICE.findAccessByLoginDateTimeBetween(startParam, endParam);

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("성공) 로그아웃 날짜의 범위를 기준으로 접근 정보 가져오기")
    void findAccessByLogoutDateTimeBetween() {
        //given
        LocalDateTime startParam = LocalDateTime.of(2022, 3, 5, 23, 30);
        LocalDateTime endParam = LocalDateTime.of(2100, 3, 5, 23, 30);

        //when
        List<Access> result = ACCESS_SERVICE.findAccessByLogoutDateTimeBetween(startParam, endParam);

        //then
        assertThat(result.size()).isEqualTo(1);
    }
}