package com.example.mobileaccessoriesbackend.services.interfaces;


import com.example.mobileaccessoriesbackend.dto.request.SalesAgentRequest;
import com.example.mobileaccessoriesbackend.dto.response.SalesAgentResponse;
import com.example.mobileaccessoriesbackend.entity.SalesAgent;


import java.util.List;

public interface ISalesAgentService {


    List<SalesAgentResponse> getAllSalesAgents();

    SalesAgentResponse createSalesAgent(SalesAgentRequest salesAgent);

    SalesAgent findSalesAgentById(Long id);

    SalesAgentResponse updateSalesAgent(SalesAgentRequest salesAgentRequest);

    void deleteSalesAgent(Long id);

    SalesAgent findByUserName(String username);


}
