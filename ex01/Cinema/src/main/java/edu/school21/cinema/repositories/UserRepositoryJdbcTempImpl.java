package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryJdbcTempImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (email, firstName, lastName, phoneNumber, password)" +
                        "VALUES (?, ?, ?, ?, ?)",
                user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getPassword());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM users WHERE email=?",
                new BeanPropertyRowMapper<>(User.class),
                new Object[]{email}).stream().findAny();
    }
}
