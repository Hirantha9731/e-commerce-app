package com.example.mobileaccessoriesbackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "vehicleType")
    private String vehicleType;

    @Column(name = "vehicleNumber")
    private String vehicleNumber;

    @Column(name = "vehicleStatus")
    private String vehicleStatus;


}
