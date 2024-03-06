package com.bike.stores.dev.service;

import com.bike.stores.dev.dto.ProductsDto;
import com.bike.stores.dev.exceptions.ProductsNotFoundException;
import com.bike.stores.dev.model.Products;
import com.bike.stores.dev.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    /**
     * Retrieves Products by ID.
     *
     * @param id The ID of the Products to be retrieved.
     * @return The Products DTO.
     * @throws ProductsNotFoundException if no Products are found for the given ID.
     */
    public ProductsDto getProductsById(int id) {
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new ProductsNotFoundException("Products couldn't be found by id:" + id));
        return mapToProductsDto(products);
    }

    /**
     * Retrieves all Products.
     *
     * @return List of Products DTOs.
     */
    public List<ProductsDto> getAllProducts() {
        List<Products> productsList = productsRepository.findAll();
        return productsList.stream()
                .map(this::mapToProductsDto)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new Products.
     *
     * @param productsDto The DTO representing the Products to be created.
     * @return The created Products DTO.
     */
    public ProductsDto createProducts(ProductsDto productsDto) {
        Products products = mapToProductsEntity(productsDto);
        Products createdProducts = productsRepository.save(products);
        return mapToProductsDto(createdProducts);
    }

    /**
     * Updates an existing Products.
     *
     * @param id          The ID of the Products to be updated.
     * @param productsDto The DTO representing the updated Products.
     * @return The updated Products DTO.
     * @throws ProductsNotFoundException if no Products are found for the given ID.
     */
    public ProductsDto updateProducts(int id, ProductsDto productsDto) {
        Products existingProducts = productsRepository.findById(id)
                .orElseThrow(() -> new ProductsNotFoundException("Products not found"));

        // Update existingProducts fields with productsDto fields
        existingProducts.setProductName(productsDto.getProductName());
        existingProducts.setBrandId(productsDto.getBrandId());
        existingProducts.setCategoryId(productsDto.getCategoryId());
        existingProducts.setModelYear(productsDto.getModelYear());
        existingProducts.setListPrice(productsDto.getListPrice());

        Products updatedProducts = productsRepository.save(existingProducts);
        return mapToProductsDto(updatedProducts);
    }

    /**
     * Deletes an existing Products.
     *
     * @param id The ID of the Products to be deleted.
     */
    public void deleteProducts(int id) {
        Products existingProducts = productsRepository.findById(id)
                .orElseThrow(() -> new ProductsNotFoundException("Products not found"));

        productsRepository.delete(existingProducts);
    }

    /**
     * Maps Products entity to ProductsDto.
     *
     * @param products The Products entity.
     * @return The ProductsDto.
     */
    private ProductsDto mapToProductsDto(Products products) {
        return new ProductsDto(
                products.getProductId(),
                products.getProductName(),
                products.getBrandId(),
                products.getCategoryId(),
                products.getModelYear(),
                products.getListPrice()
        );
    }

    /**
     * Maps ProductsDto to Products entity.
     *
     * @param productsDto The ProductsDto.
     * @return The Products entity.
     */
    private Products mapToProductsEntity(ProductsDto productsDto) {
        Products products = new Products();
        products.setProductName(productsDto.getProductName());
        products.setBrandId(productsDto.getBrandId());
        products.setCategoryId(productsDto.getCategoryId());
        products.setModelYear(productsDto.getModelYear());
        products.setListPrice(productsDto.getListPrice());
        return products;
    }
}
