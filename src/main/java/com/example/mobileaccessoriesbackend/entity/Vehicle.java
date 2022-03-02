package com.example.mobileaccessoriesbackend.entity;

import javax.persistence.*;

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

    public Vehicle() {
    }

    public Vehicle(String vehicleType, String vehicleNumber, String vehicleStatus) {
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.vehicleStatus = vehicleStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}
