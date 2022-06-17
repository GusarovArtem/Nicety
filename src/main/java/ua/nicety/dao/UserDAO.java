package ua.nicety.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.nicety.model.User;

import java.util.List;

@Component
public class UserDAO {

    JdbcTemplate jdbcTemplate;

    public List<User> showAll() {
        return jdbcTemplate.query("SELECT * FROM usr", new BeanPropertyRowMapper<>(User.class));
    }

    public User show(Long id) {
        return jdbcTemplate.query("SELECT * FROM usr WHERE id=?", new BeanPropertyRowMapper<>(User.class), id)
                .stream().findAny().orElse(null);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO usr VALUES(?, ?, ?, ?)", user.getEmail(), user.getPassword(),
                 user.getRole(), user.getUsername());
    }

    public void update(Long id, User updatedUser) {
        jdbcTemplate.update("UPDATE usr SET email=?, password=?, role=?, username=? WHERE id=?", updatedUser.getEmail(),
                updatedUser.getPassword(), updatedUser.getRole(), updatedUser.getUsername(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM usr WHERE id=?", id);
    }
}