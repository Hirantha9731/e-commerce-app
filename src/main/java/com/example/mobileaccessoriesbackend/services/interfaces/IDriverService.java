package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.dto.response.DriverResponse;
import com.example.mobileaccessoriesbackend.entity.Driver;

import java.util.List;

public interface IDriverService {

    Driver findById(Long id);

    List<DriverResponse> findAll();
}