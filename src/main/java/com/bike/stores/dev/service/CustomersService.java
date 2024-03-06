package com.bike.stores.dev.service;

import com.bike.stores.dev.dto.CustomersDto;
import com.bike.stores.dev.exceptions.CustomersNotFoundException;
import com.bike.stores.dev.model.Customers;
import com.bike.stores.dev.repository.CustomersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomersService {

    private final CustomersRepository customersRepository;

    private final PasswordEncoder passwordEncoder;


    // Constructor-based dependency injection
    public CustomersService(CustomersRepository customersRepository,PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.customersRepository = customersRepository;

    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id The ID of the customer to retrieve
     * @return DTO with information of the specified customer
     * @throws CustomersNotFoundException if the customer is not found
     */
    public CustomersDto getCustomerById(int id) {
        Customers customers = customersRepository.findById(id)
                .orElseThrow(() -> new CustomersNotFoundException("Customer couldn't be found by id:" + id));

        return mapToCustomerDto(customers);
    }



    /**
     * Retrieves all customers.
     *
     * @return List of DTOs with information of all customers
     */

    public List<CustomersDto> getAllCustomers() {
        List<Customers> customersList = customersRepository.findAll();

        return customersList.stream()
                .map(this::mapToCustomerDto)
                .collect(Collectors.toList());
    }

    private CustomersDto mapToCustomerDto(Customers customer) {
        return new CustomersDto(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getStreet(),
                customer.getCity(),
                // customer.getState(),
                customer.getZipCode(),
                customer.getPassword(),
                customer.getRole()
        );
    }

    /**
     * Updates an existing customer by their ID.
     *
     * @param id           The ID of the customer to update
     * @param customersDto DTO containing the new information
     * @return DTO with information of the updated customer
     * @throws CustomersNotFoundException if the customer is not found
     */
    public CustomersDto updateCustomer(int id, CustomersDto customersDto) {


        Customers existingCustomer = customersRepository.findById(id)
                .orElseThrow(() -> new CustomersNotFoundException("Customer couldn't be found by id:" + id));

        // Update operations
        existingCustomer.setFirstName(customersDto.getFirstName());
        existingCustomer.setLastName(customersDto.getLastName());
        existingCustomer.setPhone(customersDto.getPhone());
        existingCustomer.setEmail(customersDto.geteMail());
        existingCustomer.setStreet(customersDto.getStreet());
        existingCustomer.setCity(customersDto.getCity());
        // existingCustomer.setState(customersDto.getState()); // Uncomment if state needs to be updated
        existingCustomer.setZipCode(customersDto.getZipCode());
        String hashedPassword = passwordEncoder.encode(customersDto.getPassword());
        existingCustomer.setPassword(hashedPassword);


        Customers updatedCustomer = customersRepository.save(existingCustomer);
        return mapToCustomerDto(updatedCustomer);
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id The ID of the customer to delete
     * @throws CustomersNotFoundException if the customer is not found
     */
    public void deleteCustomersById(int id) {
        if (!customersRepository.existsById(id)) {
            throw new CustomersNotFoundException("Customer couldn't be found by id:" + id);
        }

        customersRepository.deleteById(id);
    }

    /**
     * Creates a new customer.
     *
     * @param customersDto DTO containing information for the new customer
     * @return DTO with information of the created customer
     */
    public CustomersDto createCustomer(CustomersDto customersDto) {

        Optional<Customers> optionalCustomer = customersRepository.findByEmail(customersDto.geteMail());
        if (optionalCustomer.isPresent()) {
            throw new CustomersNotFoundException("This email address is already registered");
        }


        String hashedPassword = passwordEncoder.encode(customersDto.getPassword());
        customersDto.setPassword(hashedPassword);
        Customers newCustomer = mapToCustomer(customersDto);
        Customers savedCustomer = customersRepository.save(newCustomer);
        return mapToCustomerDto(savedCustomer);
    }

    private Customers mapToCustomer(CustomersDto customersDto) {
        Customers customer = new Customers();
        customer.setFirstName(customersDto.getFirstName());
        customer.setLastName(customersDto.getLastName());
        customer.setPhone(customersDto.getPhone());
        customer.setEmail(customersDto.geteMail());
        customer.setStreet(customersDto.getStreet());
        customer.setCity(customersDto.getCity());
        // customer.setState(customersDto.getState()); // Uncomment if state needs to be added
        customer.setZipCode(customersDto.getZipCode());
        customer.setPassword(customersDto.getPassword());
        customer.setRole(customersDto.getRole());

        return customer;
    }



}