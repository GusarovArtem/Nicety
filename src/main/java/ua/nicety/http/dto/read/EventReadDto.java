package ua.nicety.http.dto.read;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ua.nicety.database.entity.Day;
import ua.nicety.http.validation.annotation.ScheduleValidate;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

@Builder
@Value
public class EventReadDto implements TemporalAccessor {

    Long id;

    String name;

    String description;

    String smiles;

    String color;

    Day day;

    LocalTime time;

    boolean notify;

    LocalDateTime dateTime;

    @Override
    public boolean isSupported(TemporalField field) {
        return false;
    }


    @Override
    public long getLong(TemporalField field) {
        return 0;
    }
}
