package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.dto.request.OrderDetailRequest;
import com.example.mobileaccessoriesbackend.dto.response.OrderDetailResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.entity.OrderDetail;
import com.example.mobileaccessoriesbackend.services.interfaces.IOrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orderDetails")
@CrossOrigin
public class OrderDetailsController {

    private IOrderDetailService orderDetailService;

    public OrderDetailsController(IOrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<OrderDetailResponse> orderDetailResponseList = orderDetailService.getAllDetails();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Order details fetch successful",
                orderDetailResponseList));
    }

    @PostMapping
    public ResponseEntity<?> saveOrderDetails(@RequestBody OrderDetailRequest productRequest){

        OrderDetailResponse response = orderDetailService.saveDetails(productRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "Order details saved successfully",
                response
        ));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetailsById(@PathVariable Long id){

        OrderDetail productResponse = orderDetailService.getOrderDetailsById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Order details fetch successful",
                productResponse
        ));
    }

    @PutMapping
    public ResponseEntity<?> updateOrderDetails(@RequestBody OrderDetailRequest orderDetailRequest){
        OrderDetailResponse productResponse = orderDetailService.updateDetails(orderDetailRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Order details updated successfully",
                productResponse
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderDetails(@PathVariable Long id){
        boolean response  = orderDetailService.deleteOrderDetails(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Order details deleted successfully",
                response
        ));
    }
}
