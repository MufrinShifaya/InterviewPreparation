package org.example.electricitybilling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.electricitybilling.entity.Meter;
import org.example.electricitybilling.entity.User;
import org.example.electricitybilling.repository.MeterRepository;
import org.example.electricitybilling.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeterService {

    @Autowired
    private MeterRepository meterRepo;

    @Autowired
    private UserRepository userRepo;

    //  Add meter (Admin)
    public Meter addMeter(Long userId, Meter meter) {

        Optional<User> optionalUser = userRepo.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        meter.setUser(optionalUser.get());
        return meterRepo.save(meter);
    }

    //  Get meters of user
    public List<Meter> getMetersByUser(Long userId) {
        return meterRepo.findByUserUserId(userId);
    }

    //  Admin updates bill (units + amount)
    public Meter updateBill(Long meterId, int units, double amount) {

        Optional<Meter> optionalMeter = meterRepo.findById(meterId);

        if (optionalMeter.isEmpty()) {
            throw new RuntimeException("Meter not found");
        }

        Meter meter = optionalMeter.get();
        meter.setUnits(units);
        meter.setAmount(amount);

        return meterRepo.save(meter);
    }
}