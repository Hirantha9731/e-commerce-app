package com.example.mobileaccessoriesbackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "sellingPrice")
    private Double sellingPrice;

    @Column(name = "supplierId")
    private long supplierId;


}
