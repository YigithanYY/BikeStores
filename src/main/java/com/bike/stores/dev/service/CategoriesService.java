package com.bike.stores.dev.service;

import com.bike.stores.dev.dto.CategoriesDto;
import com.bike.stores.dev.exceptions.CategoriesNotFoundException;
import com.bike.stores.dev.model.Categories;
import com.bike.stores.dev.repository.CategoriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    // Constructor-based dependency injection
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    /**
     * Creates a new category.
     *
     * @param categoriesDto DTO containing information for the new category
     * @return DTO with information of the created category
     */
    public CategoriesDto createCategories(CategoriesDto categoriesDto) {
        Categories newCategory = mapToCategories(categoriesDto);
        Categories savedCategory = categoriesRepository.save(newCategory);
        return mapToCategoriesDto(savedCategory);
    }

    private Categories mapToCategories(CategoriesDto categoriesDto) {
        Categories categories = new Categories();
        categories.setCategoryName(categoriesDto.getCategoryName());
        return categories;
    }

    /**
     * Retrieves a category by its ID.
     *
     * @param id The ID of the category to retrieve
     * @return DTO with information of the specified category
     * @throws CategoriesNotFoundException if the category is not found
     */
    public CategoriesDto getCategoryById(int id) {
        Categories categories = categoriesRepository.findById(id)
                .orElseThrow(() -> new CategoriesNotFoundException("Categories couldn't be found by id:" + id));
        return mapToCategoriesDto(categories);
    }

    /**
     * Retrieves all categories.
     *
     * @return List of DTOs with information of all categories
     */
    public List<CategoriesDto> getAllCategories() {
        List<Categories> categoriesList = categoriesRepository.findAll();
        return categoriesList.stream()
                .map(this::mapToCategoriesDto)
                .collect(Collectors.toList());
    }

    private CategoriesDto mapToCategoriesDto(Categories categories) {
        return new CategoriesDto(
                categories.getCategoryId(),
                categories.getCategoryName()
        );
    }

    /**
     * Updates an existing category by its ID.
     *
     * @param id            The ID of the category to update
     * @param categoriesDto DTO containing the new information
     * @return DTO with information of the updated category
     * @throws CategoriesNotFoundException if the category is not found
     */
    public CategoriesDto updateCategories(int id, CategoriesDto categoriesDto) {
        Categories existingCategory = categoriesRepository.findById(id)
                .orElseThrow(() -> new CategoriesNotFoundException("Categories couldn't be found by id:" + id));

        // Update operations
        existingCategory.setCategoryName(categoriesDto.getCategoryName());

        Categories updatedCategories = categoriesRepository.save(existingCategory);
        return mapToCategoriesDto(updatedCategories);
    }

    /**
     * Deletes a category by its ID.
     *
     * @param id The ID of the category to delete
     * @throws CategoriesNotFoundException if the category is not found
     */
    public void deleteCategoriesById(int id) {
        if (!categoriesRepository.existsById(id)) {
            throw new CategoriesNotFoundException("Categories couldn't be found by id:" + id);
        }
        categoriesRepository.deleteById(id);
    }
}
