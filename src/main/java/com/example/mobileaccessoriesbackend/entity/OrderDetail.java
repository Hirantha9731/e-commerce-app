package com.example.mobileaccessoriesbackend.entity;

import javax.persistence.*;

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

    public OrderDetail() {
    }

    public OrderDetail(long orderId, long productId, int productQt) {
        this.orderId = orderId;
        this.productId = productId;
        this.productQt = productQt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getProductQt() {
        return productQt;
    }

    public void setProductQt(int productQt) {
        this.productQt = productQt;
    }
}
