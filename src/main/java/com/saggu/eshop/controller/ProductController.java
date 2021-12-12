package com.saggu.eshop.controller;

import com.saggu.eshop.dao.ProductDao;
import com.saggu.eshop.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.List;

@RestController
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Operation(summary = "Get Products", description = "Get a list of products", tags = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product",
                content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProductDto.class)
                )}
            ),
            @ApiResponse(responseCode = "404", description = "Product not found",
                content = @Content
            )
    })
    @GetMapping(value = "/products", produces = APPLICATION_JSON_VALUE)
    public List<ProductDto> products(){
        return productDao.getProductList();
    }

    @PostMapping(value = "/products")
    public ResponseEntity<ProductDto> addProducts(@RequestBody ProductDto productDto){
        ProductDto createdDto = productDao.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDao.addProduct(productDto));
    }

}
