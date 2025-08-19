package com.example.productservice.controller;

import com.example.productservice.dto.products.CreateProductDto;
import com.example.productservice.dto.products.CreateProductRequestDto;
import com.example.productservice.dto.products.CreateProductResponseDto;
import com.example.productservice.dto.products.GetAllProductsResponseDto;
import com.example.productservice.dto.products.GetProductDto;
import com.example.productservice.dto.products.PatchProductResponseDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    private RestTemplate restTemplate;


    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
        this.restTemplate = this.restTemplate;
    }

    @GetMapping
    public GetAllProductsResponseDto getAllProducts() {
        List<Product> products = productService.getAllProducts();
        GetAllProductsResponseDto response = new GetAllProductsResponseDto();

        List<GetProductDto> getProductResponseDtos = new ArrayList<>();

        for (Product product : products) {
            getProductResponseDtos.add(GetProductDto.from(product));
        }

        response.setProducts(getProductResponseDtos);

        return response;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return "Here is your product: " + productId;
    }

    @PostMapping
    public CreateProductResponseDto createProduct(@RequestHeader("Authorization") String token, @RequestBody CreateProductRequestDto createProductRequestDto) {
        boolean isAuthenticated = restTemplate.getForObject(
                "http://userService/auth/validate?token=" + token,
                Boolean.class
        );

        if (!isAuthenticated) {
            return null;
        }

        Product product = productService.createProduct(
                createProductRequestDto.toProduct()
        );

        return CreateProductResponseDto.fromProduct(
                product
        );
    }

    @PatchMapping("/{id}")
    public PatchProductResponseDto updateProduct(
            @PathVariable("id") Long productId,
            @RequestBody CreateProductDto productDto
    ) throws ProductNotFoundException {
        Product product = productService.partialUpdateProduct(
                productId,
                productDto.toProduct()
        );

        PatchProductResponseDto response = new PatchProductResponseDto();
        response.setProduct(GetProductDto.from(product));

        return response;
    }

//    @PutMapping("/{id}")
//    public Product updateProduct(@PathVariable("id") Long id,
//                                 @RequestBody GenericProductDto genericProductDtoBody) {
//        return productService.updateProduct(id,
//                genericProductDtoBody.getTitle(),
//                genericProductDtoBody.getDescription(),
//                genericProductDtoBody.getImage(),
//                genericProductDtoBody.getCategory(),
//                genericProductDtoBody.getPrice());
//    }
//
//    @DeleteMapping("/{id}")
//    public GenericProductDto deleteProduct(@PathVariable("id") Long id) {
//        return productService.deleteProduct(id);
//    }
}
