package microservice.account.dto;

import lombok.Data;
import microservice.account.entity.Account;

@Data
public class AccountDto {

    private Long id;
    private String email;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
    }
}
