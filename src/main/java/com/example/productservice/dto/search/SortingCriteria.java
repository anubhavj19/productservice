package com.example.productservice.dto.search;

import lombok.Getter;
import lombok.Setter;

public enum SortingCriteria {
    PRICE_LOW_TO_HIGH,
    PRICE_HIGH_TO_LOW,
    RELEVANCE,
    POPULARITY,
    RATING_LOW_TO_HIGH,
    RATING_HIGH_TO_LOW
}
