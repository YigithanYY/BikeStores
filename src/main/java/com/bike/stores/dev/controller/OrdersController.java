package com.bike.stores.dev.controller;


import com.bike.stores.dev.dto.CustomersDto;
import com.bike.stores.dev.dto.OrdersDto;
import com.bike.stores.dev.service.CustomersService;
import com.bike.stores.dev.service.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrdersController {

    private final OrdersService ordersService;

    private final CustomersService customersService;


    // Applying dependency injection using constructor injection
    public OrdersController(OrdersService ordersService, CustomersService customersService) {
        this.ordersService = ordersService;
        this.customersService = customersService;

    }
        /**
         * Retrieves the order with the specified orderId.
         *
         * @param orderId Identifier of the order to retrieve
         * @return DTO with information of the specified order and HTTP Status 200 OK
         */
        @GetMapping("/{orderId}")
        @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
        public ResponseEntity<OrdersDto> getOrdersById ( @PathVariable int orderId){
            return ResponseEntity.ok(ordersService.getOrdersById(orderId));
        }

        /**
         * Retrieves all orders.
         *
         * @return List of DTOs with information of all orders and HTTP Status 200 OK
         */
        @GetMapping("/all")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<List<OrdersDto>> getAllOrders () {
            return ResponseEntity.ok(ordersService.getAllOrders());
        }

        /**
         * Creates a new order.
         *
         * @param ordersDto DTO containing information for the new order
         * @return DTO with information of the created order and HTTP Status 201 Created
         */
        @PostMapping("/create")
        @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
        public ResponseEntity<OrdersDto> createOrders (@RequestBody OrdersDto ordersDto){
            OrdersDto createdOrders = ordersService.createOrders(ordersDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrders);
        }

        /**
         * Updates the order with the specified orderId.
         *
         * @param orderId   Identifier of the order to update
         * @param ordersDto DTO containing the new information
         * @return DTO with information of the updated order and HTTP Status 200 OK
         */
        @PutMapping("/update/{orderId}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<OrdersDto> updateOrders ( @PathVariable int orderId, @RequestBody OrdersDto ordersDto){
            OrdersDto updatedOrders = ordersService.updateOrders(orderId, ordersDto);
            return new ResponseEntity<>(updatedOrders, HttpStatus.OK);
        }

        /**
         * Deletes the order with the specified orderId.
         *
         * @param orderId Identifier of the order to delete
         * @return HTTP Status 204 NO CONTENT
         */
        @DeleteMapping("/delete/{orderId}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<OrdersDto> deleteOrders ( @PathVariable int orderId){
            ordersService.deleteOrders(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    /**
     * Retrieves orders by customer ID.
     *
     * @param customerId Identifier of the customer
     * @return List of DTOs with information of orders for the specified customer and HTTP Status 200 OK
     */
    @GetMapping("/bycustomer/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<List<OrdersDto>> getOrdersByCustomerId(@PathVariable int customerId) {
        CustomersDto customersDto = customersService.getCustomerById(customerId);
        List<OrdersDto> ordersByCustomerId = ordersService.getOrdersByCustomerId(customersDto);
        return ResponseEntity.ok(ordersByCustomerId);
    }

}

