package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.response.VehicleResponse;
import com.example.mobileaccessoriesbackend.entity.Product;
import com.example.mobileaccessoriesbackend.entity.Vehicle;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.VehicleRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IVehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService implements IVehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    public VehicleService(VehicleRepository vehicleRepository,
                          ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Vehicle findVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Vehicle not exist with id : "+id));
    }

    @Override
    public List<VehicleResponse> findAll() {
        return vehicleRepository.findAll().stream().map(vehicle -> modelMapper.map(vehicle, VehicleResponse.class)).collect(Collectors.toList());
    }
}
