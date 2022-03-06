package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.entity.Payment;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.PaymentRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public Payment findPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Payment not exist with id : "+id));

        return payment;
    }
}
