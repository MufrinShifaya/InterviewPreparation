package org.example.electricitybilling.service;

import org.springframework.stereotype.Service;
import org.example.electricitybilling.entity.Admin;

@Service
public class AdminService {

    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "1234";

    public Admin login(String username, String password) {

        if (DEFAULT_USERNAME.equals(username) && DEFAULT_PASSWORD.equals(password)) {
            Admin admin = new Admin();
            admin.setUsername(username);
            return admin;
        }

        return null;
    }
}