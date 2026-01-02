package com.example.productservice.service;

import com.example.productservice.dto.FakeStoreProductDTO;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreprodservice")
@Primary
public class FakestoreProductService implements ProductService {
    /*
    To call external APIs, we can use something known as a Rest Template
     */
    private RestTemplate restTemplate ;
    private RedisTemplate<String, Object> redisTemplate;

    public FakestoreProductService(RestTemplate restTemplate,
                                   RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        //First check if the product exist in the redis with the given id.
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + id);

        if (product != null) {
            return product;
        }

        //We will inject the object instead of creating manually.
        // RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        FakeStoreProductDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class);

        if(response == null){
            //Isn't null response an exception / unexpected
            //Should we throw an exception here
            //Should we handle this exception in some sort
            throw new ProductNotFoundException("Product with id " + id + " not found");
        }

        product = response.toProduct();

        //Before returning this product, store in the Redis.
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + id, product);

        return product;
    }

    @Override
    public Product createProduct(String title, String description, double price, Category category, String image) {
        //https://fakestoreapi.com/products

//        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
//        fakeStoreProductDTO.setTitle(title);
//        fakeStoreProductDTO.setDescription(description);
//        fakeStoreProductDTO.setPrice(price);
//        fakeStoreProductDTO.setCategory(categoryName);
//        fakeStoreProductDTO.setImage(image);
//
//        FakeStoreProductDTO fakeStoreProductDTO1 = restTemplate.postForObject(
//                "https://fakestoreapi.com/products",
//                fakeStoreProductDTO,
//                FakeStoreProductDTO.class
//        );
//        return fakeStoreProductDTO1.toProduct();

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        //https://fakestoreapi.com/products

        FakeStoreProductDTO[] fakeStoreProductDTOS = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class);

        List<Product> products = new ArrayList<>();

        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOS){
            products.add(fakeStoreProductDTO.toProduct());
        }
        return products;
    }

    @Override
    public void deleteProduct(Long id) {
        return;
    }

    @Override
    public Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize) {
        return null;
    }

}
