package com.example.productservice.service.sortingservice;

import com.example.productservice.model.Product;

import java.util.List;

public interface Sorter {

    public List<Product> sort(List<Product> products);
}
