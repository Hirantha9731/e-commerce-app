package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.dto.request.SalesAgentRequest;
import com.example.mobileaccessoriesbackend.services.interfaces.ISalesAgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/salesAgent")
@CrossOrigin
public class SalesAgentController {

    private ISalesAgentService salesAgentService;

    public SalesAgentController(ISalesAgentService salesAgentService) {
        this.salesAgentService = salesAgentService;
    }


    @GetMapping
    public ResponseEntity<?> getAll(){
        ResponseEntity<?> response = salesAgentService.getAllSalesAgents();
        return  response;
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody SalesAgentRequest salesAgentRequest){

        ResponseEntity<?> response = salesAgentService.createSalesAgent(salesAgentRequest);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSalesAgentById(@PathVariable Long id){

        ResponseEntity<?> response = salesAgentService.findSalesAgentById(id);
        return response;
    }

}
