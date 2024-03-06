package com.bike.stores.dev.controller;

import com.bike.stores.dev.dto.CustomersDto;
import com.bike.stores.dev.service.CustomersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomersController {

    private final CustomersService customersService;

    // Applying dependency injection using constructor injection
    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    /**
     * Creates a new customer.
     *
     * @param customersDto DTO containing information for the new customer
     * @return DTO with information of the created customer and HTTP Status 201 Created
     */
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<CustomersDto> createCustomer(@RequestBody CustomersDto customersDto) {
        CustomersDto createdCustomerDto = customersService.createCustomer(customersDto);
        return new ResponseEntity<>(createdCustomerDto, HttpStatus.CREATED);
    }

    /**
     * Retrieves the customer with the specified customerId.
     *
     * @param customerId Identifier of the customer to retrieve
     * @return DTO with information of the specified customer and HTTP Status 200 OK
     */
    @GetMapping("/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<CustomersDto> getCustomerById(@PathVariable int customerId) {
        return ResponseEntity.ok(customersService.getCustomerById(customerId));
    }

    /**
     * Retrieves all customers.
     *
     * @return List of DTOs with information of all customers and HTTP Status 200 OK
     */
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CustomersDto>> getAllCustomers() {
        return ResponseEntity.ok(customersService.getAllCustomers());
    }

    /**
     * Updates the customer with the specified customerId.
     *
     * @param customerId    Identifier of the customer to update
     * @param customersDto DTO containing the new information
     * @return DTO with information of the updated customer and HTTP Status 200 OK
     */
    @PutMapping("/update/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<CustomersDto> updateCustomer(@PathVariable int customerId, @RequestBody CustomersDto customersDto) {
        CustomersDto updatedCustomerDto = customersService.updateCustomer(customerId, customersDto);
        return new ResponseEntity<>(updatedCustomerDto, HttpStatus.OK);
    }

    /**
     * Deletes the customer with the specified customerId.
     *
     * @param customerId Identifier of the customer to delete
     * @return HTTP Status 204 NO CONTENT
     */
    @DeleteMapping("/delete/{customerId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomersDto> deleteCustomersById(@PathVariable int customerId) {
        customersService.deleteCustomersById(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
