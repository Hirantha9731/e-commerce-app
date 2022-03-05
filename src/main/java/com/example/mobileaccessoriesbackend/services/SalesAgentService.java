package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.request.SalesAgentRequest;
import com.example.mobileaccessoriesbackend.dto.response.BranchResponse;
import com.example.mobileaccessoriesbackend.dto.response.SalesAgentResponse;
import com.example.mobileaccessoriesbackend.entity.SalesAgent;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.SalesAgentRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IBranchService;
import com.example.mobileaccessoriesbackend.services.interfaces.ISalesAgentService;

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

    /**
     *
     * @return
     */
    @Override
    public List<SalesAgentResponse> getAllSalesAgents() {
        List<SalesAgent> agents = salesAgentRepository.findAll();
        return agents.stream().map(this::response).collect(Collectors.toList());

    }

    /**
     *
     * @param salesAgentRequest
     * @return
     */
    @Override
    public SalesAgentResponse createSalesAgent(SalesAgentRequest salesAgentRequest) {
        SalesAgent salesAgent1 = new SalesAgent();

        salesAgent1.setName(salesAgentRequest.getName());
        salesAgent1.setUsername(salesAgentRequest.getUsername());
        salesAgent1.setContactNo(salesAgentRequest.getContactNo());
        salesAgent1.setBranchId(branchService.findById(salesAgentRequest.getBranchId()));

        SalesAgent response = salesAgentRepository.save(salesAgent1);


        return new SalesAgentResponse(
                response.getId(),
                response.getName(),
                response.getUsername(),
                response.getContactNo(),
                new BranchResponse(
                        response.getBranchId().getId(),
                        response.getBranchId().getBranchName() ,
                        response.getBranchId().getBranchLocation()));

    }

    @Override
    public SalesAgentResponse findSalesAgentById(Long id) {
        SalesAgent salesAgent = salesAgentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Sales Agent not exist with id :" + id));

        return response(salesAgent);

    }

    @Override
    public SalesAgentResponse updateSalesAgent(SalesAgentRequest salesAgentRequest) {
        if (salesAgentRequest.getId() != null){
            this.findSalesAgentById(salesAgentRequest.getId());

            SalesAgent salesAgent = salesAgentRepository.findById(salesAgentRequest.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("Sales Agent not exist with id :" + salesAgentRequest.getId()));

            salesAgent.setName(salesAgentRequest.getName());
            salesAgent.setUsername(salesAgentRequest.getUsername());
            salesAgent.setContactNo(salesAgentRequest.getContactNo());

            SalesAgent response = salesAgentRepository.save(salesAgent);

            return new SalesAgentResponse(
                    response.getId(),
                    response.getName(),
                    response.getUsername(),
                    response.getContactNo(),
                    new BranchResponse(
                            response.getBranchId().getId(),
                            response.getBranchId().getBranchName() ,
                            response.getBranchId().getBranchLocation()));
        }
        else {
            throw new ResourceNotFoundException("Sales Agent not found");
        }
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

