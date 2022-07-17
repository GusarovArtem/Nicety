package ua.nicety.http.dto.createEdit;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ua.nicety.database.entity.Day;
import ua.nicety.http.validation.annotation.ScheduleExistValidate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventCreateEditDto {

    @NotBlank(message = "Please write the name of the event")
    String name;

    String description;

    String smiles;

    String color;

    @NotBlank(message = "Please choose the day of the event")
    Day day;

    @NotNull(message = "Please write time of the event")
    LocalTime time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    private boolean notify;

    @ScheduleExistValidate
    String scheduleId;
}
