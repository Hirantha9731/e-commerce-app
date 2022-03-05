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
@Table(name = "sales_Agent")
public class SalesAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "contact_number")
    private String contactNo;

    @ManyToOne
    @JoinColumn(name = "fk_branchId")
    private Branch branchId;


}
