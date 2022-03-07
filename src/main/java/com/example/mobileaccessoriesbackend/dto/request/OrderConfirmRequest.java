package com.example.mobileaccessoriesbackend.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderConfirmRequest {
    @NotNull
    private Long orderId;
    @NotNull
    private Long salesAgentId;
    private String saleAgentNote;
    @NotNull
    private Long vehicleId;
    @NotNull
    private Long driverId;
}
