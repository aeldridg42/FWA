package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();
    void save(User user);

    Optional<User> findByEmail(String email);
}
