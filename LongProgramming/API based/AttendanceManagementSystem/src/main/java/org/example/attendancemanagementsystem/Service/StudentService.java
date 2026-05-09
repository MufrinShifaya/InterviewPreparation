package org.example.attendancemanagementsystem.Service;

import org.example.attendancemanagementsystem.Model.StudentModel;
import org.example.attendancemanagementsystem.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // REGISTER
    public StudentModel saveStudent(StudentModel student) {

        String dept = student.getDepartment() != null
                ? student.getDepartment().toUpperCase() : "GEN";

        StudentModel saved = studentRepository.save(student);

        String studentId = dept + String.format("%04d", saved.getId());
        saved.setStudentId(studentId);

        return studentRepository.save(saved);
    }

    // LOGIN
    public String login(String email, String password) {

        StudentModel student = studentRepository.findByEmail(email);

        if (student != null && student.getPassword().equals(password)) {
            return "Login Successful";
        }

        return "Invalid Email or Password";
    }

    // GET ALL STUDENTS (any role can call)
    public List<StudentModel> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET SPECIFIC STUDENT BY studentId
    public StudentModel getStudentById(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    // GET BY DEPARTMENT
    public List<StudentModel> getByDepartment(String department) {
        return studentRepository.findByDepartment(department);
    }

    // GET BY CLASS
    public List<StudentModel> getByClass(String className) {
        return studentRepository.findByClassName(className);
    }

    public StudentModel getStudentByEmailAndPassword(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password);
    }

    public StudentModel findByEmailAndPassword(String email, String password) {
        return studentRepository.findByEmailAndPassword(email, password);
    }
}