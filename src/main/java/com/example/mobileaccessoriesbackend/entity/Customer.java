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


}
