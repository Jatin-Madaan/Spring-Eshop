package com.saggu.eshop.controller;

import com.saggu.eshop.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void givenProducts_GetProductsEndpoint_ShouldReturnProductList(){
        String baseUrl = "http://localhost:"+  port + "/products";
        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(baseUrl, ProductDto[].class);
        assert(response.getStatusCode()).equals(HttpStatus.OK);
        assert(response.getBody().length >= 2);
    }

    @Test
    void givenANewProducts_PostProductsEndpoint_ShouldAddANewProduct(){
        String baseUrl = "http://localhost:"+  port + "/products";
        ProductDto productSamsung = ProductDto.builder().productId("P1500").name("Samsung 4k TV 75").price(5049.99).description("Samsung OLED 4k Smart TV").build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity(baseUrl, productSamsung, ProductDto.class);
        assert(response.getStatusCode()).equals(HttpStatus.CREATED);
        ProductDto newDto = response.getBody();
        assert(newDto != null);
        assert(productSamsung.getName().equals(newDto.getName()));
    }
}