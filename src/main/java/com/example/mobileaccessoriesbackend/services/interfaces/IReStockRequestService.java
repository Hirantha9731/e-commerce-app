package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.dto.request.ReStockRequest;
import com.example.mobileaccessoriesbackend.dto.response.ReStockResponse;
import com.example.mobileaccessoriesbackend.entity.ReStock;

import java.util.List;

public interface IReStockRequestService {

    List<ReStockResponse> getAllRequests();

    ReStockResponse createReStockRequest(ReStockRequest reStockRequest);

    ReStock findRequestById(Long id);

    ReStockResponse updateReStockRequest(ReStockRequest reStockRequest);

    Boolean deleteReStockRequest(Long id);

}
