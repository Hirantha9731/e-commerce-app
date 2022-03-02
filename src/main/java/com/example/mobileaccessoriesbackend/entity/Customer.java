package com.example.mobileaccessoriesbackend.entity;


import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address")
    private String address;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "customerContact")
    private String customerContact;

    public Customer() {
    }

    public Customer(String address, String customerName, String customerContact) {
        this.address = address;
        this.customerName = customerName;
        this.customerContact = customerContact;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }
}
