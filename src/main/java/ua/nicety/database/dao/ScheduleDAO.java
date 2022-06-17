package ua.nicety.database.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.nicety.database.model.Schedule;

import java.util.List;

@Component
public class ScheduleDAO {

    JdbcTemplate jdbcTemplate;

    public List<Schedule> allUserSchedules(Long userId) {
        return jdbcTemplate.query("SELECT * FROM schedule WHERE user_id=?", new BeanPropertyRowMapper<>(Schedule.class), userId);
    }

    public Schedule show(Long id) {
        return jdbcTemplate.query("SELECT * FROM schedule WHERE id=?", new BeanPropertyRowMapper<>(Schedule.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(Schedule schedule) {
        jdbcTemplate.update("INSERT INTO schedules VALUES(?, ?)", schedule.getName(), schedule.getUserId());
    }

    public void update(Long id, Schedule editedSchedule) {
        jdbcTemplate.update("UPDATE schedule SET name=? WHERE id=?", editedSchedule.getName(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM schedule WHERE id=?", id);
    }
}