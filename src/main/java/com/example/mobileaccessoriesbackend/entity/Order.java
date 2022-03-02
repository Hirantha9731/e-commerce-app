package com.example.mobileaccessoriesbackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "deliveryLocation")
    private String deliveryLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "customerId")
    private long customerId;

    @Column(name = "branchId")
    private long branchId;

    @Column(name = "salesAgentId")
    private long salesAgentId;

    @Column(name = "saleAgentNote")
    private String saleAgentNote;

    @Column(name = "status")
    private String status;

    public Order() {
    }

    public Order(String deliveryLocation, String description, String contactNumber, long customerId, long branchId, long salesAgentId, String saleAgentNote, String status) {
        this.deliveryLocation = deliveryLocation;
        this.description = description;
        this.contactNumber = contactNumber;
        this.customerId = customerId;
        this.branchId = branchId;
        this.salesAgentId = salesAgentId;
        this.saleAgentNote = saleAgentNote;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeliveryLocation() {
        return deliveryLocation;
    }

    public void setDeliveryLocation(String deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public long getSalesAgentId() {
        return salesAgentId;
    }

    public void setSalesAgentId(long salesAgentId) {
        this.salesAgentId = salesAgentId;
    }

    public String getSaleAgentNote() {
        return saleAgentNote;
    }

    public void setSaleAgentNote(String saleAgentNote) {
        this.saleAgentNote = saleAgentNote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
