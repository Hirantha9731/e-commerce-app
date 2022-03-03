package com.example.mobileaccessoriesbackend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orderDetails")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "orderId")
    private long orderId;

    @Column(name = "productId")
    private long productId;

    @Column(name = "productQt")
    private int productQt;


}
