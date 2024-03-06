package com.bike.stores.dev.service;

import com.bike.stores.dev.dto.StocksDto;
import com.bike.stores.dev.exceptions.StocksNotFoundException;
import com.bike.stores.dev.model.Stocks;
import com.bike.stores.dev.model.StocksIds;
import com.bike.stores.dev.repository.StocksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StocksService {

    private final StocksRepository stocksRepository;

    public StocksService(StocksRepository stocksRepository) {
        this.stocksRepository = stocksRepository;
    }

    /**
     * Retrieves Stocks by Store ID.
     *
     * @param storeId The ID of the Store for which Stocks are retrieved.
     * @return List of Stocks DTOs.
     * @throws StocksNotFoundException if no Stocks are found for the given Store ID.
     */
    public List<StocksDto> getStocksByStoreId(int storeId) {
        List<Stocks> stocksList = stocksRepository.findByStocksIds_StoreId(storeId);

        if (stocksList.isEmpty()) {
            throw new StocksNotFoundException("No valid Stocks found for Store ID: " + storeId);
        }

        return mapStocksDtoList(stocksList);
    }

    /**
     * Retrieves Stocks by Product ID.
     *
     * @param productId The ID of the Product for which Stocks are retrieved.
     * @return List of Stocks DTOs.
     * @throws StocksNotFoundException if no Stocks are found for the given Product ID.
     */
    public List<StocksDto> getStocksByProductId(int productId) {
        List<Stocks> stocksList = stocksRepository.findByStocksIds_ProductId(productId);

        if (stocksList.isEmpty()) {
            throw new StocksNotFoundException("No valid Stocks found for Product ID: " + productId);
        }

        return mapStocksDtoList(stocksList);
    }

    /**
     * Retrieves all Stocks.
     *
     * @return List of Stocks DTOs.
     */
    public List<StocksDto> getAllStocks() {
        List<Stocks> stocksList = stocksRepository.findAll();
        return mapStocksDtoList(stocksList);
    }

    /**
     * Creates a new Stocks.
     *
     * @param stocksDto The DTO representing the Stocks to be created.
     * @return The created Stocks DTO.
     */
    public StocksDto createStocks(StocksDto stocksDto) {
        Stocks newStocks = mapToStocksEntity(stocksDto);
        Stocks createdStocks = stocksRepository.save(newStocks);
        return mapToStocksDto(createdStocks);
    }

    /**
     * Updates an existing Stocks.
     *
     * @param stocksIds The ID of the Stocks to be updated.
     * @param stocksDto The DTO representing the updated Stocks.
     * @return The updated Stocks DTO.
     * @throws StocksNotFoundException if no Stocks are found for the given Stocks IDs.
     */
    public StocksDto updateStocks(StocksIds stocksIds, StocksDto stocksDto, int quantity) {
        Stocks existingStocks = stocksRepository.findById(stocksIds)
                .orElseThrow(() -> new StocksNotFoundException("Stocks not found"));

        // Update existingStocks fields with stocksDto fields

        int updatedQuantity = existingStocks.getQuantity() - quantity;
        existingStocks.setQuantity(updatedQuantity);


        existingStocks.setQuantity(stocksDto.getQuantity());

        Stocks updatedStocks = stocksRepository.save(existingStocks);
        return mapToStocksDto(updatedStocks);
    }

    /**
     * Deletes an existing Stocks.
     *
     * @param stocksIds The ID of the Stocks to be deleted.
     * @throws StocksNotFoundException if no Stocks are found for the given Stocks IDs.
     */
    public void deleteStocks(StocksIds stocksIds) {
        if (!stocksRepository.existsById(stocksIds)) {
            throw new StocksNotFoundException("Stocks not found");
        }

        stocksRepository.deleteById(stocksIds);
    }

    /**
     * Maps a list of Stocks entities to a list of Stocks DTOs.
     *
     * @param stocksList The list of Stocks entities.
     * @return The list of Stocks DTOs.
     */
    private List<StocksDto> mapStocksDtoList(List<Stocks> stocksList) {
        return stocksList.stream()
                .map(stocks -> new StocksDto(
                        stocks.getStocksIds(),
                        stocks.getQuantity()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Maps Stocks entity to StocksDto.
     *
     * @param stocks The Stocks entity.
     * @return The StocksDto.
     */
    private StocksDto mapToStocksDto(Stocks stocks) {
        return new StocksDto(
                stocks.getStocksIds(),
                stocks.getQuantity()
        );
    }

    /**
     * Maps StocksDto to Stocks entity.
     *
     * @param stocksDto The StocksDto.
     * @return The Stocks entity.
     */
    private Stocks mapToStocksEntity(StocksDto stocksDto) {
        Stocks stocks = new Stocks();
        stocks.setStocksIds(stocksDto.getStocksIds());
        stocks.setQuantity(stocksDto.getQuantity());
        return stocks;
    }
}
