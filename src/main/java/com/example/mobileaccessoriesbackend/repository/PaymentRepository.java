package com.example.mobileaccessoriesbackend.repository;

import com.example.mobileaccessoriesbackend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
