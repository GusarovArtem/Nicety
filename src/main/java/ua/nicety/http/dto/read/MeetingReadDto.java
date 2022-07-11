package ua.nicety.http.dto.read;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

@Builder
@Value
public class MeetingReadDto implements TemporalAccessor {

    Long id;

    String name;

    String description;

    String smiles;

    String color;

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
