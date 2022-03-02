package com.example.mobileaccessoriesbackend.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "purchaseOrders")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "employeeId")
    private long employeeId;

    @Column(name = "note")
    private String note;

    @Column(name = "purchaseOrderNumber")
    private int purchaseOrderNumber;

    @Column(name = "productId")
    private long productId;

    @Column(name = "supplierId")
    private long supplierId;

    @Column(name = "productQuantity")
    private int productQuantity;

    @Column(name = "expectedDate")
    private Date expectedDate;

    @Column(name = "status")
    private String status;

    public PurchaseOrder() {
    }

    public PurchaseOrder(long employeeId, String note, int purchaseOrderNumber, long productId, long supplierId, int productQuantity, Date expectedDate, String status) {
        this.employeeId = employeeId;
        this.note = note;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.productId = productId;
        this.supplierId = supplierId;
        this.productQuantity = productQuantity;
        this.expectedDate = expectedDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(int purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
