package org.example.attendancemanagementsystem.Service;

import org.example.attendancemanagementsystem.Model.AlertModel;
import org.example.attendancemanagementsystem.Model.AttendanceModel;
import org.example.attendancemanagementsystem.Model.StudentModel;
import org.example.attendancemanagementsystem.Repository.AlertRepository;
import org.example.attendancemanagementsystem.Repository.AttendanceRepository;
import org.example.attendancemanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AlertRepository alertRepository;

    // ─── MARK ATTENDANCE ───────────────────────────────────────
    public String markAttendance(AttendanceModel attendance) {

        AttendanceModel existing = attendanceRepository
                .findByStudentIdAndDateAndPeriod(
                        attendance.getStudentId(),
                        attendance.getDate(),
                        attendance.getPeriod()
                );

        if (existing != null) {
            return "Attendance already marked for this period";
        }

        attendanceRepository.save(attendance);

        // After marking, check if student dropped below 75%
        checkAndSendAlert(attendance.getStudentId());

        return "Attendance marked successfully";
    }

    // ─── GET ATTENDANCE BY STUDENT ─────────────────────────────
    public List<AttendanceModel> getAttendance(String studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    // ─── ATTENDANCE PERCENTAGE ─────────────────────────────────
    public double getAttendancePercentage(String studentId) {

        long total = attendanceRepository.countByStudentId(studentId);
        long present = attendanceRepository.countByStudentIdAndStatus(studentId, "Present");

        if (total == 0) return 0;

        return (present * 100.0) / total;
    }

    // ─── STUDENT ATTENDANCE SUMMARY ────────────────────────────
    public AttendanceSummary getAttendanceSummary(String studentId) {

        long total = attendanceRepository.countByStudentId(studentId);
        long present = attendanceRepository.countByStudentIdAndStatus(studentId, "Present");
        long absent = total - present;
        double percentage = total == 0 ? 0 : (present * 100.0) / total;

        return new AttendanceSummary(studentId, total, present, absent, percentage);
    }

    // ─── DEFAULTER LIST (below 75%) ────────────────────────────
    public List<AttendanceSummary> getDefaulters() {

        List<StudentModel> allStudents = studentRepository.findAll();
        List<AttendanceSummary> defaulters = new ArrayList<>();

        for (StudentModel student : allStudents) {
            String sid = student.getStudentId();
            if (sid == null) continue;

            long total = attendanceRepository.countByStudentId(sid);
            long present = attendanceRepository.countByStudentIdAndStatus(sid, "Present");
            long absent = total - present;
            double percentage = total == 0 ? 0 : (present * 100.0) / total;

            if (percentage < 75.0 && total > 0) {
                AttendanceSummary summary = new AttendanceSummary(sid, total, present, absent, percentage);
                summary.setStudentName(student.getName());
                summary.setDepartment(student.getDepartment());
                summary.setClassName(student.getClassName());
                defaulters.add(summary);
            }
        }

        return defaulters;
    }

    // ─── AUTO ALERT WHEN BELOW 75% ─────────────────────────────
    private void checkAndSendAlert(String studentId) {

        double percentage = getAttendancePercentage(studentId);

        if (percentage < 75.0) {

            StudentModel student = studentRepository.findByStudentId(studentId);
            String studentName = (student != null) ? student.getName() : studentId;

            String now = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            String message = "⚠ Warning: Your attendance is "
                    + String.format("%.2f", percentage)
                    + "%. Minimum required is 75%. Please attend classes regularly.";

            // Avoid duplicate alerts for same percentage drop
            List<AlertModel> existing = alertRepository.findByStudentId(studentId);
            boolean alreadySent = existing.stream()
                    .anyMatch(a -> a.getMessage().equals(message));

            if (!alreadySent) {
                AlertModel alert = new AlertModel();
                alert.setStudentId(studentId);
                alert.setStudentName(studentName);
                alert.setMessage(message);
                alert.setSentAt(now);
                alert.setSeen(false);
                alertRepository.save(alert);
            }
        }
    }

    // ─── INNER CLASS: Summary Response ─────────────────────────
    public static class AttendanceSummary {

        private String studentId;
        private String studentName;
        private String department;
        private String className;
        private long totalPeriods;
        private long present;
        private long absent;
        private double percentage;

        public AttendanceSummary(String studentId, long total,
                                 long present, long absent, double percentage) {
            this.studentId = studentId;
            this.totalPeriods = total;
            this.present = present;
            this.absent = absent;
            this.percentage = Math.round(percentage * 100.0) / 100.0;
        }

        // Getters and Setters
        public String getStudentId() { return studentId; }
        public void setStudentId(String studentId) { this.studentId = studentId; }

        public String getStudentName() { return studentName; }
        public void setStudentName(String studentName) { this.studentName = studentName; }

        public String getDepartment() { return department; }
        public void setDepartment(String department) { this.department = department; }

        public String getClassName() { return className; }
        public void setClassName(String className) { this.className = className; }

        public long getTotalPeriods() { return totalPeriods; }
        public long getPresent() { return present; }
        public long getAbsent() { return absent; }
        public double getPercentage() { return percentage; }
    }
}