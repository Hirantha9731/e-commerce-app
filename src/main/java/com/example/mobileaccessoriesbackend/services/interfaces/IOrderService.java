package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.dto.request.OrderRequest;
import com.example.mobileaccessoriesbackend.dto.response.OrderResponse;
import com.example.mobileaccessoriesbackend.entity.Order;


import java.util.List;

public interface IOrderService {

    List<OrderResponse> getAllOrders();

    OrderResponse createOrder(OrderRequest orderRequest);

    Order findOrderById(Long id);

    OrderResponse getOrdersByCustomerId(Long Id);

    OrderResponse updateOrderDetails(OrderRequest orderRequest);

    Boolean deleteOrder(Long id);
}
