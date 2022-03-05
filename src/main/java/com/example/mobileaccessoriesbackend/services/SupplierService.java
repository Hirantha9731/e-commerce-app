package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.request.ProductRequest;
import com.example.mobileaccessoriesbackend.dto.request.SupplierRequest;
import com.example.mobileaccessoriesbackend.dto.response.SupplierResponse;
import com.example.mobileaccessoriesbackend.entity.Product;
import com.example.mobileaccessoriesbackend.entity.Supplier;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.SupplierRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.ISupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService implements ISupplierService {

    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierResponse> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers.stream().map(this::response).collect(Collectors.toList());
    }

    @Override
    public SupplierResponse addSupplier(SupplierRequest supplierRequest) {

        Supplier response = supplierRepository.save(supplierResponse(supplierRequest));

        return new SupplierResponse(
                response.getSupplierId(),
                response.getUsername(),
                response.getSupplierName(),
                response.getBrandName(),
                response.getContactNo(),
                response.getAddress()
        );

    }

    @Override
    public Supplier findSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Branch is not exist with id : " + id));

        return supplier;
    }

    @Override
    public SupplierResponse updateSupplierDetails(SupplierRequest supplierRequest) {
        if (supplierRequest.getSupplierId() != null){
            this.findSupplierById(supplierRequest.getSupplierId());

            Supplier supplier = supplierRepository.findById(supplierRequest.getSupplierId())
                    .orElseThrow(()-> new ResourceNotFoundException("Supplier not exist with id :" + supplierRequest.getSupplierId()));

            supplier.setSupplierId(supplierRequest.getSupplierId());
            supplier.setUsername(supplierRequest.getUsername());
            supplier.setSupplierName(supplierRequest.getSupplierName());
            supplier.setBrandName(supplierRequest.getBrandName());
            supplier.setContactNo(supplierRequest.getContactNo());
            supplier.setAddress(supplierRequest.getAddress());


            Supplier response = supplierRepository.save(supplier);

            return new SupplierResponse(

                    response.getSupplierId(),
                    response.getUsername(),
                    response.getSupplierName(),
                    response.getBrandName(),
                    response.getContactNo(),
                    response.getAddress()
            );

        }
        else {
            throw new ResourceNotFoundException("Supplier not found");
        }

    }

    @Override
    public Boolean deleteSupplier(Long id) {

        if (id != null) {

            Supplier supplier = supplierRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Supplier not exist with id :" + id));

            supplierRepository.delete(supplier);
        }
        else {
            throw new ResourceNotFoundException("Supplier not found");
        }
        return  true;
    }


    /**
     * Helper method response
     * @param supplier
     * @return
     */
    public SupplierResponse response(Supplier supplier){

        SupplierResponse response = new SupplierResponse();

        response.setSupplierId(supplier.getSupplierId());
        response.setUsername(supplier.getUsername());
        response.setSupplierName(supplier.getSupplierName());
        response.setBrandName(supplier.getBrandName());
        response.setContactNo(supplier.getContactNo());
        response.setAddress(supplier.getAddress());

        return response;
    }

    /**
     * Helper method response
     * @param supplierRequest
     * @return
     */

    public Supplier supplierResponse(SupplierRequest supplierRequest){

        Supplier supplier = new Supplier();

        supplier.setSupplierId(supplierRequest.getSupplierId());
        supplier.setUsername(supplierRequest.getUsername());
        supplier.setSupplierName(supplierRequest.getSupplierName());
        supplier.setBrandName(supplierRequest.getBrandName());
        supplier.setContactNo(supplierRequest.getContactNo());
        supplier.setAddress(supplierRequest.getAddress());

        return supplier;

    }


}
