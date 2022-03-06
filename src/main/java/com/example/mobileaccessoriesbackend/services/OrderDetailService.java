package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.request.OrderDetailRequest;
import com.example.mobileaccessoriesbackend.dto.response.*;
import com.example.mobileaccessoriesbackend.entity.OrderDetail;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.OrderDetailRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IOrderDetailService;
import com.example.mobileaccessoriesbackend.services.interfaces.IOrderService;
import com.example.mobileaccessoriesbackend.services.interfaces.IProductService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService implements IOrderDetailService {

    private OrderDetailRepository orderDetailRepository;

    private IProductService productService;
    private IOrderService orderService;


    public OrderDetailService(
            OrderDetailRepository orderDetailRepository,
            IProductService productService,
            IOrderService orderService)
    {
        this.orderDetailRepository = orderDetailRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Override
    public List<OrderDetailResponse> getAllDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream().map(this::response).collect(Collectors.toList());
    }

    @Override
    public OrderDetailResponse saveDetails(OrderDetailRequest orderDetailRequest) {

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailRequest.getId());
        orderDetail.setOrderId(orderService.findOrderById(orderDetailRequest.getOrderId()));
        orderDetail.setProductId(productService.findProductById(orderDetailRequest.getProductId()));
        orderDetail.setProductQt(orderDetailRequest.getProductQt());

        // saving order details
        OrderDetail response = orderDetailRepository.save(orderDetail);

        return response(response);


    }

    /**
     * Get Order Details By Identifier
     * @param id
     * @return
     */
    @Override
    public OrderDetail getOrderDetailsById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Order details not exist with id : "+id));

        return orderDetail;
    }

    /**
     * Update Order Details
     * @param orderDetailRequest
     * @return
     */
    @Override
    public OrderDetailResponse updateDetails(OrderDetailRequest orderDetailRequest) {
        if(orderDetailRequest.getId() != null){
            this.getOrderDetailsById(orderDetailRequest.getId());

            OrderDetail orderDetail = orderDetailRepository.findById(orderDetailRequest.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("Order details not exist with id : "+ orderDetailRequest.getId()));

            orderDetail.setId(orderDetailRequest.getId());
            orderDetail.setOrderId(orderService.findOrderById(orderDetailRequest.getOrderId()));
            orderDetail.setProductId(productService.findProductById(orderDetailRequest.getProductId()));
            orderDetail.setProductQt(orderDetailRequest.getProductQt());

            // saving order details
            OrderDetail response = orderDetailRepository.save(orderDetail);

            return response(response);
        }
        else {
            throw new ResourceNotFoundException("Order Details not found");
        }
    }

    /**
     * Delete Order Details
     * @param id
     * @return
     */
    @Override
    public Boolean deleteOrderDetails(Long id) {
        if (id != null) {
            OrderDetail orderDetail = orderDetailRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order Details not exist with id :" + id));
            orderDetailRepository.delete(orderDetail);
        }
        else {
            throw new ResourceNotFoundException("Product not found");
        }
        return true;
    }

    /**
     * response - Helper method
     * @param detail
     * @return
     */
    private OrderDetailResponse response(OrderDetail detail){

        OrderDetailResponse response = new OrderDetailResponse();

        response.setId(detail.getId());

        // *** Order Response details starts here ***
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(detail.getOrderId().getId());
        orderResponse.setOrderDate(detail.getOrderId().getOrderDate());
        orderResponse.setDescription(detail.getOrderId().getDescription());
        orderResponse.setDeliverAddress(detail.getOrderId().getDeliverAddress());

        // customer details
        orderResponse.setCustomerId(new CustomerResponse(
                detail.getOrderId().getCustomerId().getId(),
                detail.getOrderId().getCustomerId().getName(),
                detail.getOrderId().getCustomerId().getUsername(),
                detail.getOrderId().getCustomerId().getEmail(),
                detail.getOrderId().getCustomerId().getContactNo(),
                detail.getOrderId().getCustomerId().getAddress(),
                detail.getOrderId().getCustomerId().getCity()
        ));
        // branch details
        orderResponse.setBranchId(new BranchResponse(
                detail.getOrderId().getBranchId().getId(),
                detail.getOrderId().getBranchId().getBranchName(),
                detail.getOrderId().getBranchId().getBranchLocation()

        ));
        orderResponse.setStatus(detail.getOrderId().getStatus());

        // sales agent details
        SalesAgentResponse salesAgentResponse = new SalesAgentResponse();
        salesAgentResponse.setId(detail.getOrderId().getSalesAgentId().getId());
        salesAgentResponse.setName(detail.getOrderId().getSalesAgentId().getName());
        salesAgentResponse.setContactNo(detail.getOrderId().getSalesAgentId().getContactNo());
        orderResponse.setSalesAgentId(salesAgentResponse);

        orderResponse.setSaleAgentNote(detail.getOrderId().getSaleAgentNote());

        // vehicle details
        orderResponse.setVehicleId(new VehicleResponse(
                detail.getOrderId().getVehicleId().getId(),
                detail.getOrderId().getVehicleId().getVehicleNumber(),
                detail.getOrderId().getVehicleId().getVehicleType(),
                detail.getOrderId().getVehicleId().getVehicleStatus()
        ));

        orderResponse.setDeliverDate(detail.getOrderId().getDeliverDate());

        // payment details
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setId(detail.getOrderId().getPaymentId().getId());
        paymentResponse.setPaymentDate(detail.getOrderId().getPaymentId().getPaymentDate());
        paymentResponse.setPaymentType(detail.getOrderId().getPaymentId().getPaymentType());
        orderResponse.setPaymentId(paymentResponse);

        response.setOrderId(orderResponse);
        // *** Order Response details ends here ***


        // *** Product Response details starts here ***

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductId(detail.getProductId().getProductId());
        productResponse.setProductName(detail.getProductId().getProductName());
        productResponse.setSellingPrice(detail.getProductId().getSellingPrice());

        byte[] urls = productService.saveFile(detail.getProductId().getImgUrl());
        productResponse.setImgUrl(urls);

        // getting supplier details for the product response
        SupplierResponse supplierResponse = new SupplierResponse();
        supplierResponse.setSupplierId(detail.getProductId().getSupplierId().getSupplierId());
        supplierResponse.setSupplierName(detail.getProductId().getSupplierId().getSupplierName());
        supplierResponse.setBrandName(detail.getProductId().getSupplierId().getBrandName());
        supplierResponse.setContactNo(detail.getProductId().getSupplierId().getContactNo());
        supplierResponse.setAddress(detail.getProductId().getSupplierId().getAddress());
        productResponse.setSupplierId(supplierResponse);

        response.setProductId(productResponse);

        // *** Product Response details ends here ***

        response.setProductQt(detail.getProductQt());


        return response;

    }

}
