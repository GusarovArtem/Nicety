package ua.nicety.database.entity;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "Please write the name of the schedule")
    private String name;

    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Schedule schedule = (Schedule) o;
        return id != null && Objects.equals(id, schedule.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
