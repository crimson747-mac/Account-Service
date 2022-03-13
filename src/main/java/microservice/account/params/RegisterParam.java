package microservice.account.params;

import lombok.Data;
import microservice.account.entity.enums.RoleType;

@Data
public class RegisterParam {
    private String email;
    private String password;
    private String password2;
    private RoleType roleType;
}
