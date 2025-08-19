package com.example.productservice.service.filteringservice;

public class FilterFactory {

    public static Filter getFilterFromKey(String key) {
        return switch (key.toLowerCase()) {
            case "ram" -> new RamFilter();
            case "brand" -> new BrandFilter();
            default -> null;
        };
    }
}
