package ua.nicety.http.dto;

import lombok.Value;
import ua.nicety.database.entity.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Value
public class ScheduleCreateEditDto {

    @NotEmpty(message = "Please write the name of the schedule")
    String name;

    User author;
}
