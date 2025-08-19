package com.example.productservice.service;

import com.example.productservice.dto.search.FilterDto;
import com.example.productservice.dto.search.SortingCriteria;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.filteringservice.FilterFactory;
import com.example.productservice.service.sortingservice.SorterFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchService {

    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(String query,
                                List<FilterDto> filters,
                                SortingCriteria sortingCriteria,
                                int pageNumber,
                                int pageSize) {

        List<Product> products = productRepository.findByTitleContaining(query);

        // apply filters
        for (FilterDto filterDto : filters) {
            products = FilterFactory.getFilterFromKey(filterDto.getKey()).apply(products, filterDto.getValues());
        }

        // apply sorting criteria
        products = SorterFactory.getSorterFromCriteria(sortingCriteria).sort(products);

        List<Product> productsOnPage = new ArrayList<>();

        for (int i = pageSize * (pageNumber - 1); i <= (pageNumber * pageSize) - 1; i++) {
            productsOnPage.add(products.get(i));
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return new PageImpl<>(productsOnPage, pageable, products.size());
    }
}
