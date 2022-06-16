package ua.nicety.model;

import lombok.Data;

import java.time.LocalTime;

@Data
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
