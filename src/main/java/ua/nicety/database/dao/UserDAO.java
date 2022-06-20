package ua.nicety.database.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.nicety.database.model.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public List<User> showAll() {
        return jdbcTemplate.query("SELECT * FROM usr", new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM usr WHERE id=?", new BeanPropertyRowMapper<>(User.class), id)
                .stream().findAny();
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO usr (email, password, role, username) VALUES(?, ?, ?, ?)", user.getEmail(), user.getPassword(),
                 user.getRole().name(), user.getUsername());
    }

    public void update(Long id, User updatedUser) {
        jdbcTemplate.update("UPDATE usr SET email=?, password=?, role=?, username=? WHERE id=?", updatedUser.getEmail(),
                updatedUser.getPassword(), updatedUser.getRole().name(), updatedUser.getUsername(), id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM usr WHERE id=?", id);
    }

    public Optional<User> findByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM usr WHERE username=?", new BeanPropertyRowMapper<>(User.class), username)
                .stream().findAny();
    }

    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM usr WHERE email=?", new BeanPropertyRowMapper<>(User.class), email)
                .stream().findAny();
    }
}