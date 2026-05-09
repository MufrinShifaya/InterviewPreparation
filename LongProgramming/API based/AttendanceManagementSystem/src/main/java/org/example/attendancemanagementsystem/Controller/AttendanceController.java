package org.example.attendancemanagementsystem.Controller;

import org.example.attendancemanagementsystem.Model.AttendanceModel;
import org.example.attendancemanagementsystem.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // MARK ATTENDANCE
    @PostMapping("/mark")
    public String markAttendance(@RequestBody AttendanceModel attendance) {
        return attendanceService.markAttendance(attendance);
    }

    // VIEW ATTENDANCE LIST BY STUDENT
    @GetMapping("/student/{studentId}")
    public List<AttendanceModel> getAttendance(@PathVariable String studentId) {
        return attendanceService.getAttendance(studentId);
    }

    // ATTENDANCE PERCENTAGE
    @GetMapping("/percentage/{studentId}")
    public double getPercentage(@PathVariable String studentId) {
        return attendanceService.getAttendancePercentage(studentId);
    }

    // STUDENT FULL SUMMARY (total, present, absent, %)
    @GetMapping("/summary/{studentId}")
    public AttendanceService.AttendanceSummary getSummary(@PathVariable String studentId) {
        return attendanceService.getAttendanceSummary(studentId);
    }

    // DEFAULTER LIST — any role can view
    @GetMapping("/defaulters")
    public List<AttendanceService.AttendanceSummary> getDefaulters() {
        return attendanceService.getDefaulters();
    }
}