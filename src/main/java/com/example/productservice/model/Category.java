package com.example.productservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "categories")
public class Category extends BaseModel {

    @Column(nullable = false, unique = true, name = "category_name")
    private String name;

    @Basic(fetch = FetchType.LAZY)
    private String description;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> featuredProducts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Fetch(FetchMode.SELECT)
    private List<Product> allProducts;

    @OneToOne(cascade = {})
    private Subcategory subcategories;

    private int countOfProducts;
}
