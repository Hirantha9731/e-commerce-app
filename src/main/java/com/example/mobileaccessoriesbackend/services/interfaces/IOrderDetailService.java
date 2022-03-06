package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.dto.request.OrderDetailRequest;
import com.example.mobileaccessoriesbackend.dto.response.OrderDetailResponse;
import com.example.mobileaccessoriesbackend.entity.OrderDetail;

import java.util.List;

public interface IOrderDetailService {

    List<OrderDetailResponse> getAllDetails();

    OrderDetailResponse saveDetails(OrderDetailRequest orderDetailRequest);

    OrderDetail getOrderDetailsById(Long id);

    OrderDetailResponse updateDetails(OrderDetailRequest orderDetailRequest);

    Boolean deleteOrderDetails(Long id);
}
