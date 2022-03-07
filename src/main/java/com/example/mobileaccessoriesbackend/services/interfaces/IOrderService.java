package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.dto.request.OrderConfirmRequest;
import com.example.mobileaccessoriesbackend.dto.request.OrderRequest;
import com.example.mobileaccessoriesbackend.dto.response.OrderResponse;
import com.example.mobileaccessoriesbackend.entity.Order;
import com.example.mobileaccessoriesbackend.enums.OrderStatusType;


import java.util.List;

public interface IOrderService {

    List<OrderResponse> getAllOrders();

    Long createOrder(OrderRequest orderRequest);

    Order findOrderById(Long id);

    OrderResponse getOrdersByCustomerId(Long Id);

    OrderResponse updateOrderDetails(OrderRequest orderRequest);

    Boolean deleteOrder(Long id);

    List<OrderResponse> findByStatus(OrderStatusType statusType);

    void confirmOrder(OrderConfirmRequest orderConfirmRequest);
}
