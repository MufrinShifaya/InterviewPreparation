package org.example.electricitybilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.electricitybilling.entity.Bill;
import org.example.electricitybilling.service.BillService;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService service;
    @GetMapping("/generate/{meterId}/{units}")
    public Bill generateBill(@PathVariable Long meterId,
                             @PathVariable int units) {
        return service.generateBill(meterId, units);
    }

    // Pay bill
    @PutMapping("/pay/{billId}")
    public Bill payBill(@PathVariable Long billId) {
        return service.payBill(billId);
    }

    // Get bills of user
    @GetMapping("/user/{userId}")
    public List<Bill> getUserBills(@PathVariable Long userId) {
        return service.getBillsByUser(userId);
    }

    //  Admin: all bills
    @GetMapping("/all")
    public List<Bill> getAllBills() {
        return service.getAllBills();
    }

    //  Filter by status
    @GetMapping("/status/{status}")
    public List<Bill> getByStatus(@PathVariable String status) {
        return service.getByStatus(status);

    }
}