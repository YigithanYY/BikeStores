package com.bike.stores.dev.service;

import com.bike.stores.dev.dto.CustomersDto;
import com.bike.stores.dev.dto.OrdersDto;
import com.bike.stores.dev.exceptions.CustomersNotFoundException;
import com.bike.stores.dev.exceptions.OrdersNotFoundException;
import com.bike.stores.dev.model.Customers;

import com.bike.stores.dev.model.Orders;
import com.bike.stores.dev.repository.CustomersRepository;
import com.bike.stores.dev.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final CustomersRepository customersRepository;

    public OrdersService(OrdersRepository ordersRepository, CustomersRepository customersRepository) {
        this.ordersRepository = ordersRepository;
        this.customersRepository = customersRepository;

    }

    /**
     * Retrieves Orders by ID.
     *
     * @param id The ID of the Orders to be retrieved.
     * @return The Orders DTO.
     * @throws OrdersNotFoundException if no Orders are found for the given ID.
     */
    public OrdersDto getOrdersById(int id) {
        Orders orders = findOrdersById(id);
        return mapToOrdersDto(orders);
    }

    /**
     * Retrieves all Orders.
     *
     * @return List of Orders DTOs.
     */
    public List<OrdersDto> getAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();
        return ordersList.stream()
                .map(this::mapToOrdersDto)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new Orders.
     *
     * @param ordersDto The DTO representing the Orders to be created.
     * @return The created Orders DTO.
     */
    @Transactional
    public OrdersDto createOrders(OrdersDto ordersDto) {
        Orders orders = mapToOrdersEntity(ordersDto);
        Orders createdOrders = ordersRepository.save(orders);
        return mapToOrdersDto(createdOrders);
    }

    /**
     * Updates an existing Orders.
     *
     * @param id         The ID of the Orders to be updated.
     * @param ordersDto  The DTO representing the updated Orders.
     * @return The updated Orders DTO.
     * @throws OrdersNotFoundException if no Orders are found for the given ID.
     */
    public OrdersDto updateOrders(int id, OrdersDto ordersDto) {
        Orders existingOrders = findOrdersById(id);
        updateOrdersFields(existingOrders, ordersDto);
        Orders updatedOrders = ordersRepository.save(existingOrders);
        return mapToOrdersDto(updatedOrders);
    }

    /**
     * Deletes an existing Orders.
     *
     * @param id The ID of the Orders to be deleted.
     */
    public void deleteOrders(int id) {
        Orders existingOrders = findOrdersById(id);
        ordersRepository.delete(existingOrders);
    }

    /**
     * Finds Orders by ID.
     *
     * @param id The ID of the Orders to be retrieved.
     * @return The Orders entity.
     * @throws OrdersNotFoundException if no Orders are found for the given ID.
     */
    private Orders findOrdersById(int id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new OrdersNotFoundException("Orders not found"));
    }

    /**
     * Maps Orders entity to OrdersDto.
     *
     * @param orders The Orders entity.
     * @return The OrdersDto.
     */
    private OrdersDto mapToOrdersDto(Orders orders) {
        return new OrdersDto(
                orders.getOrderId(),
                orders.getCustomerId(),
                orders.getOrderStatus(),
                orders.getOrderDate(),
                orders.getRequiredDate(),
                orders.getShippedDate(),
                orders.getStoreId(),
                orders.getStaffId()
        );
    }

    /**
     * Updates fields of existing Orders entity.
     *
     * @param existingOrders The existing Orders entity.
     * @param ordersDto      The DTO containing updated values.
     */
    private void updateOrdersFields(Orders existingOrders, OrdersDto ordersDto) {
        existingOrders.setCustomerId(ordersDto.getCustomerId());
        existingOrders.setOrderStatus(ordersDto.getOrderStatus());
        existingOrders.setOrderDate(ordersDto.getOrderDate());
        existingOrders.setRequiredDate(ordersDto.getRequiredDate());
        existingOrders.setShippedDate(ordersDto.getShippedDate());
        existingOrders.setStoreId(ordersDto.getStoreId());
        existingOrders.setStaffId(ordersDto.getStaffId());
    }

    /**
     * Maps OrdersDto to Orders entity.
     *
     * @param ordersDto The OrdersDto.
     * @return The Orders entity.
     */
    private Orders mapToOrdersEntity(OrdersDto ordersDto) {
        Orders orders = new Orders();
        orders.setOrderStatus(ordersDto.getOrderStatus());
        orders.setOrderDate(ordersDto.getOrderDate());
        orders.setRequiredDate(ordersDto.getRequiredDate());
        orders.setShippedDate(ordersDto.getShippedDate());
        orders.setStoreId(ordersDto.getStoreId());
        orders.setStaffId(ordersDto.getStaffId());

        Customers existingCustomer = customersRepository.findById(ordersDto.getCustomerId().getCustomerId())
                .orElseThrow(() -> new CustomersNotFoundException("Customer not found"));
        orders.setCustomerId(existingCustomer);

        return orders;
    }


    public List<OrdersDto> getOrdersByCustomerId(CustomersDto customersDto) {
        Customers existingCustomer = customersRepository.findById(customersDto.getCustomerId())
                .orElseThrow(() -> new CustomersNotFoundException("Customer not found"));
        return ordersRepository.findByCustomerId(existingCustomer);
    }






}
