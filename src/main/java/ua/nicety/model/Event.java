package ua.nicety.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private Long id;

    @NotEmpty(message = "Please write the name of the event")
    private String name;

    private String description;

    private String smiles;

    @NotEmpty(message = "Please write the day of the event")
    private Day day;

    @NotEmpty(message = "Please write the time of the event")
    private LocalTime time;

    private String color;

    private Long scheduleId;


}
