package com.bike.stores.dev.controller;

import com.bike.stores.dev.dto.CategoriesDto;
import com.bike.stores.dev.service.CategoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    // Applying dependency injection using constructor injection
    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    /**
     * Creates a new category.
     *
     * @param categoriesDto DTO containing information for the new category
     * @return DTO with information of the created category and HTTP Status 201 Created
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoriesDto> createCategory(@RequestBody CategoriesDto categoriesDto) {
        CategoriesDto createdCategoriesDto = categoriesService.createCategories(categoriesDto);
        return new ResponseEntity<>(createdCategoriesDto, HttpStatus.CREATED);
    }

    /**
     * Retrieves the category with the specified categoryId.
     *
     * @param categoryId Identifier of the category to retrieve
     * @return DTO with information of the specified category and HTTP Status 200 OK
     */
    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<CategoriesDto> getCategoryById(@PathVariable int categoryId) {
        return ResponseEntity.ok(categoriesService.getCategoryById(categoryId));
    }

    /**
     * Retrieves all categories.
     *
     * @return List of DTOs with information of all categories and HTTP Status 200 OK
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<List<CategoriesDto>> getAllCategories() {
        return ResponseEntity.ok(categoriesService.getAllCategories());
    }

    /**
     * Updates the category with the specified categoryId.
     *
     * @param categoryId     Identifier of the category to update
     * @param categoriesDto DTO containing the new information
     * @return DTO with information of the updated category and HTTP Status 200 OK
     */
    @PutMapping("/update/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoriesDto> updateCategory(@PathVariable int categoryId, @RequestBody CategoriesDto categoriesDto) {
        CategoriesDto updatedCategoriesDto = categoriesService.updateCategories(categoryId, categoriesDto);
        return new ResponseEntity<>(updatedCategoriesDto, HttpStatus.OK);
    }

    /**
     * Deletes the category with the specified categoryId.
     *
     * @param categoryId Identifier of the category to delete
     * @return HTTP Status 204 NO CONTENT
     */
    @DeleteMapping("/delete/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CategoriesDto> deleteCategoryById(@PathVariable int categoryId) {
        categoriesService.deleteCategoriesById(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
