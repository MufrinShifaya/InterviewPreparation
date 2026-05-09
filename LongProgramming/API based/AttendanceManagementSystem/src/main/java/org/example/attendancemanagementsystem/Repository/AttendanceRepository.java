package org.example.attendancemanagementsystem.Repository;

import org.example.attendancemanagementsystem.Model.AttendanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<AttendanceModel, Long> {

    List<AttendanceModel> findByStudentId(String studentId);

    List<AttendanceModel> findByDate(String date);

    List<AttendanceModel> findByStudentIdAndDate(String studentId, String date);

    List<AttendanceModel> findByStudentIdAndSubject(String studentId, String subject);

    AttendanceModel findByStudentIdAndDateAndPeriod(String studentId, String date, int period);

    long countByStudentId(String studentId);

    long countByStudentIdAndStatus(String studentId, String status);
}