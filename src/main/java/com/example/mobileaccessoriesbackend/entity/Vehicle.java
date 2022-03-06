package com.example.mobileaccessoriesbackend.entity;

import com.example.mobileaccessoriesbackend.entity.enums.VehicleStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle ")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicleType")
    private String vehicleType;

    @Column(name = "vehicleNumber")
    private String vehicleNumber;

    @Column(name = "vehicleStatus")
    private VehicleStatusType vehicleStatus;


}
