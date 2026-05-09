package org.example.attendancemanagementsystem.Service;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class SecurityService {

    private final Map<String, String> otpStore = new HashMap<>();

    public String generateOtp(String staffId) {
        String otp = String.valueOf(new Random().nextInt(9000) + 1000);
        otpStore.put(staffId, otp);
        return "OTP Generated: " + otp;
    }

    public String verifyOtp(String staffId, String otp) {
        String storedOtp = otpStore.get(staffId);

        if (storedOtp != null && storedOtp.equals(otp)) {
            return "Attendance Marked (Present)";
        }

        return "Invalid OTP";
    }

    public String logout(String staffId) {
        return "Logout successful for " + staffId;
    }
}