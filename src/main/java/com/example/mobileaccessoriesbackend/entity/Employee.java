package com.example.mobileaccessoriesbackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "employeeName")
    private String employeeName;

    @Column(name = "EmployeeContactNo")
    private String EmployeeContactNo;

    @Column(name = "designation")
    private String designation;

    @Column(name = "branchId")
    private long branchId;

    public Employee() {
    }

    public Employee(String employeeName, String employeeContactNo, String designation, long branchId) {
        this.employeeName = employeeName;
        EmployeeContactNo = employeeContactNo;
        this.designation = designation;
        this.branchId = branchId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeContactNo() {
        return EmployeeContactNo;
    }

    public void setEmployeeContactNo(String employeeContactNo) {
        EmployeeContactNo = employeeContactNo;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }
}
