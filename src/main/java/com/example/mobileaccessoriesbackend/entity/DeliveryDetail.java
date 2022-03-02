package com.example.mobileaccessoriesbackend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deliveryDetails")
public class DeliveryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "driverId")
    private long driverId;

    @Column(name = "orderId")
    private long orderId;

    @Column(name = "deliveryDate")
    private Date deliveryDate;

    @Column(name = "vehicleId")
    private long vehicleId;

    public DeliveryDetail() {
    }

    public DeliveryDetail(long driverId, long orderId, Date deliveryDate, long vehicleId) {
        this.driverId = driverId;
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
        this.vehicleId = vehicleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
