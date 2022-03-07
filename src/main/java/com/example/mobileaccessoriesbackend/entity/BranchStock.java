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
@Table(name = "branch_stcok")
public class BranchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fk_productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "fk_branchId")
    private Branch branch;

    @Column(name = "availableQt")
    private int availableQt;

}
