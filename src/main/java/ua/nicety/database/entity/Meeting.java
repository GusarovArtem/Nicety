package ua.nicety.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString(exclude = "author")
@EqualsAndHashCode(callSuper=false, of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String color;

    private String smiles;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

}
