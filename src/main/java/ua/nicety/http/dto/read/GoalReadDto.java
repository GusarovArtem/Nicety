package ua.nicety.http.dto.read;

import lombok.Builder;
import lombok.Value;
import ua.nicety.database.entity.Day;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

@Builder
@Value
public class GoalReadDto implements TemporalAccessor {

    Long id;

    String name;

    String description;

    String smiles;

    String color;

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
