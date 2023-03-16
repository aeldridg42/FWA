package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserLog;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.utils.ErrorFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ErrorFinder errorFinder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public String signUp(String email, String firstName, String lastName, String phoneNumber, String password) {
        if (userRepository.findByEmail(email).isPresent())
            return email + ": this email is already taken";
        String ERROR = errorFinder.getERROR(email, firstName, lastName, phoneNumber, password);
        if (ERROR.length() != 0)
            return ERROR;
        userRepository.save(new User(email, firstName, lastName, phoneNumber, passwordEncoder.encode(password)));
        return ERROR;
    }

    @Override
    public boolean signIn(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    @Override
    public User getUser(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(value -> value.setLogs(userRepository.getLog(user.get())));
        return user.orElse(new User());
    }

    @Override
    public void addLogInfo(String email, String ip) {
        userRepository.saveLog(new UserLog(getUser(email).getId(),
                new Date(System.currentTimeMillis()),
                new Time(System.currentTimeMillis()),
                ip));
    }

    @Override
    public void addPictureName(String email, String picName) {
        userRepository.savePicName(email, picName);
    }
}
