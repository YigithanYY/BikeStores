package com.bike.stores.dev.controller;

import com.bike.stores.dev.dto.ProductsDto;
import com.bike.stores.dev.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    private final ProductsService productsService;

    // Applying dependency injection using constructor injection
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    /**
     * Retrieves the product with the specified productId.
     *
     * @param productId Identifier of the product to retrieve
     * @return DTO with information of the specified product and HTTP Status 200 OK
     */
    @GetMapping("/{productId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<ProductsDto> getProductById(@PathVariable int productId) {
        return ResponseEntity.ok(productsService.getProductsById(productId));
    }

    /**
     * Retrieves all products.
     *
     * @return List of DTOs with information of all products and HTTP Status 200 OK
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<List<ProductsDto>> getAllProducts() {
        return ResponseEntity.ok(productsService.getAllProducts());
    }

    /**
     * Creates a new product.
     *
     * @param productsDto DTO containing information for the new product
     * @return DTO with information of the created product and HTTP Status 201 Created
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductsDto> createProducts(@RequestBody ProductsDto productsDto) {
        ProductsDto createdProducts = productsService.createProducts(productsDto);
        return new ResponseEntity<>(createdProducts, HttpStatus.CREATED);
    }

    /**
     * Updates the product with the specified productId.
     *
     * @param productId   Identifier of the product to update
     * @param productsDto DTO containing the new information
     * @return DTO with information of the updated product and HTTP Status 200 OK
     */
    @PutMapping("/update/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductsDto> updateProducts(@PathVariable int productId, @RequestBody ProductsDto productsDto) {
        ProductsDto updatedProducts = productsService.updateProducts(productId, productsDto);
        return new ResponseEntity<>(updatedProducts, HttpStatus.OK);
    }

    /**
     * Deletes the product with the specified productId.
     *
     * @param productId Identifier of the product to delete
     * @return HTTP Status 204 NO CONTENT
     */
    @DeleteMapping("/delete/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductsDto> deleteProducts(@PathVariable int productId) {
        productsService.deleteProducts(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
