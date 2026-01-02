package com.example.productservice.controller;

import com.example.productservice.commons.AuthCommon;
import com.example.productservice.dto.ProductRequestDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;
    private AuthCommon authCommon;

    public ProductController(@Qualifier("selfproductservice") ProductService productService,
                             AuthCommon authCommon) {
        this.productService = productService;
        this.authCommon = authCommon;
    }
    //this is the class which is the entry point of requests

    /*
    Get product details - 1
    Get all products - 2
    Create product - 3
    Update product
    delete product
     */

    @GetMapping("/products/{id}")
    ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long id) throws ProductNotFoundException {
//        if (!authCommon.validateToken(tokenValue)) {
//            //Invalid Token.
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }

        //call the service layer
        Product product = productService.getSingleProduct(id); // @17632
//        product.setTitle("iphone 17 pro max");
//        product.setDescription("iphone 17 pro max");

        if(product == null){
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    List<Product> getAllProducts(){
        return productService.getAllProducts(); // @17787
    }

    @PostMapping("/products")
    Product createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        /*
        run some validations before calling the service
         */
        return productService.createProduct(
                productRequestDTO.getTitle(),
                productRequestDTO.getDescription(),
                productRequestDTO.getPrice(),
                productRequestDTO.getCategory(),
                productRequestDTO.getImage()
        );
    }
    /*
    We got an exception but we don't want to send the stack trace to the client
        1. We can handle the exception in the controller method itself
        2. We can have a global exception handler using @ControllerAdvice
        3. We can have a local exception handler using @ExceptionHandler
     */


    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws ProductNotFoundException {

    }

    @GetMapping("/products/{title}/{pageNumber}/{pageSize}")
    public Page<Product> getProductsByTitle(@PathVariable("title") String title,
                                            @PathVariable("pageNumber") int pageNumber,
                                            @PathVariable("pageSize") int pageSize) {

        return productService.getProductsByTitle(title, pageNumber, pageSize);

    }
}
