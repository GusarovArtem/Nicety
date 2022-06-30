package ua.nicety.http.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ua.nicety.database.entity.Day;
import ua.nicety.http.validation.annotation.ScheduleValidate;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Builder
@Value
public class EventReadDto {

    Long id;

    String name;

    String description;

    String smiles;

    String color;

    Day day;

    LocalTime time;
}
