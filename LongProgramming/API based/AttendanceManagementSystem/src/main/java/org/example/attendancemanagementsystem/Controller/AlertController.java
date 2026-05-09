package org.example.attendancemanagementsystem.Controller;

import org.example.attendancemanagementsystem.Model.AlertModel;
import org.example.attendancemanagementsystem.Service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    // STUDENT views their own alerts
    @GetMapping("/student/{studentId}")
    public List<AlertModel> getStudentAlerts(@PathVariable String studentId) {
        return alertService.getAlertsForStudent(studentId);
    }

    // ADMIN/HOD views all unread alerts
    @GetMapping("/unread")
    public List<AlertModel> getUnread() {
        return alertService.getUnreadAlerts();
    }

    // ADMIN/HOD/TEACHER views all alerts
    @GetMapping("/all")
    public List<AlertModel> getAll() {
        return alertService.getAllAlerts();
    }

    // STUDENT marks alert as seen
    @PutMapping("/seen/{alertId}")
    public String markSeen(@PathVariable Long alertId) {
        return alertService.markSeen(alertId);
    }
}