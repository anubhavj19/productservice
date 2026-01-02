package com.example.productservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private String image;
}

/*
Cardinality
Product Category => M : 1
Product belongs to one Category
Category can have multiple Products
 */

/*

1:1 -> id of one side on other side.
1:M -> id of one side on many side.
M:1 -> id of one side on many side.
M:M -> Mapping table.

 */
