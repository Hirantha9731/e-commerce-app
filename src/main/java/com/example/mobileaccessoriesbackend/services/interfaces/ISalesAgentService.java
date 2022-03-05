package com.example.mobileaccessoriesbackend.services.interfaces;


import com.example.mobileaccessoriesbackend.dto.request.SalesAgentRequest;
import com.example.mobileaccessoriesbackend.entity.SalesAgent;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface ISalesAgentService {


    ResponseEntity<?> getAllSalesAgents();

    // create sales agent
    ResponseEntity<?> createSalesAgent(SalesAgentRequest salesAgent);

    // get sales agent by id
    ResponseEntity<?> findSalesAgentById(Long id);











}
