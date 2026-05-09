package org.example.attendancemanagementsystem.Repository;

import org.example.attendancemanagementsystem.Model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminModel, Long> {

    AdminModel findByEmail(String email);
}