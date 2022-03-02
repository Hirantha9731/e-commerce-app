package com.example.mobileaccessoriesbackend.entity;


import javax.persistence.*;

@Entity
@Table(name = "receivedProducts")
public class ProdReceived {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "productName")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "supplierId")
    private long supplierId;

    public ProdReceived() {
    }

    public ProdReceived(String productName, int quantity, long supplierId) {
        this.productName = productName;
        this.quantity = quantity;
        this.supplierId = supplierId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }
}
