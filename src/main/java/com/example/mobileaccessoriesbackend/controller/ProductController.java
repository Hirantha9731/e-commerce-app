package com.example.mobileaccessoriesbackend.controller;

import com.example.mobileaccessoriesbackend.dto.request.ProductRequest;
import com.example.mobileaccessoriesbackend.dto.response.ProductResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.entity.Product;
import com.example.mobileaccessoriesbackend.services.interfaces.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final IProductService productService;


    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<ProductResponse> productsList = productService.getAllProducts();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Products fetch successful",
                productsList));
    }


    @PostMapping
    public ResponseEntity<?> save(ProductRequest productRequest){

        ProductResponse productResponse = productService.addProduct(productRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "Product saved successfully",
                productResponse
        ));
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){

        Product productResponse = productService.findProductById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Product fetch successful",
                productResponse
        ));
    }


    @PutMapping
    public ResponseEntity<?> updateProductDetails(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.updateProductDetails(productRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Product details updated successfully",
                productResponse
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        boolean response  = productService.deleteProduct(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Product deleted successfully",
                response
        ));
    }

}
