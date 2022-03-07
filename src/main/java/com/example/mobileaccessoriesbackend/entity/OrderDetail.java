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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fk_productId")
    private Product product;

    @Column(name = "productQt")
    private int productQt;


}
