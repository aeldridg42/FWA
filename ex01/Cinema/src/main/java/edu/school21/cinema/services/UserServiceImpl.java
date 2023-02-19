package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public boolean signUp(String email, String firstName, String lastName, String phoneNumber, String password) {
        if (userRepository.findByEmail(email).isPresent())
            return false;
        userRepository.save(new User(email, firstName, lastName, phoneNumber, passwordEncoder.encode(password)));
        return true;
    }

    @Override
    public boolean signIn(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    @Override
    public User getUser(String email) {
        return userRepository.findByEmail(email).get();
    }

}
