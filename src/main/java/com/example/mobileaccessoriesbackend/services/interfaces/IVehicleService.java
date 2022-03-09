package com.example.mobileaccessoriesbackend.services.interfaces;


import com.example.mobileaccessoriesbackend.dto.response.VehicleResponse;
import com.example.mobileaccessoriesbackend.entity.Vehicle;

import java.util.List;

public interface IVehicleService {

    Vehicle findVehicleById(Long id);

    List<VehicleResponse> findAll();
}

