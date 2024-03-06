package com.bike.stores.dev.service;

import com.bike.stores.dev.dto.StoresDto;
import com.bike.stores.dev.exceptions.StoresNotFoundException;
import com.bike.stores.dev.model.Stores;
import com.bike.stores.dev.repository.StoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling operations related to Stores.
 */
@Service
public class StoresService {

    private final StoresRepository storesRepository;

    /**
     * Constructor to initialize the service with a StoresRepository.
     *
     * @param storesRepository The repository for accessing Stores data.
     */
    public StoresService(StoresRepository storesRepository) {
        this.storesRepository = storesRepository;
    }

    /**
     * Retrieves a specific store by ID.
     *
     * @param id The ID of the store to retrieve.
     * @return The DTO representation of the store.
     * @throws StoresNotFoundException if no store is found for the given ID.
     */
    public StoresDto getStoresById(int id) {
        Stores stores = storesRepository.findById(id)
                .orElseThrow(() -> new StoresNotFoundException("Stores couldn't be found by id:" + id));

        return mapToStoresDto(stores);
    }

    /**
     * Retrieves all stores.
     *
     * @return List of DTO representations of all stores.
     */
    public List<StoresDto> getAllStores() {
        List<Stores> storesList = storesRepository.findAll();

        return storesList.stream()
                .map(this::mapToStoresDto)
                .collect(Collectors.toList());
    }

    /**
     * Maps a Stores entity to its DTO representation.
     *
     * @param stores The Stores entity.
     * @return The DTO representation of the store.
     */
    private StoresDto mapToStoresDto(Stores stores) {
        return new StoresDto(
                stores.getStoreId(),
                stores.getStoreName(),
                stores.getPhone(),
                stores.getEmail(),
                stores.getStreet(),
                stores.getCity(),
                stores.getState(),
                stores.getZipCode()
        );
    }

    /**
     * Creates a new store.
     *
     * @param storesDto The DTO representing the store to be created.
     * @return The DTO representation of the created store.
     */
    public StoresDto createStores(StoresDto storesDto) {
        Stores newStores = mapToStoresEntity(storesDto);
        Stores createdStores = storesRepository.save(newStores);
        return mapToStoresDto(createdStores);
    }

    /**
     * Updates an existing store.
     *
     * @param id         The ID of the store to be updated.
     * @param storesDto  The DTO representing the updated store.
     * @return The DTO representation of the updated store.
     * @throws StoresNotFoundException if no store is found for the given ID.
     */
    public StoresDto updateStores(int id, StoresDto storesDto) {
        Stores existingStores = storesRepository.findById(id)
                .orElseThrow(() -> new StoresNotFoundException("Stores couldn't be found by id:" + id));

        // Update existingStores fields with storesDto fields
        existingStores.setStoreName(storesDto.getStoreName());
        existingStores.setPhone(storesDto.getPhone());
        existingStores.setEmail(storesDto.getEmail());
        existingStores.setStreet(storesDto.getStreet());
        existingStores.setCity(storesDto.getCity());
        existingStores.setState(storesDto.getState());
        existingStores.setZipCode(storesDto.getZipCode());

        Stores updatedStores = storesRepository.save(existingStores);
        return mapToStoresDto(updatedStores);
    }

    /**
     * Deletes a store by ID.
     *
     * @param id The ID of the store to be deleted.
     * @throws StoresNotFoundException if no store is found for the given ID.
     */
    public void deleteStores(int id) {
        if (!storesRepository.existsById(id)) {
            throw new StoresNotFoundException("Stores couldn't be found by id:" + id);
        }

        storesRepository.deleteById(id);
    }

    /**
     * Maps a StoresDto to its entity representation.
     *
     * @param storesDto The DTO representing the store.
     * @return The entity representation of the store.
     */
    private Stores mapToStoresEntity(StoresDto storesDto) {
        Stores stores = new Stores();
        stores.setStoreName(storesDto.getStoreName());
        stores.setPhone(storesDto.getPhone());
        stores.setEmail(storesDto.getEmail());
        stores.setStreet(storesDto.getStreet());
        stores.setCity(storesDto.getCity());
        stores.setState(storesDto.getState());
        stores.setZipCode(storesDto.getZipCode());
        return stores;
    }
}
