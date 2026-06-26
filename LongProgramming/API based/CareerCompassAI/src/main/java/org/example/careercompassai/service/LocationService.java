package org.example.careercompassai.service;

import lombok.RequiredArgsConstructor;
import org.example.careercompassai.entity.Location;
import org.example.careercompassai.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

}