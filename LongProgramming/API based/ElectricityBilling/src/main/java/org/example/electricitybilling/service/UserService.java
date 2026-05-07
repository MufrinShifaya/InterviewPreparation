package org.example.electricitybilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.electricitybilling.entity.User;
import org.example.electricitybilling.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // 🔹 Register user
    public User register(User user) {

        // Check if phone already exists
        Optional<User> existingUser = repo.findByPhone(user.getPhone());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Phone number already registered");
        }

        return repo.save(user);
    }

    // 🔹 Login user
    public User login(String phone, String password) {

        Optional<User> optionalUser = repo.findByPhone(phone);

        // User not found
        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        // Password check (safe)
        if (user.getPassword() == null || password == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        return user;
    }
}