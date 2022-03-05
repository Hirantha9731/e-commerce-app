package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.request.SalesAgentRequest;
import com.example.mobileaccessoriesbackend.dto.response.BranchResponse;
import com.example.mobileaccessoriesbackend.dto.response.SalesAgentResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.entity.SalesAgent;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.SalesAgentRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IBranchService;
import com.example.mobileaccessoriesbackend.services.interfaces.ISalesAgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class SalesAgentService implements ISalesAgentService {

    private final SalesAgentRepository salesAgentRepository;
    private final IBranchService branchService;

    public SalesAgentService(SalesAgentRepository salesAgentRepository,
                             IBranchService branchService) {
        this.salesAgentRepository = salesAgentRepository;
        this.branchService = branchService;
    }


    @Override
    public ResponseEntity<?> getAllSalesAgents() {
        List<SalesAgent> agents = salesAgentRepository.findAll();
        List<SalesAgentResponse> agentResponses  = agents.stream().map(this::response).collect(Collectors.toList());
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "sales agents found",
                agentResponses
        ));
    }

    /**
     *
     * @param salesAgentRequest
     * @return
     */
    @Override
    public ResponseEntity<?> createSalesAgent(SalesAgentRequest salesAgentRequest) {
        SalesAgent salesAgent1 = new SalesAgent();

        salesAgent1.setName(salesAgentRequest.getName());
        salesAgent1.setUsername(salesAgentRequest.getUsername());
        salesAgent1.setContactNo(salesAgentRequest.getContactNo());
        salesAgent1.setBranchId(branchService.findById(salesAgentRequest.getBranchId()));

        SalesAgent response = salesAgentRepository.save(salesAgent1);


        SalesAgentResponse salesAgentResponse = new SalesAgentResponse(
                response.getId(),
                response.getName(),
                response.getUsername(),
                response.getContactNo(),
                new BranchResponse(
                        response.getBranchId().getId(),
                        response.getBranchId().getBranchName() ,
                        response.getBranchId().getBranchLocation()));

        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "Sales Agent Successfully saved",
                salesAgentResponse
        ));

    }

    @Override
    public ResponseEntity<?> findSalesAgentById(Long id) {
        SalesAgent salesAgent = salesAgentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Sales Agent not exist with id :" + id));

        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "Sales Agent Found",
                response(salesAgent)
        ));
    }


    public SalesAgentResponse response(SalesAgent salesAgent){

        SalesAgentResponse response = new SalesAgentResponse();

        response.setId(salesAgent.getId());
        response.setName(salesAgent.getName());
        response.setUsername(salesAgent.getUsername());
        response.setContactNo(salesAgent.getContactNo());
        response.setBranchId(new BranchResponse(
                salesAgent.getBranchId().getId(),
                salesAgent.getBranchId().getBranchName(),
                salesAgent.getBranchId().getBranchLocation()));

        return response;
    }

}
