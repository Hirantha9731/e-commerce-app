package com.example.mobileaccessoriesbackend.services.interfaces;


import com.example.mobileaccessoriesbackend.dto.request.SalesAgentRequest;
import com.example.mobileaccessoriesbackend.dto.response.SalesAgentResponse;


import java.util.List;

public interface ISalesAgentService {


    List<SalesAgentResponse> getAllSalesAgents();

    // create sales agent
    SalesAgentResponse createSalesAgent(SalesAgentRequest salesAgent);

    // get sales agent by id
    SalesAgentResponse findSalesAgentById(Long id);

    SalesAgentResponse updateSalesAgent(SalesAgentRequest salesAgentRequest);











}
