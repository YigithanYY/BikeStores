package com.bike.stores.dev;

import com.bike.stores.dev.repository.CustomersRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()

public class DevApplication  {


	public static void main(String[] args) {
		SpringApplication.run(DevApplication.class, args);
	}

	public DevApplication(CustomersRepository customersRepository) {
		this.customersRepository = customersRepository;
	}
	private final CustomersRepository customersRepository;


}
