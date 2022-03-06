package com.example.mobileaccessoriesbackend.repository;

import com.example.mobileaccessoriesbackend.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
