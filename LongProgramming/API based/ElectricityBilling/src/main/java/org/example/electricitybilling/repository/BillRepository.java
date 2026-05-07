package org.example.electricitybilling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.electricitybilling.entity.Bill;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByMeterUserUserId(Long userId);

    List<Bill> findByStatus(String status);
}