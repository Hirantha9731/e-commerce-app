package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.response.DriverResponse;
import com.example.mobileaccessoriesbackend.entity.Driver;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.DriverRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IDriverService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService implements IDriverService {

    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;

    public DriverService(DriverRepository driverRepository,
                         ModelMapper modelMapper) {
        this.driverRepository = driverRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Driver findById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Driver not exist with id : "+id));
    }

    @Override
    public List<DriverResponse> findAll() {
        return driverRepository.findAll().stream().map(driver -> modelMapper.map(driver, DriverResponse.class)).collect(Collectors.toList());
    }
}
