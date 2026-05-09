package org.example.attendancemanagementsystem.Repository;

import org.example.attendancemanagementsystem.Model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentModel, Long> {

    StudentModel findByEmail(String email);

    StudentModel findByStudentId(String studentId);
    StudentModel findByEmailAndPassword(String email, String password);
    List<StudentModel> findByDepartment(String department);

    List<StudentModel> findByClassName(String className);
}