package ua.nicety.http.dto.read;

import lombok.Builder;
import lombok.Value;
import ua.nicety.database.entity.User;

@Builder
@Value
public class ScheduleReadDto {

    String id;

    String name;

}
