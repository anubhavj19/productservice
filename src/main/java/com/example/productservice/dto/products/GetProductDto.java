package com.example.productservice.dto.products;

import com.example.productservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;

    public static GetProductDto from(Product product) {
        GetProductDto getProductDto = new GetProductDto();
        getProductDto.setId(product.getId());
        getProductDto.setDescription(product.getDescription());
        getProductDto.setImageUrl(product.getImageUrl());
        getProductDto.setPrice(product.getPrice());
        getProductDto.setTitle(product.getTitle());

        return getProductDto;
    }
}
