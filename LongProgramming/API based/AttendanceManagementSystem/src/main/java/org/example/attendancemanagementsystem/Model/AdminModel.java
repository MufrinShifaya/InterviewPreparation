package org.example.attendancemanagementsystem.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin")
public class AdminModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adminId;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;


}