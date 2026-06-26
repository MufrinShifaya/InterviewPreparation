package org.example.careercompassai.controller;

import lombok.RequiredArgsConstructor;
import org.example.careercompassai.entity.Location;
import org.example.careercompassai.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

}