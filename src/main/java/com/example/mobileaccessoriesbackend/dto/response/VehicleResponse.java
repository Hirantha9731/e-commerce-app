package com.example.mobileaccessoriesbackend.dto.response;

import com.example.mobileaccessoriesbackend.enums.VehicleStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleResponse {

    private Long id;
    private String vehicleType;
    private String vehicleNumber;
    private VehicleStatusType vehicleStatus;

}
