package com.example.productservice.service;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    Product createProduct(String title,
                          String description,
                          double price,
                          Category category,
                          String image);

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize);
}
