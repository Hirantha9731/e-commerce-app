package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.dto.request.CustomerRequest;
import com.example.mobileaccessoriesbackend.dto.response.CustomerResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.entity.Customer;
import com.example.mobileaccessoriesbackend.services.interfaces.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    /**
     * Customer Service
     */
    private ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<CustomerResponse> customers = customerService.getAllCustomers();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Customers fetch successful",
                customers));
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "Customer saved successfully",
                customerResponse
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){

        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Customer fetch successful",
                customer
        ));
    }


    @PutMapping
    public ResponseEntity<?> updateUserDetails(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.updateCustomerDetails(customerRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Customer details updated successfully",
                customerResponse
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id){

        boolean response  = customerService.deleteCustomer(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Customer deleted successfully",
                response
        ));
    }

}
