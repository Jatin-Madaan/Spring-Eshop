package com.saggu.eshop.dao;

import com.saggu.eshop.dto.ProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

    static int id = 100;
    private final List<ProductDto> productList = new ArrayList<>();

    private String createAndGetId(){
        return "P"+(id++);
    }
    private final String prefix;

    public ProductDao(@Value("${products.prefix:}") String prefix) {
        this.prefix = prefix;
        productList.add(ProductDto.builder().productId(createAndGetId()).name(this.prefix + "Sony 4k TV 65").price(2049.99).description("Sony LED 4k Smart TV").build());
        productList.add(ProductDto.builder().productId(createAndGetId()).name(this.prefix + "Sony 4k TV 55").price(1049.99).description("Sony LCD 4k Smart TV").build());
    }

    public List<ProductDto> getProductList() {
        return productList;
    }

    public ProductDto addProduct(ProductDto productDto) {
        productDto.setProductId(createAndGetId());
        productList.add(productDto);
        return productDto;
    }
}
