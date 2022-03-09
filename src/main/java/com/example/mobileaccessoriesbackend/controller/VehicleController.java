package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.dto.response.DriverResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.dto.response.VehicleResponse;
import com.example.mobileaccessoriesbackend.services.interfaces.IVehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final IVehicleService vehicleService;

    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<VehicleResponse> productsList = vehicleService.findAll();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Vehicle fetch successful",
                productsList));
    }

}
