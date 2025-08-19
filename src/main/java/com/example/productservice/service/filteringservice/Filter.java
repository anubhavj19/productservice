package com.example.productservice.service.filteringservice;

import com.example.productservice.model.Product;

import java.util.List;

public interface Filter {

    public List<Product> apply(List<Product> products, List<String> filters);
}
