package ua.nicety.http.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ua.nicety.database.entity.User;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleCreateEditDto {

    @NotEmpty(message = "Please write the name of the schedule")
    String name;

    User author;
}
