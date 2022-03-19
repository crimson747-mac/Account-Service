package microservice.account.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam {
    private String email;
    private String password;
    private Double latitude;
    private Double longitude;
}
