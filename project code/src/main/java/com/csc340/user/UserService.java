package com.csc340.user;

import com.csc340.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(null);
        reviewRepository.deleteByUsername(username);
        userRepository.delete(user);
    }

    public void changeUserRole(String username, String newRole) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username: " + username));
        user.setRole(newRole);
        userRepository.save(user);
    }

    public String saveUser(User user) {

        if (userRepository.existsByUsername(user.getUsername())) {
            return "redirect:/username-exists";
        } else {

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return "redirect:/home";
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(null);
    }
}