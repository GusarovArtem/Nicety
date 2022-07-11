package ua.nicety.http.dto.read;

import lombok.Builder;
import lombok.Value;
import ua.nicety.database.entity.Day;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Value
public class MeetingReadDto {

    Long id;

    String name;

    String description;

    String smiles;

    String color;

    LocalDateTime dateTime;

    boolean notify;
}
