package org.example.attendancemanagementsystem.Service;

import org.example.attendancemanagementsystem.Model.StaffModel;
import org.example.attendancemanagementsystem.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public StaffModel saveStaff(StaffModel staff) {

        String dept = staff.getDepartment() != null
                ? staff.getDepartment().toUpperCase()
                : "GEN";

        StaffModel saved = staffRepository.save(staff);

        String staffId = "STF-" + dept + String.format("%03d", saved.getId());

        saved.setStaffId(staffId);

        return staffRepository.save(saved);
    }

    public StaffModel login(String email, String password) {

        StaffModel staff = staffRepository.findByEmail(email);

        if (staff != null && staff.getPassword().equals(password)) {
            return staff;
        }

        return null;
    }
    public List<StaffModel> getAllStaff() {
        return staffRepository.findAll();
    }

    // GET BY ROLE
    public List<StaffModel> getByRole(String role) {
        return staffRepository.findByRole(role);
    }

    // GET BY DEPARTMENT
    public List<StaffModel> getByDepartment(String department) {
        return staffRepository.findByDepartment(
                department.toUpperCase()
        );
    }
}