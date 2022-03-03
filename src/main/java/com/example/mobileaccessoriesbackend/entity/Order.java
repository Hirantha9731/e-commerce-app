package com.example.mobileaccessoriesbackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "deliveryLocation")
    private String deliveryLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "customerId")
    private long customerId;

    @Column(name = "branchId")
    private long branchId;

    @Column(name = "salesAgentId")
    private long salesAgentId;

    @Column(name = "saleAgentNote")
    private String saleAgentNote;

    @Column(name = "status")
    private String status;




}
