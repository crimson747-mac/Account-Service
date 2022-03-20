package microservice.account.dto;

import lombok.Data;
import microservice.account.entity.Account;

@Data
public class AccountDto {

    private Long id;
    private String email;
    private String token;
    private boolean isLogin;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.token = account.getToken();
        this.isLogin = account.isLogin();
    }
}
