package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.request.ReStockRequest;
import com.example.mobileaccessoriesbackend.dto.response.ProductResponse;
import com.example.mobileaccessoriesbackend.dto.response.ReStockResponse;
import com.example.mobileaccessoriesbackend.dto.response.SalesAgentResponse;
import com.example.mobileaccessoriesbackend.entity.ReStock;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.ReStockRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IProductService;
import com.example.mobileaccessoriesbackend.services.interfaces.IReStockRequestService;
import com.example.mobileaccessoriesbackend.services.interfaces.ISalesAgentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReStockRequestService implements IReStockRequestService {

    private ReStockRepository reStockRepository;
    private IProductService productService;
    private ISalesAgentService salesAgentService;

    /**
     * ReStockRequestService
     * @param reStockRepository
     * @param productService
     * @param salesAgentService
     */
    public ReStockRequestService(
            ReStockRepository reStockRepository,
            IProductService productService,
            ISalesAgentService salesAgentService
    ) {
        this.reStockRepository = reStockRepository;
        this.productService = productService;
        this.salesAgentService = salesAgentService;
    }

    /**
     * Get All Requests
     * @return
     */
    @Override
    public List<ReStockResponse> getAllRequests() {
        List<ReStock> requests = reStockRepository.findAll();
        return requests.stream().map(this::response).collect(Collectors.toList());
    }

    /**
     * Create ReStock Request
     * @param reStockRequest
     * @return
     */
    @Override
    public ReStockResponse createReStockRequest(ReStockRequest reStockRequest) {

        // Setting values
        ReStock reStock = new ReStock();
        reStock.setId(reStockRequest.getId());
        reStock.setDescription(reStockRequest.getDescription());
        reStock.setProductId(productService.findProductById(reStockRequest.getProductId()));
        reStock.setSalesAgentId(salesAgentService.findSalesAgentById(reStockRequest.getSalesAgentId()));

        ReStock response = reStockRepository.save(reStock);
        return response(response);

    }

    /**
     * Find Request By Identifier
     * @param id
     * @return
     */
    @Override
    public ReStock findRequestById(Long id) {
        ReStock reStock = reStockRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Request details not exist with id : "+ id));

        return reStock;
    }

    /**
     * Update ReStock Request
     * @param reStockRequest
     * @return
     */
    @Override
    public ReStockResponse updateReStockRequest(ReStockRequest reStockRequest) {
        if(reStockRequest.getId() != null){
            ReStock reStock = reStockRepository.findById(reStockRequest.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("Request details not exist with id : "+ reStockRequest.getId()));

            // Setting values
            reStock.setId(reStockRequest.getId());
            reStock.setDescription(reStockRequest.getDescription());
            reStock.setProductId(productService.findProductById(reStockRequest.getProductId()));
            reStock.setSalesAgentId(salesAgentService.findSalesAgentById(reStockRequest.getSalesAgentId()));

            ReStock response = reStockRepository.save(reStock);
            return response(response);
        }
        else {
            throw new ResourceNotFoundException("Request Details not found");
        }
    }

    /**
     * Delete ReStock Request
     * @param id
     * @return
     */
    @Override
    public Boolean deleteReStockRequest(Long id) {

        if (id != null) {
            ReStock reStock = reStockRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Request Details not exist with id :" + id));
            reStockRepository.delete(reStock);
        }
        else {
            throw new ResourceNotFoundException("Request not found");
        }
        return true;

    }

    /**
     * response - Helper method
     * @param reStock
     * @return
     */
    private ReStockResponse response(ReStock reStock){

        ReStockResponse response = new ReStockResponse();
        response.setId(reStock.getId());
        response.setDescription(reStock.getDescription());

        //Product Response details

        ProductResponse prodResponse = new ProductResponse();
        prodResponse.setProductId(reStock.getProductId().getProductId());
        prodResponse.setProductName(reStock.getProductId().getProductName());
        prodResponse.setSellingPrice(reStock.getProductId().getSellingPrice());
        byte[] urls = productService.saveFile(reStock.getProductId().getImgUrl());
        prodResponse.setImgUrl(urls);
        response.setProductId(prodResponse);

        // sales agent details
        SalesAgentResponse salesAgentResponse = new SalesAgentResponse();
        salesAgentResponse.setId(reStock.getSalesAgentId().getId());
        salesAgentResponse.setName(reStock.getSalesAgentId().getName());
        salesAgentResponse.setContactNo(reStock.getSalesAgentId().getContactNo());
        response.setSalesAgentId(salesAgentResponse);

        return  response;
    }
}
