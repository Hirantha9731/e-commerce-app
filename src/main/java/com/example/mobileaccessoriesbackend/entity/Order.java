package com.example.mobileaccessoriesbackend.entity;

import com.example.mobileaccessoriesbackend.enums.OrderStatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate orderDate;

    @Column(name = "description")
    private String description;

    @Column(name = "deliverAddress")
    private String deliverAddress;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "branchId")
    private Branch branch;

    @Column(name = "status")
    private OrderStatusType status;

    @ManyToOne
    @JoinColumn(name = "salesAgentId")
    private SalesAgent salesAgent;

    @Column(name = "saleAgentNote")
    private String saleAgentNote;

    @ManyToOne
    @JoinColumn(name = "vehicleId")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driverId")
    private Driver driver;

    @Column(name = "deliverDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate deliverDate;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
