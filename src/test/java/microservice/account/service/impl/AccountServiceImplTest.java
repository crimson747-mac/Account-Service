package microservice.account.service.impl;

import microservice.account.entity.Account;
import microservice.account.entity.enums.RoleType;
import microservice.account.params.account.LoginParam;
import microservice.account.params.account.LogoutParam;
import microservice.account.params.account.RegisterParam;
import microservice.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceImplTest {

    @Autowired AccountService ACCOUNT_SERVICE;
    Account account;

    @BeforeEach
    void beforeEach() {
        account = ACCOUNT_SERVICE.register(new RegisterParam(
                "zenon8485@fsn.co.kr",
                "12345",
                "12345",
                RoleType.USER));
    }

    @Test
    @DisplayName("성공) 사용자 등록")
    void register() {
        //given
        String email = "zeros8485@fsn.co.kr";
        String password = "12345";
        String password2 = "12345";
        RoleType role = RoleType.USER;

        RegisterParam param = new RegisterParam(email, password, password2, role);

        //when
        Account result = ACCOUNT_SERVICE.register(param);

        //then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("성공) 사용자 로그인")
    void login() {
        //given
        String email = "zenon8485@fsn.co.kr";
        String password = "12345";
        Double latitude = 37.5;
        Double longitude = 126.5;
        LoginParam param = new LoginParam(email, password, latitude, longitude);

        //when
        Account result = ACCOUNT_SERVICE.login(param);

        //then
        assertThat(result.isLogin()).isTrue();
    }

    @Test
    @DisplayName("성공) 사용자 로그아웃")
    void logout() {
        //given
        Account loginAccount = ACCOUNT_SERVICE.login(new LoginParam(
                "zenon8485@fsn.co.kr",
                "12345",
                37.5,
                126.5)
        );

        //when
        LogoutParam param = new LogoutParam(loginAccount.getId(), loginAccount.getToken());
        Account result = ACCOUNT_SERVICE.logout(param);


        //then
        assertThat(result.isLogin()).isFalse();
        assertThat(result.getToken()).isNull();
    }

    @Test
    @DisplayName("성공) 사용자정보 가져오기")
    void getAccount() {
        //given
        Long id = account.getId();

        //when
        Account result = ACCOUNT_SERVICE.getAccount(id);

        //then
        assertThat(result).isEqualTo(account);
    }
}