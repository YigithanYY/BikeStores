package com.bike.stores.dev.config;

import com.bike.stores.dev.model.Customers;
import com.bike.stores.dev.model.Staffs;
import com.bike.stores.dev.repository.CustomersRepository;
import com.bike.stores.dev.repository.StaffsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class CustomersInfoUsersDetailsService implements UserDetailsService {

    private final CustomersRepository customersRepository;
    private final StaffsRepository staffsRepository;

    @Autowired
    public CustomersInfoUsersDetailsService(CustomersRepository customersRepository, StaffsRepository staffsRepository) {
        this.customersRepository = customersRepository;
        this.staffsRepository = staffsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customers> customerOptional = customersRepository.findByEmail(username);
        if (customerOptional.isPresent()) {
            return new CustomersInfoDetails(customerOptional.get());
        }

        Optional<Staffs> staffOptional = staffsRepository.findByEmail(username);
        if (staffOptional.isPresent()) {
            return new StaffsInfoDetails(staffOptional.get());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
