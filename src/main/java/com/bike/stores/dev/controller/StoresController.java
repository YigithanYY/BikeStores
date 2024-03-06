package com.bike.stores.dev.controller;

import com.bike.stores.dev.dto.StoresDto;
import com.bike.stores.dev.service.StoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/stores")
public class StoresController {

    private final StoresService storesService;

    // Applying dependency injection using constructor injection
    public StoresController(StoresService storesService) {
        this.storesService = storesService;
    }

    /**
     * Retrieves the store with the specified storeId.
     *
     * @param storeId Identifier of the store to retrieve
     * @return DTO with information of the specified store and HTTP Status 200 OK
     */
    @GetMapping("/{storeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StoresDto> getStoreById(@PathVariable int storeId) {
        return ResponseEntity.ok(storesService.getStoresById(storeId));
    }

    /**
     * Retrieves all stores.
     *
     * @return List of DTOs with information of all stores and HTTP Status 200 OK
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<List<StoresDto>> getAllStores() {
        return ResponseEntity.ok(storesService.getAllStores());
    }

    /**
     * Creates a new store.
     *
     * @param storesDto DTO containing information for the new store
     * @return DTO with information of the created store and HTTP Status 201 Created
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StoresDto> createStores(@RequestBody StoresDto storesDto) {
        StoresDto createdStores = storesService.createStores(storesDto);
        return new ResponseEntity<>(createdStores, HttpStatus.CREATED);
    }

    /**
     * Updates the store with the specified storeId.
     *
     * @param storeId   Identifier of the store to update
     * @param storesDto DTO containing the new information
     * @return DTO with information of the updated store and HTTP Status 200 OK
     */
    @PutMapping("/update/{storeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StoresDto> updateStores(@PathVariable int storeId, @RequestBody StoresDto storesDto) {
        StoresDto updatedStores = storesService.updateStores(storeId, storesDto);
        return ResponseEntity.ok(updatedStores);
    }

    /**
     * Deletes the store with the specified storeId.
     *
     * @param storeId Identifier of the store to delete
     * @return HTTP Status 204 NO CONTENT
     */
    @DeleteMapping("/delete/{storeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StoresDto> deleteStores(@PathVariable int storeId) {
        storesService.deleteStores(storeId);
        return ResponseEntity.noContent().build();
    }
}
