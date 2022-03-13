package microservice.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import microservice.account.entity.enums.RoleType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private RoleType roleType;

    public Role(RoleType roleType) {
        this.roleType = roleType;
    }
}
