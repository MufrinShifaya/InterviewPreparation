package org.example.attendancemanagementsystem.Controller;

import org.example.attendancemanagementsystem.Model.StaffModel;
import org.example.attendancemanagementsystem.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // REGISTER
    @PostMapping("/register")
    public StaffModel register(@RequestBody StaffModel staff) {
        return staffService.saveStaff(staff);
    }

    // LOGIN ✅ Fixed — returns full staff object
    @PostMapping("/login")
    public ResponseEntity<StaffModel> login(@RequestBody StaffModel staff) {
        StaffModel found = staffService.login(
                staff.getEmail(),
                staff.getPassword()
        );
        if (found != null) {
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.status(401).build();
    }

    // GET ALL STAFF
    @GetMapping("/all")
    public List<StaffModel> getAllStaff() {
        return staffService.getAllStaff();
    }

    // GET TEACHERS
    @GetMapping("/teachers")
    public List<StaffModel> getTeachers() {
        return staffService.getByRole("TEACHER");
    }

    // GET HODS
    @GetMapping("/hods")
    public List<StaffModel> getHods() {
        return staffService.getByRole("HOD");
    }

    // GET STAFF BY DEPARTMENT
    @GetMapping("/department/{department}")
    public List<StaffModel> getByDepartment(
            @PathVariable String department) {
        return staffService.getByDepartment(department);
    }
}