package microservice.account.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.account.entity.enums.RoleType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterParam {
    private String email;
    private String password;
    private String password2;
    private RoleType roleType;
}
