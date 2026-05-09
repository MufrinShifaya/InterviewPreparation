package org.example.attendancemanagementsystem.Controller;

import org.example.attendancemanagementsystem.Model.AdminModel;
import org.example.attendancemanagementsystem.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // REGISTER
    @PostMapping("/register")
    public AdminModel register(@RequestBody AdminModel admin) {

        return adminService.register(admin);
    }
    @PostMapping("/login")
    public String login(@RequestBody AdminModel admin) {

        if(admin.getEmail().equals("admin@gmail.com")
                && admin.getPassword().equals("admin123")) {

            return "Admin Login Successful";
        }

        return "Invalid Admin Credentials";
    }
}