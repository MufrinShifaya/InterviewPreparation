package org.example.electricitybilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.electricitybilling.entity.Meter;

import java.util.List;

public interface MeterRepository extends JpaRepository<Meter, Long> {

    List<Meter> findByUserUserId(Long userId);
}