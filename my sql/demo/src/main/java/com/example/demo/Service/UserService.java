package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.UserRepo;
import com.tech.course_mania.dto.UpdateRequest;
import com.tech.course_mania.model.User;
import com.tech.course_mania.repository.UserRepository;
import com.example.demo.Model.User

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private UserRepo passwordEncoder;

    // newUser
    public User createUser(@NonNull User user) {
        // Encrypt the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // getUser
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // getUsers
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // updateUser
    public User updateUser(@NonNull String email, UpdateRequest updateRequest) {
        return userRepository.findByEmail(email)
                .map(existingUser -> {
                    existingUser.setName(updateRequest.getName());
                    existingUser.setEmail(updateRequest.getEmail());
                    existingUser.setPassword(passwordEncoder.encode(updateRequest.getPassword()));
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
    }

    // deleteUser
    public void removeUser(@NonNull Integer userId) {
        userRepository.deleteById(userId);
    }
}
 