package ua.nicety.http.dto.createEdit;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ua.nicety.database.entity.User;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleCreateEditDto {

    @NotBlank(message = "Please write the name of the schedule")
    String name;

    User author;
}
