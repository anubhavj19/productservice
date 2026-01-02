package com.example.productservice.service;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("selfproductservice")
//@Primary
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private RestTemplate restTemplate;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {

        System.out.println("Calling getSingleProduct API.");

        return null;

//        ResponseEntity<UserDto> response = restTemplate.getForEntity(
//                "http://USERSERVICESEPT25MORNING/users/validate/" + "abc",
//                UserDto.class
//        );
//
//        Optional<Product> optionalProduct = productRepository.findById(id);
//
//        if (optionalProduct.isEmpty()){
//            throw new ProductNotFoundException("Product with id " + id + " not found");
//        }
//
//        return optionalProduct.get();
    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 double price,
                                 Category category,
                                 String image) {
        Product p = new Product();
        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        p.setImage(image);

        //first we should save the category
        if (category.getId() != null) {
            Optional<Category> categoryOptional = categoryRepository.findById(category.getId());

            if (categoryOptional.isEmpty()) {
                //throw InvalidCategoryException or create the category.
            }

            p.setCategory(categoryOptional.get());
        } else {
            Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());

            if (categoryOptional.isPresent()) {
                p.setCategory(categoryOptional.get());
            } else {
                Category c = new Category();
                c.setName(category.getName());
                c = categoryRepository.save(c);

                p.setCategory(c);
            }
        }

        return productRepository.save(p);

//        /*
//        1. This categoryName already exists in the db
//        Fetch the category from the db
//        2. This categoryName does not exist in the db
//        Create a new category with this name in the db
//         */
//        Category category = categoryRepository.findByName(categoryName);
//        if(category == null) {
//            //category does not exist
//            Category newCategory = new Category();
//            newCategory.setName(categoryName);
//            Category savedCategory = categoryRepository.save(newCategory);
//            p.setCategory(savedCategory);
//        }else{
//            //category exists
//            p.setCategory(category);
//        }
//        return productRepository.save(p);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize) {

        /*
        Page Size = 10
        Page Number = 7

        Limit = 10
        Offset = 61 (10 * 6 + 1)
         */

        Sort sort = Sort.by(Sort.Direction.ASC, "price")
                .and(Sort.by(Sort.Direction.ASC, "title"))
                .and(Sort.by(Sort.Direction.ASC, "id"));

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        return productRepository.findByTitleContainsIgnoreCase(title,  pageRequest);
    }
}


// Git fork
