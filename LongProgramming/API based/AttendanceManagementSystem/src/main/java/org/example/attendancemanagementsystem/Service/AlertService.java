package org.example.attendancemanagementsystem.Service;

import org.example.attendancemanagementsystem.Model.AlertModel;
import org.example.attendancemanagementsystem.Repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    // GET ALERTS FOR A SPECIFIC STUDENT
    public List<AlertModel> getAlertsForStudent(String studentId) {
        return alertRepository.findByStudentId(studentId);
    }

    // GET ALL UNREAD ALERTS (for admin/HOD view)
    public List<AlertModel> getUnreadAlerts() {
        return alertRepository.findBySeenFalse();
    }

    // GET ALL ALERTS (for admin/HOD view)
    public List<AlertModel> getAllAlerts() {
        return alertRepository.findAll();
    }

    // MARK ALERT AS SEEN (student reads it)
    public String markSeen(Long alertId) {
        AlertModel alert = alertRepository.findById(alertId).orElse(null);
        if (alert != null) {
            alert.setSeen(true);
            alertRepository.save(alert);
            return "Alert marked as seen";
        }
        return "Alert not found";
    }
}