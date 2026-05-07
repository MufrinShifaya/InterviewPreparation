package org.example.electricitybilling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.electricitybilling.entity.Meter;
import org.example.electricitybilling.service.MeterService;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/meter")
public class MeterController {
    @Autowired
    private MeterService service;
    @PostMapping("/add/{userId}")
    public Meter addMeter(@PathVariable Long userId, @RequestBody Meter meter) {
        return service.addMeter(userId, meter);
    }
    @GetMapping("/user/{userId}")
    public List<Meter> getMeters(@PathVariable Long userId) {
        return service.getMetersByUser(userId);
    }

    // 🔥 NEW: Update bill (Admin)
    @PutMapping("/update/{meterId}")
    public Meter updateBill(@PathVariable Long meterId,
                            @RequestParam int units,
                            @RequestParam double amount) {

        return service.updateBill(meterId, units, amount);
    }
}