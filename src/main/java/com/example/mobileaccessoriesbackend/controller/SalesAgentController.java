package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.dto.request.SalesAgentRequest;
import com.example.mobileaccessoriesbackend.dto.response.SalesAgentResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.entity.SalesAgent;
import com.example.mobileaccessoriesbackend.services.interfaces.ISalesAgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/salesAgent")
@CrossOrigin
public class SalesAgentController {

    private final ISalesAgentService salesAgentService;

    public SalesAgentController(ISalesAgentService salesAgentService) {
        this.salesAgentService = salesAgentService;
    }


    @GetMapping
    public ResponseEntity<?> getAll(){
        List<SalesAgentResponse> allSalesAgents = salesAgentService.getAllSalesAgents();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Sales agents fetch successful",
                allSalesAgents
        ));
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody SalesAgentRequest salesAgentRequest){

        SalesAgentResponse salesAgent = salesAgentService.createSalesAgent(salesAgentRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "Sales agent save successful",
                salesAgent
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSalesAgentById(@PathVariable Long id){

        SalesAgent saleAgent = salesAgentService.findSalesAgentById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Sales agent fetch successful",
                saleAgent
        ));
    }

    @PutMapping
    public ResponseEntity<?> updateSalesAgent(@RequestBody SalesAgentRequest salesAgentRequest){
        SalesAgentResponse salesAgent = salesAgentService.updateSalesAgent(salesAgentRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Sales agent details updated successfully",
                salesAgent
        ));
    }

}
