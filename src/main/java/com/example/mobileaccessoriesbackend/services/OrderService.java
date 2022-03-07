package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.request.OrderConfirmRequest;
import com.example.mobileaccessoriesbackend.dto.request.OrderDetailRequest;
import com.example.mobileaccessoriesbackend.dto.request.OrderRequest;
import com.example.mobileaccessoriesbackend.dto.response.OrderDetailResponse;
import com.example.mobileaccessoriesbackend.dto.response.OrderResponse;
import com.example.mobileaccessoriesbackend.entity.*;
import com.example.mobileaccessoriesbackend.enums.OrderStatusType;
import com.example.mobileaccessoriesbackend.exceptions.InvalidArgumentException;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.OrderDetailRepository;
import com.example.mobileaccessoriesbackend.repository.OrderRepository;
import com.example.mobileaccessoriesbackend.repository.PaymentRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final ICustomerService customerService;
    private final IBranchService branchService;
    private final ISalesAgentService salesAgentService;
    private final IVehicleService vehicleService;
    private final IPaymentService paymentService;
    private final IProductService productService;
    private final OrderDetailRepository orderDetailRepository;
    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final IDriverService driverService;

    public OrderService(
            OrderRepository orderRepository,
            ICustomerService customerService,
            IBranchService branchService,
            ISalesAgentService salesAgentService,
            IVehicleService vehicleService,
            IPaymentService paymentService,
            IProductService productService,
            OrderDetailRepository orderDetailRepository,
            PaymentRepository paymentRepository,
            ModelMapper modelMapper,
            IDriverService driverService)
    {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.branchService = branchService;
        this.salesAgentService = salesAgentService;
        this.vehicleService = vehicleService;
        this.paymentService = paymentService;
        this.productService = productService;
        this.orderDetailRepository = orderDetailRepository;
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
        this.driverService = driverService;
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
    public Long createOrder(OrderRequest orderRequest) {
        Order order = new Order();

        order.setOrderDate(LocalDate.now());
        order.setDescription(orderRequest.getDescription());
        order.setDeliverAddress(orderRequest.getDeliverAddress());
        order.setCustomer(customerService.getCustomerById(orderRequest.getCustomerId()));
        order.setBranch(branchService.findById(orderRequest.getBranchId()));
        order.setStatus(OrderStatusType.PENDING);

        Order save = orderRepository.save(order);

        double totalPrice= 0;

        for (OrderDetailRequest request :
                orderRequest.getRequestList()) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productService.findProductById(request.getProductId());
            orderDetail.setOrder(save);
            orderDetail.setProduct(product);
            orderDetail.setProductQt(request.getProductQt());

            totalPrice += product.getSellingPrice();

            orderDetailRepository.save(orderDetail);
        }

        Payment payment = new Payment();
        payment.setDate(LocalDate.now());
        payment.setTotal(totalPrice);
        payment.setPaymentType(orderRequest.getPaymentType());

        paymentRepository.save(payment);

        return save.getId();
    }

    /**
     * Find Order By Identifier
     * @param id
     * @return
     */
    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order not exist with id : "+id));
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
        return null;
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

    @Override
    public List<OrderResponse> findByStatus(OrderStatusType statusType) {
        List<Order> orders = orderRepository.findByStatus(statusType);
        return orders.stream().map(this::response).collect(Collectors.toList());
    }

    @Override
    public void confirmOrder(OrderConfirmRequest orderConfirmRequest) {
        if (orderConfirmRequest == null)
            throw new InvalidArgumentException("Confirmation request not found");

        Order order = findOrderById(orderConfirmRequest.getOrderId());
        order.setSalesAgent(salesAgentService.findSalesAgentById(orderConfirmRequest.getSalesAgentId()));
        order.setSaleAgentNote(orderConfirmRequest.getSaleAgentNote());
        order.setDriver(driverService.findById(orderConfirmRequest.getDriverId()));
        order.setVehicle(vehicleService.findVehicleById(orderConfirmRequest.getVehicleId()));
        order.setDeliverDate(LocalDate.now());
    }


    /**
     * helper method
     * @param order
     * @return
     */
    public OrderResponse response(Order order){
        OrderResponse orderResponse = modelMapper.map(order, OrderResponse.class);

        List<OrderDetailResponse> responseList = new ArrayList<>();
        order.getOrderDetails().forEach(orderDetail -> {
            OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
            orderDetailResponse.setProductId(orderDetail.getProduct().getProductId());
            orderDetailResponse.setProductName(orderDetail.getProduct().getProductName());
            orderDetailResponse.setDescription(orderDetail.getProduct().getDescription());
            orderDetailResponse.setSellingPrice(orderDetail.getProduct().getSellingPrice());
            orderDetailResponse.setImgUrl(productService.saveFile(orderDetail.getProduct().getImgUrl()));
            orderDetailResponse.setQty(orderDetail.getProductQt());

            responseList.add(orderDetailResponse);
        });
        orderResponse.setOrderDetailResponse(responseList);
        return orderResponse;
    }
}
