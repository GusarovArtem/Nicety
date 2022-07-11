package ua.nicety.database.entity.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.nicety.database.entity.Schedule;

import javax.persistence.*;

@Setter
@Getter
@ToString(exclude = "schedule")
@MappedSuperclass
public class BaseEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String color;

    private String smiles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
