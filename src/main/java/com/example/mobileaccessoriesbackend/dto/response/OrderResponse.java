package com.example.mobileaccessoriesbackend.dto.response;

import com.example.mobileaccessoriesbackend.enums.OrderStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {

    private Long id;
    private Date orderDate;
    private String description;
    private String deliverAddress;
    private CustomerResponse customerId;
    private BranchResponse branchId;
    private OrderStatusType status;
    private SalesAgentResponse salesAgentId;
    private String saleAgentNote;
    private VehicleResponse vehicleId;
    private Date deliverDate;
    private PaymentResponse paymentId;
}
