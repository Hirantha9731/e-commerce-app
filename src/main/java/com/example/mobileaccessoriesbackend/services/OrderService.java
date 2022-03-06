package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.request.OrderRequest;
import com.example.mobileaccessoriesbackend.dto.response.*;
import com.example.mobileaccessoriesbackend.entity.Order;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.OrderRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;
    private ICustomerService customerService;
    private IBranchService branchService;
    private ISalesAgentService salesAgentService;
    private IVehicleService vehicleService;
    private IPaymentService paymentService;

    public OrderService(
            OrderRepository orderRepository,
            ICustomerService customerService,
            IBranchService branchService,
            ISalesAgentService salesAgentService,
            IVehicleService vehicleService,
            IPaymentService paymentService)
    {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.branchService = branchService;
        this.salesAgentService = salesAgentService;
        this.vehicleService = vehicleService;
        this.paymentService = paymentService;
    }

    /**
     * Get All Orders
     * @return
     */
    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::response).collect(Collectors.toList());
    }

    /**
     * Create Order
     * @param orderRequest
     * @return
     */
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setId(orderRequest.getId());
        order.setOrderDate(orderRequest.getOrderDate());
        order.setDescription(orderRequest.getDescription());
        order.setDeliverAddress(orderRequest.getDeliverAddress());
        order.setCustomerId(customerService.getCustomerById(orderRequest.getCustomerId()));
        order.setBranchId(branchService.findById(orderRequest.getBranchId()));
        order.setStatus(orderRequest.getStatus());
        order.setSalesAgentId(salesAgentService.findSalesAgentById(orderRequest.getSalesAgentId()));
        order.setSaleAgentNote(orderRequest.getSaleAgentNote());
        order.setVehicleId(vehicleService.findVehicleById(orderRequest.getVehicleId()));
        order.setDeliverDate(orderRequest.getDeliverDate());
        order.setPaymentId(paymentService.findPaymentById(orderRequest.getPaymentId()));

        // saving order
        Order response = orderRepository.save(order);

        return response(response);
    }

    /**
     * Find Order By Identifier
     * @param id
     * @return
     */
    @Override
    public Order findOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order not exist with id : "+id));

        return order;
    }

    /**
     * Get Orders By Customer Identifier
     * @param Id
     * @return
     */
    @Override
    public OrderResponse getOrdersByCustomerId(Long Id) {
        return null;
    }

    /**
     * Update Order Details
     * @param orderRequest
     * @return
     */
    @Override
    public OrderResponse updateOrderDetails(OrderRequest orderRequest) {
        if(orderRequest.getId() != null){
            this.findOrderById(orderRequest.getId());

            Order order = orderRepository.findById(orderRequest.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("Order not exist with id : "+ orderRequest.getId()));

            // setting details
            order.setId(orderRequest.getId());
            order.setOrderDate(orderRequest.getOrderDate());
            order.setDescription(orderRequest.getDescription());
            order.setDeliverAddress(orderRequest.getDeliverAddress());
            order.setCustomerId(customerService.getCustomerById(orderRequest.getCustomerId()));
            order.setBranchId(branchService.findById(orderRequest.getBranchId()));
            order.setStatus(orderRequest.getStatus());
            order.setSalesAgentId(salesAgentService.findSalesAgentById(orderRequest.getSalesAgentId()));
            order.setSaleAgentNote(orderRequest.getSaleAgentNote());
            order.setVehicleId(vehicleService.findVehicleById(orderRequest.getVehicleId()));
            order.setDeliverDate(orderRequest.getDeliverDate());
            order.setPaymentId(paymentService.findPaymentById(orderRequest.getPaymentId()));


            // saving order details
            Order response = orderRepository.save(order);

            return response(response);
        }
        else {
            throw new ResourceNotFoundException("Order Details not found");
        }
    }

    /**
     * Delete Order
     * @param id
     * @return
     */
    @Override
    public Boolean deleteOrder(Long id) {
        if (id != null) {
            Order order = orderRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id :" + id));
            orderRepository.delete(order);
        }
        else {
            throw new ResourceNotFoundException("Order not found");
        }
        return true;
    }


    /**
     * helper method
     * @param order
     * @return
     */
    public OrderResponse response(Order order) throws ArrayIndexOutOfBoundsException{
        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setOrderDate(order.getOrderDate());
        response.setDescription(order.getDescription());
        response.setDeliverAddress(order.getDeliverAddress());
        response.setCustomerId(new CustomerResponse(
                order.getCustomerId().getId(),
                order.getCustomerId().getName(),
                order.getCustomerId().getUsername(),
                order.getCustomerId().getEmail(),
                order.getCustomerId().getContactNo(),
                order.getCustomerId().getAddress(),
                order.getCustomerId().getCity()
        ));

        response.setBranchId(new BranchResponse(
              order.getBranchId().getId(),
              order.getBranchId().getBranchName(),
              order.getBranchId().getBranchLocation()
        ));

        response.setStatus(order.getStatus());

        SalesAgentResponse salesAgentResponse = new SalesAgentResponse();
        salesAgentResponse.setId(order.getSalesAgentId().getId());
        salesAgentResponse.setName(order.getSalesAgentId().getName());
        salesAgentResponse.setUsername(order.getSalesAgentId().getUsername());
        salesAgentResponse.setContactNo(order.getSalesAgentId().getContactNo());

        response.setSalesAgentId(salesAgentResponse);


        response.setSaleAgentNote(order.getSaleAgentNote());
        response.setVehicleId(new VehicleResponse(
                order.getVehicleId().getId(),
                order.getVehicleId().getVehicleType(),
                order.getVehicleId().getVehicleNumber(),
                order.getVehicleId().getVehicleStatus()
        ));
        response.setDeliverDate(order.getDeliverDate());
        response.setPaymentId(new PaymentResponse(
                order.getPaymentId().getId(),
                order.getPaymentId().getPaymentType(),
                order.getPaymentId().getPaymentDate()
        ));

        return response;
    }
}
