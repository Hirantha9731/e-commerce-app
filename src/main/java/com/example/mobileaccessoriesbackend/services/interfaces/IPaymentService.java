package com.example.mobileaccessoriesbackend.services.interfaces;


import com.example.mobileaccessoriesbackend.entity.Payment;

public interface IPaymentService {

    Payment findPaymentById(Long id);
}
