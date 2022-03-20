package microservice.account.params.utils;

import microservice.account.params.access.LocalDateTimeParam;

import java.time.LocalDateTime;

public class Param2LocalDateTime {

    public static LocalDateTime transfer(LocalDateTimeParam param) {
        return LocalDateTime.of(
                param.getYear(),
                param.getMonth(),
                param.getDay(),
                param.getHour(),
                param.getMinute(),
                param.getSecond()
        );
    }

}
