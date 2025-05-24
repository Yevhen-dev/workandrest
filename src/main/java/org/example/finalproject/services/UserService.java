package org.example.finalproject.services;

import org.example.finalproject.domains.User;
import org.example.finalproject.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerUser( String username, String password, String email, String role ) {
        User user = new User( username, passwordEncoder.encode( password ), email, role );
        userRepository.save( user );
    }

    public Optional<User> findUserByEmail(String email ) {
        return userRepository.findByEmail( email );
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
