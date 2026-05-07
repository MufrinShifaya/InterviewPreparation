package org.example.electricitybilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.electricitybilling.entity.User;
import org.example.electricitybilling.service.UserService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        try {
            service.login(user.getPhone(), user.getPassword());
            return "Login successful";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @GetMapping("/test")
    public String test() {
        return "Working";
    }

}