package com.example.mobileaccessoriesbackend.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "branchName")
    private String branchName;

    @Column(name = "branchLocation")
    private String branchLocation;


}
