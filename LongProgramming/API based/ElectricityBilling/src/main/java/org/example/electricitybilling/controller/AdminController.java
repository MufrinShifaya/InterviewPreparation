package org.example.electricitybilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.electricitybilling.entity.Admin;
import org.example.electricitybilling.service.AdminService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        try {
            service.login(admin.getUsername(), admin.getPassword());
            return "Admin login successful";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}