package com.example.mobileaccessoriesbackend.repository;

import com.example.mobileaccessoriesbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
