package ua.nicety.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    private Long id;

    @NotEmpty(message = "Please write the name of the schedule")
    private String name;

    private Long userId;

}
