package com.example.productservice.service;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Category toBePutInProduct = getCategoryToBeInProduct(product);

        product.setCategory(toBePutInProduct);

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) throws ProductNotFoundException {
        Optional<Product> productToUpdateOptional = productRepository.findById(productId);

        if (productToUpdateOptional.isEmpty()) {
            throw new ProductNotFoundException("Product with ID " + productId + " not found.");
        }

        Product productToUpdate = productToUpdateOptional.get();

        if (product.getDescription() != null) {
            productToUpdate.setDescription(product.getDescription());
        }

        if (product.getPrice() != null) {
            productToUpdate.setPrice(product.getPrice());
        }

        if (product.getTitle() != null) {
            productToUpdate.setTitle(product.getTitle());
        }

        if (product.getCategory() != null) {
            Category toBePutInProduct = getCategoryToBeInProduct(product);

            productToUpdate.setCategory(toBePutInProduct);
        }

        return productRepository.save(productToUpdate);
    }

    private Category getCategoryToBeInProduct(Product product) {
        String categoryName = product.getCategory().getName();

        Optional<Category> category =
                categoryRepository.findByName(categoryName);
        Category toBePutInProduct = null;

        if (category.isEmpty()) {
            Category toSaveCategory = new Category();
            toSaveCategory.setName(categoryName);

            toBePutInProduct = toSaveCategory;
        } else {
            toBePutInProduct = category.get();
        }
        return toBePutInProduct;
    }
}
