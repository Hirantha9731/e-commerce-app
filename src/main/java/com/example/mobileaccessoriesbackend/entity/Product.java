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

    public Product() {
    }

    public Product(String productName, Double sellingPrice, long supplierId) {
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.supplierId = supplierId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }
}
