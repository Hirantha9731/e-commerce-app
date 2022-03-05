package com.example.mobileaccessoriesbackend.controller;


import com.example.mobileaccessoriesbackend.dto.request.SupplierRequest;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.dto.response.SupplierResponse;
import com.example.mobileaccessoriesbackend.entity.Supplier;
import com.example.mobileaccessoriesbackend.services.interfaces.ISupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/supplier")
@CrossOrigin
public class SupplierController {

    private ISupplierService supplierService;

    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<SupplierResponse> suppliersList = supplierService.getAllSuppliers();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Suppliers fetch successful",
                suppliersList));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody SupplierRequest supplierRequest){

        SupplierResponse supplierResponse = supplierService.addSupplier(supplierRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "Supplier saved successfully",
                supplierResponse
        ));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable Long id){

        Supplier supplier = supplierService.findSupplierById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Supplier fetch successful",
                supplier
        ));
    }

    @PutMapping
    public ResponseEntity<?> updateSupplierDetails(@RequestBody SupplierRequest supplierRequest){
        SupplierResponse supplierResponse = supplierService.updateSupplierDetails(supplierRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Supplier details updated successfully",
                supplierResponse
        ));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id){

        boolean response  = supplierService.deleteSupplier(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Supplier deleted successfully",
                response
        ));
    }
}
