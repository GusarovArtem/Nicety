package ua.nicety.http.dto;

import lombok.Value;
import ua.nicety.database.entity.Day;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@Value
public class EventCreateEditDto {

    @NotEmpty(message = "Please write the name of the event")
    String name;

    String description;

    String smiles;

    @NotBlank(message = "Please write the day of the event")
    Day day;

    @NotBlank(message = "Please write the time of the event")
    LocalTime time;

    @NotBlank
    Long scheduleId;
}
