package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.entity.Product;
import com.example.mobileaccessoriesbackend.entity.Vehicle;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.VehicleRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IVehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleService implements IVehicleService {

    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Vehicle findVehicleById(Long id) {
        Vehicle vehicle =vehicleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Vehicle not exist with id : "+id));

        return vehicle;
    }
}
