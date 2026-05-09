package org.example.attendancemanagementsystem.Controller;

import org.example.attendancemanagementsystem.Model.StudentModel;
import org.example.attendancemanagementsystem.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // REGISTER
    @PostMapping("/register")
    public StudentModel register(@RequestBody StudentModel student) {
        return studentService.saveStudent(student);
    }

    @PostMapping("/login")
    public ResponseEntity<StudentModel> login(@RequestBody StudentModel student) {
        StudentModel found = studentService.getStudentByEmailAndPassword(
                student.getEmail(), student.getPassword()
        );
        if (found != null) {
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.status(401).build();
    }

    // GET ALL STUDENTS — any role
    @GetMapping("/all")
    public List<StudentModel> getAllStudents() {
        return studentService.getAllStudents();
    }

    // GET SPECIFIC STUDENT
    @GetMapping("/{studentId}")
    public StudentModel getStudent(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }

    // GET BY DEPARTMENT
    @GetMapping("/department/{dept}")
    public List<StudentModel> getByDept(@PathVariable String dept) {
        return studentService.getByDepartment(dept);
    }

    // GET BY CLASS
    @GetMapping("/class/{className}")
    public List<StudentModel> getByClass(@PathVariable String className) {
        return studentService.getByClass(className);
    }
}