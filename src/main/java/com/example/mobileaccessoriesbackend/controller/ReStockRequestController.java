package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.dto.request.ReStockRequest;
import com.example.mobileaccessoriesbackend.dto.response.ReStockResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.entity.ReStock;
import com.example.mobileaccessoriesbackend.services.interfaces.IReStockRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@CrossOrigin
public class ReStockRequestController {

    private IReStockRequestService reStockRequestService;

    public ReStockRequestController(IReStockRequestService reStockRequestService) {
        this.reStockRequestService = reStockRequestService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<ReStockResponse> requests = reStockRequestService.getAllRequests();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "ReStock Requests fetch successful",
                requests));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReStockRequest reStockRequest){

        ReStockResponse request = reStockRequestService.createReStockRequest(reStockRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "ReStock Request added successfully",
                request
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReStockRequestById(@PathVariable Long id){

        ReStock reStock = reStockRequestService.findRequestById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "ReStock Request fetch successful",
                reStock
        ));
    }

    @PutMapping
    public ResponseEntity<?> updateReStockRequest(@RequestBody ReStockRequest reStockRequest){
        ReStockResponse response = reStockRequestService.updateReStockRequest(reStockRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "ReStock Request details updated successfully",
                response
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        boolean response  = reStockRequestService.deleteReStockRequest(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "ReStock Request deleted successfully",
                response
        ));
    }
}
