package microservice.account.controller;

import lombok.RequiredArgsConstructor;
import microservice.account.dto.AccountDto;
import microservice.account.entity.Account;
import microservice.account.params.account.LoginParam;
import microservice.account.params.account.LogoutParam;
import microservice.account.params.account.RegisterParam;
import microservice.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService ACCOUNT_SERVICE;

    @PostMapping("/register")
    public ResponseEntity<AccountDto> register(@Valid @RequestBody RegisterParam param) {
        Account account = ACCOUNT_SERVICE.register(param);
        AccountDto result = new AccountDto(account);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<AccountDto> login(@Valid @RequestBody LoginParam param) {
        Account account = ACCOUNT_SERVICE.login(param);
        AccountDto result = new AccountDto(account);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/logout")
    public ResponseEntity<AccountDto> logout(@Valid @RequestBody LogoutParam param) {
        Account account = ACCOUNT_SERVICE.logout(param);
        AccountDto result = new AccountDto(account);
        return ResponseEntity.ok().body(result);
    }
}
