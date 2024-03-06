package com.bike.stores.dev.controller;

import com.bike.stores.dev.dto.OrderItemsDto;
import com.bike.stores.dev.model.OrderItemsIds;
import com.bike.stores.dev.service.OrderItemsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orderitems")
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    // Applying dependency injection using constructor injection
    public OrderItemsController(OrderItemsService orderItemsService) {
        this.orderItemsService = orderItemsService;
    }

    /**
     * Retrieves order items by their order and item IDs.
     *
     * @param orderId Identifier of the order
     * @param itemId  Identifier of the item
     * @return List of DTOs with information of the specified order items
     */
    @GetMapping("/{orderId}/{itemId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public List<OrderItemsDto> getOrderItemsByIds(@PathVariable int orderId, @PathVariable int itemId) {
        return orderItemsService.getOrderItemsByIds(orderId, itemId);
    }

    /**
     * Retrieves all order items for a given order.
     *
     * @param orderId Identifier of the order
     * @return List of DTOs with information of all order items for the specified order
     */
    @GetMapping("/all/{orderId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public List<OrderItemsDto> getOrderItemsByOrderId(@PathVariable int orderId) {
        return orderItemsService.getOrderItemsByOrderId(orderId);
    }

    /**
     * Retrieves all order items.
     *
     * @return List of DTOs with information of all order items and HTTP Status 200 OK
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<OrderItemsDto>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemsService.getAllOrderItems());
    }

    /**
     * Creates new order items.
     *
     * @param orderItemsDto DTO containing information for the new order items
     * @return DTO with information of the created order items and HTTP Status 201 Created
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OrderItemsDto> createOrderItems(@RequestBody OrderItemsDto orderItemsDto) {
        return new ResponseEntity<>(orderItemsService.createOrderItems(orderItemsDto), HttpStatus.CREATED);
    }

    /**
     * Updates existing order items.
     *
     * @param orderId       Identifier of the order
     * @param itemId        Identifier of the item
     * @param orderItemsDto DTO containing the new information
     * @return DTO with information of the updated order items and HTTP Status 200 OK
     */
    @PutMapping("/update/{orderId}/{itemId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OrderItemsDto> updateOrderItems(
            @PathVariable int orderId, @PathVariable int itemId, @RequestBody OrderItemsDto orderItemsDto) {
        OrderItemsDto updatedOrderItemsDto = orderItemsService.updateOrderItems(new OrderItemsIds(orderId, itemId), orderItemsDto);
        return new ResponseEntity<>(updatedOrderItemsDto, HttpStatus.OK);
    }

    /**
     * Deletes existing order items.
     *
     * @param orderId Identifier of the order
     * @param itemId  Identifier of the item
     * @return HTTP Status 204 NO CONTENT
     */
    @DeleteMapping("/delete/{orderId}/{itemId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<OrderItemsDto> deleteOrderItems(
            @PathVariable int orderId,
            @PathVariable int itemId) {
        orderItemsService.deleteOrderItems(new OrderItemsIds(orderId, itemId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
