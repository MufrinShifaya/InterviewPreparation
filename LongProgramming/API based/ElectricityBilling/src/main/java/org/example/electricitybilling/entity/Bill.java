package org.example.electricitybilling.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;

    private int units;

    private double amount;

    private LocalDate dueDate;

    private String status; // PAID / UNPAID

    private double fine;

    private LocalDateTime paymentTime;
}