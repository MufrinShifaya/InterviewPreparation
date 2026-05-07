package org.example.electricitybilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.electricitybilling.entity.Bill;
import org.example.electricitybilling.entity.Meter;
import org.example.electricitybilling.repository.BillRepository;
import org.example.electricitybilling.repository.MeterRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepo;

    @Autowired
    private MeterRepository meterRepo;

    //  Generate Bill
    public Bill generateBill(Long meterId, int units) {

        Meter meter = meterRepo.findById(meterId)
                .orElseThrow(() -> new RuntimeException("Meter not found"));

        double amount;

        if (units <= 100) {
            amount = units * 5;
        } else if (units <= 200) {
            amount = (100 * 5) + ((units - 100) * 7);
        } else {
            amount = (100 * 5) + (100 * 7) + ((units - 200) * 10);
        }

        Bill bill = new Bill();
        bill.setMeter(meter);
        bill.setUnits(units);
        bill.setAmount(amount);
        bill.setDueDate(LocalDate.now().plusDays(10));
        bill.setStatus("UNPAID");
        bill.setFine(0);

        System.out.println("📩 EB Notification:");
        System.out.println("Units: " + units);
        System.out.println("Amount: " + amount);
        System.out.println("Due Date: " + bill.getDueDate());

        return billRepo.save(bill);
    }

    //  Pay Bill
    public Bill payBill(Long billId) {

        Bill bill = billRepo.findById(billId)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        if (bill.getDueDate().isBefore(LocalDate.now())) {
            bill.setFine(100);
        }

        bill.setStatus("PAID");
        bill.setPaymentTime(LocalDateTime.now());

        return billRepo.save(bill);
    }

    //  Get bills by user
    public List<Bill> getBillsByUser(Long userId) {
        return billRepo.findByMeterUserUserId(userId);
    }

    //  Get all bills
    public List<Bill> getAllBills() {
        return billRepo.findAll();
    }

    // Filter by status
    public List<Bill> getByStatus(String status) {
        return billRepo.findByStatus(status);
    }
}