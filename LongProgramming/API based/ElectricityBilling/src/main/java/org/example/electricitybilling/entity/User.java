package org.example.electricitybilling.entity;
import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    @Column(unique = true)
    private String phone;
    private String address;
    private String meterType;
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Meter> meters;

}