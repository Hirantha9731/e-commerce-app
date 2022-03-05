package com.example.mobileaccessoriesbackend.services.interfaces;


import com.example.mobileaccessoriesbackend.dto.request.SupplierRequest;
import com.example.mobileaccessoriesbackend.dto.response.SupplierResponse;
import com.example.mobileaccessoriesbackend.entity.Supplier;

import java.util.List;

public interface ISupplierService {

    List<SupplierResponse> getAllSuppliers();

    SupplierResponse addSupplier(SupplierRequest supplierRequest);

    Supplier findSupplierById(Long id);

    SupplierResponse updateSupplierDetails(SupplierRequest supplierDetails);

    Boolean deleteSupplier(Long id);
}
