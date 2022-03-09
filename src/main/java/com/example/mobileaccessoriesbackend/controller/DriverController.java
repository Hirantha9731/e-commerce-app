package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.dto.response.BranchResponse;
import com.example.mobileaccessoriesbackend.dto.response.DriverResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.services.interfaces.IDriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final IDriverService driverService;

    public DriverController(IDriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<DriverResponse> productsList = driverService.findAll();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Driver fetch successful",
                productsList));
    }
}
