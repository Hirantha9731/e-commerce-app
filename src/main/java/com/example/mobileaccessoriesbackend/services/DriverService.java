package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.entity.Driver;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.DriverRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IDriverService;
import org.springframework.stereotype.Service;

@Service
public class DriverService implements IDriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver findById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Driver not exist with id : "+id));
    }
}
