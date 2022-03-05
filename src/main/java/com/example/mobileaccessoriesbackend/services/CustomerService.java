package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.request.CustomerRequest;
import com.example.mobileaccessoriesbackend.dto.response.CustomerResponse;
import com.example.mobileaccessoriesbackend.dto.response.UserResponse;
import com.example.mobileaccessoriesbackend.entity.Customer;
import com.example.mobileaccessoriesbackend.entity.User;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.CustomerRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    /**
     * Customer Repository
     */
    private CustomerRepository customerRepository;

    /**
     * Add Customer
     * @param customerRequest
     * @return
     */
    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        Customer customer = new Customer();

        customer.setId(customerRequest.getId());
        customer.setUsername(customerRequest.getUsername());
        customer.setName(customerRequest.getName());
        customer.setEmail(customerRequest.getEmail());
        customer.setContactNo(customerRequest.getContactNo());
        customer.setAddress(customerRequest.getAddress());
        customer.setCity(customerRequest.getCity());

        Customer response = customerRepository.save(customer);

        return new CustomerResponse(
                response.getId(),
                response.getUsername(),
                response.getName(),
                response.getEmail(),
                response.getContactNo(),
                response.getAddress(),
                response.getCity()
        );
    }

    /**
     * Get All Customers
     * @return
     */
    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::response).collect(Collectors.toList());
    }

    /**
     * Get Customer By Id
     * @param id
     * @return
     */
    @Override
    public Customer getCustomerById(Long id) {
        return  customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Customer not exist with id : "+ id));
    }

    /**
     * Update Customer Details
     * @param customerRequest
     * @return
     */
    @Override
    public CustomerResponse updateCustomerDetails(CustomerRequest customerRequest) {
        if(customerRequest.getId() != null){
            this.getCustomerById(customerRequest.getId());

            Customer customer = customerRepository.findById(customerRequest.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("Customer not exist with id :" + customerRequest.getId()));

            customer.setId(customerRequest.getId());
            customer.setUsername(customerRequest.getUsername());
            customer.setName(customerRequest.getName());
            customer.setEmail(customerRequest.getEmail());
            customer.setContactNo(customerRequest.getContactNo());
            customer.setAddress(customerRequest.getAddress());
            customer.setCity(customerRequest.getCity());

            Customer response = customerRepository.save(customer);
            return new CustomerResponse(
                    response.getId(),
                    response.getUsername(),
                    response.getName(),
                    response.getEmail(),
                    response.getContactNo(),
                    response.getAddress(),
                    response.getCity()
            );
        }
        else {
            throw new ResourceNotFoundException("Customer not found");
        }
    }

    /**
     * Delete Customer
     * @param id
     * @return
     */
    @Override
    public Boolean deleteCustomer(Long id) {
        if(id != null){
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));

            customerRepository.delete(customer);
        }
        else {
            throw new ResourceNotFoundException("Customer not found");
        }
        return true;
    }

    /**
     * Helper method
     * @param customer
     * @return
     */
    public CustomerResponse response(Customer customer){
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setUsername(customer.getUsername());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setContactNo(customer.getContactNo());
        response.setAddress(customer.getAddress());
        response.setCity(customer.getCity());
        return response;
    }
}
