package com.example.productservice.service;

import com.example.productservice.dto.fakestore.FakeStoreCreateProductRequestDto;
import com.example.productservice.dto.fakestore.FakeStoreGetProductResponseDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    public static final String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    public static final String PRODUCT_REQUEST_BASE_URL = "https://fakestoreapi" + ".com/products";

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {

        throw new RuntimeException();

//        FakeStoreGetProductResponseDto[] response = restTemplate.getForObject(
//            "https://fakestoreapi.com/products",
//            FakeStoreGetProductResponseDto[].class
//        );
//
//        List<FakeStoreGetProductResponseDto> responseDtoList =
//                Stream.of(response).toList();
//
//        List<Product> products = new ArrayList<>();
//
//        for (FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto: responseDtoList) {
//            products.add(fakeStoreGetProductResponseDto.toProduct());
//        }
//
//        return products;
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) throws ProductNotFoundException {
        return null;
    }

//    @Override
//    public Product getProductById(Long id) throws ProductNotFoundException {
//        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
//        FakeStoreProductDto fakeStoreProductDto = response.getBody();
//
//        if (fakeStoreProductDto == null) {
//            throw new ProductNotFoundException("Product with ID " + id + " not found.");
//        }
//
//        return fakeStoreProductDto.toProduct();
//    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
        request.setCategory(product.getCategory().getName());
        request.setTitle(product.getTitle());
        request.setImage(product.getImageUrl());
        request.setDescription(product.getDescription());
        request.setPrice(product.getPrice());

        FakeStoreGetProductResponseDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreGetProductResponseDto.class
        );


        // List<FakeStoreProductDto>

//         List<FakeStoreCreateProductResponseDto> response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                List<FakeStoreCreateProductResponseDto>.class
//        );

        return response.toProduct();
    }

//    @Override
//    public GenericProductDto deleteProduct(Long id) {
//        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
//        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
//        FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
//
//        return getGenericProductDto(fakeStoreProductDto);
//    }

//    private static GenericProductDto getGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
//        GenericProductDto updatedGenericProductDto = new GenericProductDto();
//        updatedGenericProductDto.setId(fakeStoreProductDto.getId());
//        updatedGenericProductDto.setTitle(fakeStoreProductDto.getTitle());
//        updatedGenericProductDto.setDescription(fakeStoreProductDto.getDescription());
//        updatedGenericProductDto.setImage(fakeStoreProductDto.getImage());
//        updatedGenericProductDto.setCategory(fakeStoreProductDto.getCategory());
//        updatedGenericProductDto.setPrice(fakeStoreProductDto.getPrice());
//
//        return updatedGenericProductDto;
//    }
}
