package com.saggu.eshop.dao;

import com.saggu.eshop.dto.ProductDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

class ProductDaoTest {
    @Test
    void givenPrePopulatedData_getProducts_ShouldReturnAProductList(){
        ProductDao dao = new ProductDao("prod");
        assertThat(dao.getProductList().size()).isEqualTo(2);
    }

    @Test
    void givenANewProductDto_AddProducts_ShouldAddAndReturnNewProduct(){
        ProductDao dao = new ProductDao("prod");
        assertThat(dao.getProductList().size()).isEqualTo(2);
        ProductDto productSamsung = ProductDto.builder().productId("P1200").name("Sony 4k TV 55").price(1049.99).description("Sony LCD 4k Smart TV").build();

        ProductDto createdDto = dao.addProduct(productSamsung);
        assertThat(dao.getProductList().size()).isEqualTo(3);
    }
}