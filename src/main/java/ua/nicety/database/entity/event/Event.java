package ua.nicety.database.entity.event;

import lombok.*;
import ua.nicety.database.entity.Day;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@ToString(exclude = "schedule")
@EqualsAndHashCode(callSuper=false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Event extends BaseEvent {

    @Enumerated(EnumType.STRING)
    private Day day;

    private LocalTime time;


}
