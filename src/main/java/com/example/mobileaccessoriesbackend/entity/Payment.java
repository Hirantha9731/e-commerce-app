package com.example.mobileaccessoriesbackend.entity;

import com.example.mobileaccessoriesbackend.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paymentType")
    private PaymentType paymentType;

    @Column(name = "paymentDate")
    private Date paymentDate;
}
