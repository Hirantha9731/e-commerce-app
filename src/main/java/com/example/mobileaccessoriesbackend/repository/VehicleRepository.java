package com.example.mobileaccessoriesbackend.repository;

import com.example.mobileaccessoriesbackend.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
