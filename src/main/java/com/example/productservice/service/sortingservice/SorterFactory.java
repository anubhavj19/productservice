package com.example.productservice.service.sortingservice;

import com.example.productservice.dto.search.SortingCriteria;

public class SorterFactory {

    public static Sorter getSorterFromCriteria(SortingCriteria sortingCriteria) {
        return switch (sortingCriteria) {
            case PRICE_LOW_TO_HIGH -> new PriceLowToHighSorter();
            case PRICE_HIGH_TO_LOW -> new PriceHighToLowSorter();
            case RELEVANCE -> null;
            case POPULARITY -> null;
            default -> null;
        };
    }
}
