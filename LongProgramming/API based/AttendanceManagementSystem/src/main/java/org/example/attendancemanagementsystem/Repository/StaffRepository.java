package org.example.attendancemanagementsystem.Repository;

import org.example.attendancemanagementsystem.Model.StaffModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<StaffModel, Long> {

    StaffModel findByEmail(String email);

    List<StaffModel> findByDepartment(String department);

    List<StaffModel> findByRole(String role);
}