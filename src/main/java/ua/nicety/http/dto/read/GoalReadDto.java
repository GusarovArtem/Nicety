package ua.nicety.http.dto.read;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class GoalReadDto {

    Long id;

    String name;

    String description;

    String smiles;

    String color;

    LocalDateTime dateTime;

}
