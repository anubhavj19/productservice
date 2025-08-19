package com.example.productservice.dto.products;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllProductsResponseDto {
    private List<GetProductDto> products;
}
