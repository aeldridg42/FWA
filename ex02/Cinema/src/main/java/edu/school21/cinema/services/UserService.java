package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    boolean signUp(String email, String firstName, String lastName, String phoneNumber, String password);

    boolean signIn(String email, String password);

    User getUser(String email);
}
