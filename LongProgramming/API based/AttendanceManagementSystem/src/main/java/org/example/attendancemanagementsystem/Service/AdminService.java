package org.example.attendancemanagementsystem.Service;

import org.example.attendancemanagementsystem.Model.AdminModel;
import org.example.attendancemanagementsystem.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // REGISTER
    public AdminModel register(AdminModel admin) {

        AdminModel saved = adminRepository.save(admin);

        saved.setAdminId("ADM" + String.format("%03d", saved.getId()));

        return adminRepository.save(saved);
    }

    // LOGIN
    public String login(String email, String password) {

        AdminModel admin = adminRepository.findByEmail(email);

        if (admin != null &&
                admin.getPassword().equals(password)) {

            return "Admin Login Successful";
        }

        return "Invalid Email or Password";
    }
}