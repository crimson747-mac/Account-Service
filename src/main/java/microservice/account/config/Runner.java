package microservice.account.config;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservice.account.entity.Role;
import microservice.account.entity.enums.RoleType;
import microservice.account.repository.RoleRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {

    private final RoleRepository ROLE_REPOSITORY;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("DEFAULT ROLE 데이터 생성");
        Role roleUser = new Role(RoleType.USER);
        Role roleManager = new Role(RoleType.MANAGER);
        Role roleAdmin = new Role(RoleType.ADMIN);

        ROLE_REPOSITORY.save(roleUser);
        ROLE_REPOSITORY.save(roleManager);
        ROLE_REPOSITORY.save(roleAdmin);
    }
}
