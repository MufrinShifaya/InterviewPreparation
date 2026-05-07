package org.example.electricitybilling.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "meter")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meterId;

    private String meterNumber;

    @Enumerated(EnumType.STRING)
    private MeterType meterType;

    private Double unitPrice;

    private int units;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}