package com.bike.stores.dev.service;

import com.bike.stores.dev.dto.BrandsDto;
import com.bike.stores.dev.exceptions.BrandsNotFoundException;
import com.bike.stores.dev.model.Brands;
import com.bike.stores.dev.repository.BrandsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandsService {

    private final BrandsRepository brandsRepository;

    // Constructor-based dependency injection
    public BrandsService(BrandsRepository brandsRepository) {
        this.brandsRepository = brandsRepository;
    }

    /**
     * Retrieves a brand by its ID.
     *
     * @param id The ID of the brand to retrieve
     * @return DTO with information of the specified brand
     * @throws BrandsNotFoundException if the brand is not found
     */
    public BrandsDto getBrandsById(int id) {
        Brands brand = brandsRepository.findById(id)
                .orElseThrow(() -> new BrandsNotFoundException("Brand couldn't be found by id:" + id));
        return mapToBrandsDto(brand);
    }

    /**
     * Retrieves all brands.
     *
     * @return List of DTOs with information of all brands
     */
    public List<BrandsDto> getAllBrands() {
        List<Brands> brandsList = brandsRepository.findAll();

        return brandsList.stream()
                .map(this::mapToBrandsDto)
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing brand by its ID.
     *
     * @param id         The ID of the brand to update
     * @param brandsDto  DTO containing the new information
     * @return DTO with information of the updated brand
     * @throws BrandsNotFoundException if the brand is not found
     */
    public BrandsDto updateBrands(int id, BrandsDto brandsDto) {
        Brands existingBrand = brandsRepository.findById(id)
                .orElseThrow(() -> new BrandsNotFoundException("Brand not found"));
        existingBrand.setBrandName(brandsDto.getBrandName());

        Brands updatedBrand = brandsRepository.save(existingBrand);
        return mapToBrandsDto(updatedBrand);
    }

    /**
     * Deletes a brand by its ID.
     *
     * @param id The ID of the brand to delete
     * @throws BrandsNotFoundException if the brand is not found
     */
    public void deleteBrandsById(int id) {
        if (!brandsRepository.existsById(id)) {
            throw new BrandsNotFoundException("Brand couldn't be found by id:" + id);
        }

        brandsRepository.deleteById(id);
    }

    /**
     * Creates a new brand.
     *
     * @param brandsDto DTO containing information for the new brand
     * @return DTO with information of the created brand
     */
    public BrandsDto createBrands(BrandsDto brandsDto) {
        Brands newBrands = mapToBrands(brandsDto);
        Brands savedBrands = brandsRepository.save(newBrands);
        return mapToBrandsDto(savedBrands);
    }

    private BrandsDto mapToBrandsDto(Brands brands) {
        return new BrandsDto(
                brands.getBrandId(),
                brands.getBrandName()
        );
    }

    private Brands mapToBrands(BrandsDto brandsDto) {
        Brands brands = new Brands();
        brands.setBrandName(brandsDto.getBrandName());

        return brands;
    }
}
