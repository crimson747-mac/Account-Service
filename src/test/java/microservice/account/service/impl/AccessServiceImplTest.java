package microservice.account.service.impl;

import microservice.account.service.AccessService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccessServiceImplTest {

    @Autowired AccessService ACCESS_SERVICE;

    @BeforeEach
    void beforeEach() {
        
    }

    @Test
    @DisplayName("성공) 계정 정보로 모든 접근정보 가져오기")
    void findAllByAccount() {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("")
    void findOneByToken() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("")
    void findAllByLoginDateTimeAfter() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("")
    void findAllByLogoutDateTimeAfter() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("")
    void findAccessByLoginDateTimeBetween() {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("")
    void findAccessByLogoutDateTimeBetween() {
        //given

        //when

        //then
    }
}