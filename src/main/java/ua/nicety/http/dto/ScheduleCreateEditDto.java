package ua.nicety.http.dto;

import lombok.Value;

import javax.validation.constraints.NotEmpty;

@Value
public class ScheduleCreateEditDto {

    @NotEmpty(message = "Please write the name of the schedule")
    String name;
}
