package microservice.account.params.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam {

    @Email
    private String email;

    @NotBlank
    private String password;

    private Double latitude;

    private Double longitude;
}
