package com.bike.stores.dev.controller;

import com.bike.stores.dev.dto.BrandsDto;
import com.bike.stores.dev.service.BrandsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/brands")
public class BrandsController {

    private final BrandsService brandsService;

    // Applying dependency injection using constructor injection
    public BrandsController(BrandsService brandsService) {
        this.brandsService = brandsService;
    }

    /**
     * Creates a new brand.
     *
     * @param brandsDto DTO containing information for the new brand
     * @return DTO with information of the created brand and HTTP Status 201 Created
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BrandsDto> createBrand(@RequestBody BrandsDto brandsDto) {
        BrandsDto createdBrandsDto = brandsService.createBrands(brandsDto);
        return new ResponseEntity<>(createdBrandsDto, HttpStatus.CREATED);
    }

    /**
     * Retrieves the brand with the specified brandId.
     *
     * @param brandId Identifier of the brand to retrieve
     * @return DTO with information of the specified brand and HTTP Status 200 OK
     */
    @GetMapping("/{brandId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<BrandsDto> getBrandById(@PathVariable int brandId) {
        BrandsDto brandsDto = brandsService.getBrandsById(brandId);
        return ResponseEntity.ok(brandsDto);
    }

    /**
     * Retrieves all brands.
     *
     * @return List of DTOs with information of all brands and HTTP Status 200 OK
     */
    @GetMapping("all")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<List<BrandsDto>> getAllBrands() {
        List<BrandsDto> brandsDtoList = brandsService.getAllBrands();
        return ResponseEntity.ok(brandsDtoList);
    }

    /**
     * Updates the brand with the specified brandId.
     *
     * @param brandId   Identifier of the brand to update
     * @param brandsDto DTO containing the new information
     * @return DTO with information of the updated brand and HTTP Status 200 OK
     */
    @PutMapping("/update/{brandId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BrandsDto> updateBrand(@PathVariable int brandId, @RequestBody BrandsDto brandsDto) {
        BrandsDto updatedBrandsDto = brandsService.updateBrands(brandId, brandsDto);
        return new ResponseEntity<>(updatedBrandsDto, HttpStatus.OK);
    }

    /**
     * Deletes the brand with the specified brandId.
     *
     * @param brandId Identifier of the brand to delete
     * @return HTTP Status 204 NO CONTENT
     */
    @DeleteMapping("/delete/{brandId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteBrandById(@PathVariable int brandId) {
        brandsService.deleteBrandsById(brandId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
