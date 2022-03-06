package com.example.mobileaccessoriesbackend.controller;


import com.example.mobileaccessoriesbackend.dto.request.OrderRequest;
import com.example.mobileaccessoriesbackend.dto.request.ProductRequest;
import com.example.mobileaccessoriesbackend.dto.response.OrderResponse;
import com.example.mobileaccessoriesbackend.dto.response.ProductResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.services.interfaces.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Orders fetch successful",
                orders));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderRequest orderRequest){

        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "Order placed successfully",
                orderResponse
        ));
    }


}
