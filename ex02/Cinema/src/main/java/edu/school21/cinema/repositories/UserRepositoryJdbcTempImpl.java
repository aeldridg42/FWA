package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserLog;
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
    private final String USERS = "users";
    private final String LOGS = "usersLog";

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM " + USERS, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO " + USERS + "(email, firstname, lastname, phonenumber, password)" +
                        " VALUES (?, ?, ?, ?, ?)",
                user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getPassword());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jdbcTemplate.query("SELECT * FROM " + USERS + " WHERE email=?",
                new BeanPropertyRowMapper<>(User.class),
                new Object[]{email}).stream().findAny();
    }

    @Override
    public void saveLog(UserLog log) {
        jdbcTemplate.update("INSERT INTO " + LOGS + " (user_id, date, time, ip) VALUES (?, ?, ?, ?)",
                log.getUser_id(), log.getDate(), log.getTime(), log.getIp());
    }

    @Override
    public List<UserLog> getLog(User user) {
        return jdbcTemplate.query("SELECT * FROM " + LOGS + " WHERE user_id=?",
                new BeanPropertyRowMapper<>(UserLog.class),
                user.getId());
    }

    @Override
    public void savePicName(String email, String picName) {
        jdbcTemplate.update("UPDATE " + USERS +
                " SET picName = ? " +
                "WHERE email = ?;", picName, email);
    }

}
