package com.example.mobileaccessoriesbackend.entity;

import com.example.mobileaccessoriesbackend.entity.enums.OrderStatusType;
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
    private Long id;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "description")
    private String description;

    @Column(name = "deliverAddress")
    private String deliverAddress;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branchId;

    @Column(name = "status")
    private OrderStatusType status;

    @ManyToOne
    @JoinColumn(name = "salesAgentId")
    private SalesAgent salesAgentId;

    @Column(name = "saleAgentNote")
    private String saleAgentNote;

    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicleId;

    @Column(name = "deliverDate")
    private Date deliverDate;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment paymentId;

}
