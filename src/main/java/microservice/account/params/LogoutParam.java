package microservice.account.params;

import lombok.Data;

@Data
public class LogoutParam {
    private Long id;
    private String token;
}
