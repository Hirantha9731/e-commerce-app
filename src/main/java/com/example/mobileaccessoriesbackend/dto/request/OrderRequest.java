package com.example.mobileaccessoriesbackend.dto.request;


import com.example.mobileaccessoriesbackend.enums.OrderStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {

    private Long id;
    private Date orderDate;
    private String description;
    private String deliverAddress;
    private Long customerId;
    private Long branchId;
    private OrderStatusType status;
    private Long salesAgentId;
    private String saleAgentNote;
    private Long vehicleId;
    private Date deliverDate;
    private Long paymentId;

}
