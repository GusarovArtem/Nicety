package ua.nicety.database.entity.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Goal extends BaseEvent {


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

}
