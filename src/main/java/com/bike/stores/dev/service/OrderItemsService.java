package com.bike.stores.dev.service;

import com.bike.stores.dev.dto.OrderItemsDto;
import com.bike.stores.dev.exceptions.OrderItemsNotFoundException;
import com.bike.stores.dev.model.OrderItems;
import com.bike.stores.dev.model.OrderItemsIds;
import com.bike.stores.dev.repository.OrderItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    /**
     * Creates a new OrderItems.
     *
     * @param orderItemsDto The DTO representing the OrderItems to be created.
     * @return The created OrderItems DTO.
     */
    public OrderItemsDto createOrderItems(OrderItemsDto orderItemsDto) {
        OrderItems newOrderItems = mapToOrderItems(orderItemsDto);
        OrderItems savedOrderItems = orderItemsRepository.save(newOrderItems);
        return mapToOrderItemsDto(savedOrderItems);
    }

    /**
     * Retrieves OrderItems by orderId and itemId.
     *
     * @param orderId The order ID.
     * @param itemId  The item ID.
     * @return List of OrderItems DTOs.
     * @throws OrderItemsNotFoundException if no OrderItems are found for the given orderId and itemId.
     */
    public List<OrderItemsDto> getOrderItemsByIds(int orderId, int itemId) {
        List<OrderItems> orderItemsList = orderItemsRepository
                .findByOrderItemsIds_OrderIdAndOrderItemsIds_ItemId(orderId, itemId);

        if (orderItemsList.isEmpty()) {
            throw new OrderItemsNotFoundException("No valid order items found for orderId: " + orderId + " and itemId: " + itemId);
        }

        return mapOrderItemsListToDtoList(orderItemsList);
    }

    /**
     * Retrieves OrderItems by orderId.
     *
     * @param orderId The order ID.
     * @return List of OrderItems DTOs.
     * @throws OrderItemsNotFoundException if no OrderItems are found for the given orderId.
     */
    public List<OrderItemsDto> getOrderItemsByOrderId(int orderId) {
        List<OrderItems> orderItemsList = orderItemsRepository.findByOrderItemsIds_OrderId(orderId);

        if (orderItemsList.isEmpty()) {
            throw new OrderItemsNotFoundException("No order items found for orderId: " + orderId);
        }

        return mapOrderItemsListToDtoList(orderItemsList);
    }

    /**
     * Retrieves all OrderItems.
     *
     * @return List of all OrderItems DTOs.
     */
    public List<OrderItemsDto> getAllOrderItems() {
        List<OrderItems> orderItemsList = orderItemsRepository.findAll();
        return mapOrderItemsListToDtoList(orderItemsList);
    }

    /**
     * Updates an existing OrderItems.
     *
     * @param orderItemsIds The IDs identifying the OrderItems.
     * @param orderItemsDto The DTO representing the updated OrderItems.
     * @return The updated OrderItems DTO.
     * @throws OrderItemsNotFoundException if no OrderItems are found for the given orderItemsIds.
     */
    public OrderItemsDto updateOrderItems(OrderItemsIds orderItemsIds, OrderItemsDto orderItemsDto) {
        OrderItems existingOrderItems = orderItemsRepository.findById(orderItemsIds)
                .orElseThrow(() -> new OrderItemsNotFoundException("Order items not found for id: " + orderItemsIds));

        // Update operations
        existingOrderItems.setProductId(orderItemsDto.getProductId());
        existingOrderItems.setQuantity(orderItemsDto.getQuantity());
        existingOrderItems.setListPrice(orderItemsDto.getListPrice());

        OrderItems updatedOrderItems = orderItemsRepository.save(existingOrderItems);
        return mapToOrderItemsDto(updatedOrderItems);
    }

    /**
     * Deletes an existing OrderItems.
     *
     * @param orderItemsIds The IDs identifying the OrderItems to be deleted.
     * @throws OrderItemsNotFoundException if no OrderItems are found for the given orderItemsIds.
     */
    public void deleteOrderItems(OrderItemsIds orderItemsIds) {
        if (!orderItemsRepository.existsById(orderItemsIds)) {
            throw new OrderItemsNotFoundException("Order items not found for id: " + orderItemsIds);
        }

        orderItemsRepository.deleteById(orderItemsIds);
    }

    /**
     * Maps an OrderItems entity to its corresponding DTO.
     *
     * @param orderItems The OrderItems entity.
     * @return The mapped OrderItems DTO.
     */
    private OrderItemsDto mapToOrderItemsDto(OrderItems orderItems) {
        return new OrderItemsDto(
                orderItems.getOrderItemsIds(),
                orderItems.getProductId(),
                orderItems.getQuantity(),
                orderItems.getListPrice(),
                orderItems.getDiscount()
        );
    }

    /**
     * Maps a list of OrderItems entities to a list of corresponding DTOs.
     *
     * @param orderItemsList The list of OrderItems entities.
     * @return The list of mapped OrderItems DTOs.
     */
    private List<OrderItemsDto> mapOrderItemsListToDtoList(List<OrderItems> orderItemsList) {
        return orderItemsList.stream()
                .map(this::mapToOrderItemsDto)
                .collect(Collectors.toList());
    }

    /**
     * Maps an OrderItemsDto to its corresponding entity.
     *
     * @param orderItemsDto The OrderItemsDto.
     * @return The mapped OrderItems entity.
     */
    private OrderItems mapToOrderItems(OrderItemsDto orderItemsDto) {
        OrderItems orderItems = new OrderItems();
        orderItems.setOrderItemsIds(orderItemsDto.getOrderItemsIds());
        orderItems.setProductId(orderItemsDto.getProductId());
        orderItems.setQuantity(orderItemsDto.getQuantity());
        orderItems.setListPrice(orderItemsDto.getListPrice());
        orderItems.setDiscount(orderItemsDto.getDiscount());
        return orderItems;
    }
}
