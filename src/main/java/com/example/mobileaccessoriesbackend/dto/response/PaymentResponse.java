package com.example.mobileaccessoriesbackend.dto.response;

import com.example.mobileaccessoriesbackend.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentResponse {

    private Long id;
    private PaymentType paymentType;
    private Date paymentDate;
}
