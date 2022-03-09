package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.dto.request.CustomerRequest;
import com.example.mobileaccessoriesbackend.dto.response.CustomerResponse;
import com.example.mobileaccessoriesbackend.entity.Customer;
import java.util.List;

public interface ICustomerService {

    CustomerResponse addCustomer(CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomers();

    Customer getCustomerById(Long id);

    CustomerResponse updateCustomerDetails(CustomerRequest customerRequest);

    Boolean deleteCustomer(Long id);

    Customer findByUserName(String username);
}
