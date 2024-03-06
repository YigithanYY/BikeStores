package com.bike.stores.dev.exceptions.Global;

import com.bike.stores.dev.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BrandsNotFoundException.class)
    public ResponseEntity<String> handleBrandsNotFoundException(BrandsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CategoriesNotFoundException.class)
    public ResponseEntity<String> handleCategoriesNotFoundException(CategoriesNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomersNotFoundException.class)
    public ResponseEntity<String> handleCustomersNotFoundException(CustomersNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderItemsNotFoundException.class)
    public ResponseEntity<String> handleOrderItemsNotFoundException(OrderItemsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrdersNotFoundException.class)
    public ResponseEntity<String> handleOrdersNotFoundException(OrdersNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductsNotFoundException.class)
    public ResponseEntity<String> handleProductsNotFoundException(ProductsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StaffsNotFoundException.class)
    public ResponseEntity<String> handleStaffsNotFoundException(StaffsNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StocksNotFoundException.class)
    public ResponseEntity<String> handleStocksNotFoundException(StocksNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StoresNotFoundException.class)
    public ResponseEntity<String> handleStoresNotFoundException(StoresNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }














}
