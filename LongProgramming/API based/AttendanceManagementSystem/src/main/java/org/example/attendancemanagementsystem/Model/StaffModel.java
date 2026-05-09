package org.example.attendancemanagementsystem.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class StaffModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String staffId;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;

    private String department;

    private String subject;
}