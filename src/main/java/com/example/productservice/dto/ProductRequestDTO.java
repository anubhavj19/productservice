package com.example.productservice.dto;

import com.example.productservice.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
}
/*
POST /products
{
    title:
    description:
    image:
    category:
    price:
    token:
}

In your API logic, you will create the actual product
Product p = new Product(); = save in your db as well = add a row in your product table
 */
