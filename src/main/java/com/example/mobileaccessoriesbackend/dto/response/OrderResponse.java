package com.example.mobileaccessoriesbackend.dto.response;

import com.example.mobileaccessoriesbackend.enums.OrderStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {

    private Long id;
    private LocalDate orderDate;
    private String description;
    private String deliverAddress;
    private CustomerResponse customer;
    private BranchResponse branch;
    private OrderStatusType status;
    private SalesAgentResponse salesAgent;
    private String saleAgentNote;
    private VehicleResponse vehicle;
    private Date deliverDate;
    private PaymentResponse payment;
    private List<OrderDetailResponse> orderDetailResponse;
}
