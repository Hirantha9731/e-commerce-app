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
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    @Column(name = "username")
    private String username;

    @Column(name = "supplierName")
    private String supplierName;

    @Column(name = "brandName")
    private String brandName;

    @Column(name = "contactNo")
    private String contactNo;

    @Column(name = "address")
    private String address;


}
