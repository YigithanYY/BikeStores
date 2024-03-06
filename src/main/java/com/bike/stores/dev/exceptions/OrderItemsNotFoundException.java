package com.bike.stores.dev.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderItemsNotFoundException extends RuntimeException {
    public OrderItemsNotFoundException(String message){
        super(message);
    }

}
