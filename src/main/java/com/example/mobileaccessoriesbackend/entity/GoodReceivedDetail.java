package com.example.mobileaccessoriesbackend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "itemReceivedDetails")
public class GoodReceivedDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "poNumber")
    private int poNumber;

    @Column(name = "productId")
    private long productId;

    @Column(name = "productQuantity")
    private int productQuantity;

    @Column(name = "receivedDate")
    private Date receivedDate;

    public GoodReceivedDetail() {
    }

    public GoodReceivedDetail(int poNumber, long productId, int productQuantity, Date receivedDate) {
        this.poNumber = poNumber;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.receivedDate = receivedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(int poNumber) {
        this.poNumber = poNumber;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }
}
