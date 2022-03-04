package com.example.mobileaccessoriesbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "description")
    private String description;

    @Column(name = "deliverAddress")
    private String deliverAddress;

    @ManyToOne
    @JoinColumn(name = "fk_customerId")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "fk_branchId")
    private Branch branchId;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "fk_salesAgentId")
    private SalesAgent salesAgentId;

    @Column(name = "saleAgentNote")
    private String saleAgentNote;

    @ManyToOne
    @JoinColumn(name = "fk_vehicleId")
    private Vehicle vehicleId;

    @Column(name = "deliverDate")
    private Date deliverDate;

    @ManyToOne
    @JoinColumn(name = "fk_paymentId")
    private Payment paymentId;










}
