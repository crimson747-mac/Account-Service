package microservice.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String email;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    private boolean isLogin;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
