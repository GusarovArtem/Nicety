package ua.nicety.database.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.nicety.database.model.Event;
import ua.nicety.database.model.Schedule;

import java.util.List;

@Component
public class EventDAO {

    JdbcTemplate jdbcTemplate;

    public List<Schedule> allScheduleEvents(Long scheduleId) {
        return jdbcTemplate.query("SELECT * FROM event WHERE schedule_id=?", new BeanPropertyRowMapper<>(Schedule.class), scheduleId);
    }

    public Schedule show(Long id) {
        return jdbcTemplate.query("SELECT * FROM event WHERE id=?", new BeanPropertyRowMapper<>(Schedule.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Event event) {
        jdbcTemplate.update("INSERT INTO event VALUES(?, ?, ?, ?, ?, ?, ?)", event.getColor(), event.getDay(), event.getDescription(),
                event.getName(), event.getSmiles(), event.getTime(), event.getScheduleId());
    }

    public void update(Long id, Event editedEvent) {
        jdbcTemplate.update("UPDATE event SET color=?, day=?, description=?, name=?, smiles?, time=? WHERE id=?",
                editedEvent.getColor(), editedEvent.getDay(), editedEvent.getDescription(), editedEvent.getName(),
                editedEvent.getSmiles(), editedEvent.getTime(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM event WHERE id=?", id);
    }
}
