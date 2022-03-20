package microservice.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.account.entity.Access;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessDto {

    private Long id;
    private Long accountId;
    private LocalDateTime loginDateTime;
    private LocalDateTime logoutDateTime;

    public AccessDto(Access access) {
        this.id = access.getId();
        this.accountId = access.getAccount().getId(); // TODO: 2022/03/20 N + 1 문제 발생할 지점. 테스트 후 개선 필요
        this.loginDateTime = access.getLoginDateTime();
        this.logoutDateTime = access.getLogoutDateTime();
    }
}
