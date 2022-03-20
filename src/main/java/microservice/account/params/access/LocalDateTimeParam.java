package microservice.account.params.access;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LocalDateTimeParam {

    @NotNull
    private int year;

    @NotNull
    private int month;

    @NotNull
    private int day;

    @NotNull
    private int hour;

    @NotNull
    private int minute;

    private int second;

}
