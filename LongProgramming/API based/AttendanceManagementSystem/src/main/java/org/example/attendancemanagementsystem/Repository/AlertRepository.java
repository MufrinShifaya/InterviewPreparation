package org.example.attendancemanagementsystem.Repository;

import org.example.attendancemanagementsystem.Model.AlertModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<AlertModel, Long> {

    List<AlertModel> findByStudentId(String studentId);

    List<AlertModel> findBySeenFalse();
}