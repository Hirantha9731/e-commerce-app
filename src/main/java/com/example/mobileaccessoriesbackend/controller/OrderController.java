package com.example.mobileaccessoriesbackend.controller;


import com.example.mobileaccessoriesbackend.dto.request.OrderConfirmRequest;
import com.example.mobileaccessoriesbackend.dto.request.OrderRequest;
import com.example.mobileaccessoriesbackend.dto.response.OrderResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.entity.Order;
import com.example.mobileaccessoriesbackend.enums.OrderStatusType;
import com.example.mobileaccessoriesbackend.services.interfaces.IOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

    private final IOrderService orderService;

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

        Long orderResponse = orderService.createOrder(orderRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "Order placed successfully",
                orderResponse
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){

        Order order = orderService.findOrderById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Order fetch successful",
                order
        ));
    }

    @PutMapping
    public ResponseEntity<?> updateProductDetails(@RequestBody OrderRequest productRequest){
        OrderResponse orderResponse = orderService.updateOrderDetails(productRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Order details updated successfully",
                orderResponse
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        boolean response  = orderService.deleteOrder(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Order deleted successfully",
                response
        ));
    }

    @GetMapping("/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable OrderStatusType status){

        List<OrderResponse> orders = orderService.findByStatus(status);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Order fetch successful",
                orders
        ));
    }

    @PutMapping("/confirm")
    public ResponseEntity<?> confirmOrder(@RequestBody OrderConfirmRequest orderConfirmRequest){
        orderService.confirmOrder(orderConfirmRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Order confirm successfully",
                true
        ));
    }


}
