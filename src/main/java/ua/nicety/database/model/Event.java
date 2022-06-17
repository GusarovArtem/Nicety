package ua.nicety.database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private Long id;
    private String name;
    private String description;
    private String smiles;
    private Day day;
    private LocalTime time;
    private String color;
    private Long scheduleId;
}
