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
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "sellingPrice")
    private Double sellingPrice;

    @Column(name = "imageUrl")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "supplierId")
    private Supplier supplierId;

}
