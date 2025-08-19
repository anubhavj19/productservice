package com.example.productservice.controller;

import com.example.productservice.dto.products.GetProductDto;
import com.example.productservice.dto.search.FilterDto;
import com.example.productservice.dto.search.SearchResponseDto;
import com.example.productservice.dto.search.SortingCriteria;
import com.example.productservice.model.Product;
import com.example.productservice.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public SearchResponseDto search(@RequestParam("query") String query,
                                    @RequestParam("filters") List<FilterDto> filters,
                                    @RequestParam("sortBy") SortingCriteria sortingCriteria,
                                    @RequestParam("pageNumber") int pageNumber,
                                    @RequestParam("pageSize") int pageSize) {

        Page<Product> productsPage = searchService.search(query, filters, sortingCriteria, pageNumber, pageSize);
        SearchResponseDto response = new SearchResponseDto();
        Page<GetProductDto> dtoPage = productsPage.map(GetProductDto::from);
        response.setProductsPage(dtoPage);

        return response;
    }
}
