package microservice.account.controller;

import lombok.RequiredArgsConstructor;
import microservice.account.dto.AccessDto;
import microservice.account.entity.Access;
import microservice.account.params.access.LocalDateTimeParam;
import microservice.account.params.utils.Param2LocalDateTime;
import microservice.account.service.AccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/access")
@RequiredArgsConstructor
public class AccessController {

    private final AccessService ACCESS_SERVICE;

    @GetMapping("/account/{id}")
    public ResponseEntity<List<AccessDto>> findAllByAccount(@PathVariable Long id) {
        List<Access> accesses = ACCESS_SERVICE.findAllByAccount(id);
        List<AccessDto> result = accesses.stream().map(AccessDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<AccessDto> findOneByToken(@PathVariable String token) {
        Access access = ACCESS_SERVICE.findOneByToken(token);
        AccessDto result = new AccessDto(access);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/login")
    public ResponseEntity<List<AccessDto>> findAllByLoginDateTimeAfter(@Valid @RequestParam LocalDateTimeParam param) {
        LocalDateTime localDateTime = Param2LocalDateTime.transfer(param);
        List<Access> accesses = ACCESS_SERVICE.findAllByLoginDateTimeAfter(localDateTime);
        List<AccessDto> result = accesses.stream().map(AccessDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/logout")
    public ResponseEntity<List<AccessDto>> findAllByLogoutDateTimeAfter(@Valid @RequestParam LocalDateTimeParam param) {
        LocalDateTime localDateTime = Param2LocalDateTime.transfer(param);
        List<Access> accesses = ACCESS_SERVICE.findAllByLogoutDateTimeAfter(localDateTime);
        List<AccessDto> result = accesses.stream().map(AccessDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(result);
    }
}
