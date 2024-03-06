package com.bike.stores.dev.controller;

import com.bike.stores.dev.dto.StocksDto;
import com.bike.stores.dev.model.StocksIds;
import com.bike.stores.dev.service.StocksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/stocks")
public class StocksController {

    private final StocksService stocksService;

    // Applying dependency injection using constructor injection
    public StocksController(StocksService stocksService) {
        this.stocksService = stocksService;
    }

    /**
     * Retrieves stocks by the specified storeId.
     *
     * @param storeId Identifier of the store to retrieve stocks for
     * @return List of DTOs with information of stocks for the specified store
     */
    @GetMapping("/store/{storeId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public List<StocksDto> getStocksByStoreId(@PathVariable int storeId) {
        return stocksService.getStocksByStoreId(storeId);
    }

    /**
     * Retrieves stocks by the specified productId.
     *
     * @param productId Identifier of the product to retrieve stocks for
     * @return List of DTOs with information of stocks for the specified product
     */
    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public List<StocksDto> getStocksByProductId(@PathVariable int productId) {
        return stocksService.getStocksByProductId(productId);
    }

    /**
     * Retrieves all stocks.
     *
     * @return List of DTOs with information of all stocks and HTTP Status 200 OK
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<List<StocksDto>> getAllStocks() {
        return ResponseEntity.ok(stocksService.getAllStocks());
    }

    /**
     * Creates new stocks.
     *
     * @param stocksDto DTO containing information for the new stocks
     * @return DTO with information of the created stocks and HTTP Status 201 Created
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StocksDto> createStocks(@RequestBody StocksDto stocksDto) {
        return new ResponseEntity<>(stocksService.createStocks(stocksDto), HttpStatus.CREATED);
    }

    /**
     * Updates existing stocks.
     *
     * @param storeId    Identifier of the store
     * @param productId  Identifier of the product
     * @param stocksDto  DTO containing the new information
     * @return DTO with information of the updated stocks and HTTP Status 200 OK
     */
    @PutMapping("/{storeId}/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StocksDto> updateStocks(
            @PathVariable int storeId,
            @PathVariable int productId,
            @RequestBody StocksDto stocksDto,
            @RequestBody int quantity
    ) {
        StocksDto updatedStocksDto = stocksService.updateStocks(new StocksIds(storeId, productId), stocksDto,quantity);
        return new ResponseEntity<>(updatedStocksDto, HttpStatus.OK);
    }

    /**
     * Deletes existing stocks.
     *
     * @param storeId   Identifier of the store
     * @param productId Identifier of the product
     * @return HTTP Status 204 NO CONTENT
     */
    @DeleteMapping("/delete/{storeId}/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StocksDto> deleteStocks(
            @PathVariable int storeId,
            @PathVariable int productId
    ) {
        StocksIds stocksIds = new StocksIds(storeId, productId);
        stocksService.deleteStocks(stocksIds);
        return ResponseEntity.noContent().build();
    }
}
