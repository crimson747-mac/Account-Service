package microservice.account.params;

import lombok.Data;

@Data
public class LoginParam {
    private String email;
    private String password;
    private Double latitude;
    private Double longitude;
}
