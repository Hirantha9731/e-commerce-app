package com.example.mobileaccessoriesbackend.entity;


import javax.persistence.*;

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

    public Branch() {
    }

    public Branch(String branchName, String branchLocation) {
        this.branchName = branchName;
        this.branchLocation = branchLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchLocation() {
        return branchLocation;
    }

    public void setBranchLocation(String branchLocation) {
        this.branchLocation = branchLocation;
    }
}
