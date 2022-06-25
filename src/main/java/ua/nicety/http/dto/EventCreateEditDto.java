package ua.nicety.http.dto;

import lombok.Value;
import ua.nicety.database.entity.Schedule;

import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@Value
public class EventCreateEditDto {

    @NotEmpty(message = "Please write the name of the event")
    String name;

    String description;

    String smiles;

    String color;

    String day;

    LocalTime time;

    Schedule schedule;
}
