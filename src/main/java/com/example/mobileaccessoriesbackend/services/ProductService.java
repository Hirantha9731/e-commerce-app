package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.request.ProductRequest;
import com.example.mobileaccessoriesbackend.dto.response.ProductResponse;
import com.example.mobileaccessoriesbackend.dto.response.SupplierResponse;
import com.example.mobileaccessoriesbackend.entity.Product;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.ProductRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IProductService;
import com.example.mobileaccessoriesbackend.services.interfaces.ISupplierService;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private ProductRepository productRepository;
    private ISupplierService supplierService;

    public ProductService(
            ProductRepository productRepository,
            ISupplierService supplierService)
    {
        this.productRepository = productRepository;
        this.supplierService = supplierService;
    }

    /**
     * Get all products
     * @return
     */
    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::response).collect(Collectors.toList());
    }

    /**
     * Add Product
     * @param productRequest
     * @return
     */
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = new Product();

        product.setProductName(productRequest.getProductName());
        product.setSellingPrice(productRequest.getSellingPrice());
        product.setImgUrl(uploadFile(productRequest.getImgUrl()));
        product.setSupplierId(supplierService.findSupplierById(productRequest.getSupplierId()));
        Product response = productRepository.save(product);

        return new ProductResponse(
                response.getProductId(),
                response.getProductName(),
                response.getSellingPrice(),
                saveFile(response.getImgUrl()),
                new SupplierResponse(
                        response.getSupplierId().getSupplierId(),
                        response.getSupplierId().getUsername(),
                        response.getSupplierId().getSupplierName(),
                        response.getSupplierId().getBrandName(),
                        response.getSupplierId().getContactNo(),
                        response.getSupplierId().getAddress()));
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Product findProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not exist with id : "+id));

        return product;
    }

    /**
     *
     * @param productRequest
     * @return
     */
    @Override
    public ProductResponse updateProductDetails(ProductRequest productRequest) {
        if(productRequest.getProductId() != null){
            this.findProductById(productRequest.getProductId());

            Product product = productRepository.findById(productRequest.getProductId())
                    .orElseThrow(()-> new ResourceNotFoundException("Product not exist with id :" + productRequest.getProductId()));

            product.setProductName(productRequest.getProductName());
            product.setSellingPrice(productRequest.getSellingPrice());
            product.setImgUrl(uploadFile(productRequest.getImgUrl()));
            product.setSupplierId(supplierService.findSupplierById(productRequest.getSupplierId()));

            Product response = productRepository.save(product);

            return new ProductResponse(
                    response.getProductId(),
                    response.getProductName(),
                    response.getSellingPrice(),
                    saveFile(response.getImgUrl()),
                    new SupplierResponse(
                            response.getSupplierId().getSupplierId(),
                            response.getSupplierId().getUsername(),
                            response.getSupplierId().getSupplierName(),
                            response.getSupplierId().getBrandName(),
                            response.getSupplierId().getContactNo(),
                            response.getSupplierId().getAddress()));
        }
        else {
            throw new ResourceNotFoundException("Product not found");
        }
    }

    /**
     * Delete Product
     * @param id
     */
    @Override
    public Boolean deleteProduct(Long id) {
        if (id != null) {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id :" + id));
            productRepository.delete(product);
        }
        else {
            throw new ResourceNotFoundException("Product not found");
        }
        return true;
    }

    /**
     *
     * @param file
     * @throws IllegalStateException
     */
    @Override
    public String uploadFile(MultipartFile file) throws IllegalStateException {
        try{
            file.transferTo(new File("E:\\SpringBoot-Projects\\Uploads\\" + file.getOriginalFilename()));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return "E:\\SpringBoot-Projects\\Uploads\\" + file.getOriginalFilename();

    }

    /**
     * helper method
     * @param product
     * @return
     */
    private ProductResponse response(Product product){
        ProductResponse response = new ProductResponse();

        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setSellingPrice(product.getSellingPrice());

        byte[] urls  = saveFile(product.getImgUrl());
        response.setImgUrl(urls);

        response.setSupplierId(new SupplierResponse(
                product.getSupplierId().getSupplierId(),
                product.getSupplierId().getUsername(),
                product.getSupplierId().getSupplierName(),
                product.getSupplierId().getBrandName(),
                product.getSupplierId().getContactNo(),
                product.getSupplierId().getAddress()
        ));

        return response;
    }

    /**
     * File saving
     * @param imgUrl
     * @return
     */
    public byte[] saveFile(String imgUrl){
        byte[] bytes = null;
        try{

            File file = new File(imgUrl);
            if(file.exists()){
                InputStream inputStream = new FileInputStream(file);
                bytes = StreamUtils.copyToByteArray(inputStream);
            }
        }
        catch (IOException e) {

            e.printStackTrace();

        }
        return bytes;
    }
}
