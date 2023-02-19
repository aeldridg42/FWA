package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private String email;
    private List<UserLog> logs;
    public User() {}

    public User(String email, String firstName, String lastName, String phoneNumber, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
