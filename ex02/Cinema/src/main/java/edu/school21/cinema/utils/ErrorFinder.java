package edu.school21.cinema.utils;

import org.springframework.stereotype.Component;

@Component
public class ErrorFinder {
    public String getERROR(String email, String firstName, String lastName, String phoneNumber, String password) {
        if (email.length() == 0 || firstName.length() == 0 || lastName.length() == 0
            || phoneNumber.length() == 0 || password.length() == 0)
            return "All fields must be filled";
        else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
            return "Please add real email address";
        return "";
    }
}
