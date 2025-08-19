package com.example.productservice.service.filteringservice;

import com.example.productservice.model.Product;

import java.util.List;

public class RamFilter implements Filter {

    @Override
    public List<Product> apply(List<Product> products, List<String> filters) {
        return List.of();
    }
}
