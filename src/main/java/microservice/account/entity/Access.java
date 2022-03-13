package microservice.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Access {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private LocalDateTime loginDateTime;

    private LocalDateTime logoutDateTime;

    private String token;

    private Double latitude;

    private Double longitude;

    public Access(Account account, LocalDateTime loginDateTime, String token, Double latitude, Double longitude) {
        this.account = account;
        this.loginDateTime = loginDateTime;
        this.token = token;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLogoutDateTime(LocalDateTime logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }
}
