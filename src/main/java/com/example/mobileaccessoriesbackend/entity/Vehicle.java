package com.example.mobileaccessoriesbackend.entity;

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
    private long id;

    @Column(name = "vehicleType")
    private String vehicleType;

    @Column(name = "vehicleNumber")
    private String vehicleNumber;

    @Column(name = "vehicleStatus")
    private String vehicleStatus;


}
