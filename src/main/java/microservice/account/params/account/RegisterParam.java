package microservice.account.params.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.account.entity.enums.RoleType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterParam {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String password2;

    @NotNull
    private RoleType roleType;
}
