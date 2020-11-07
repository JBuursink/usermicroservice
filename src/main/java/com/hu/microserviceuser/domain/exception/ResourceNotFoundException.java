package com.hu.microserviceuser.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, Class<?> type){
        super(String.format("Resource of type [%s] wat not found: [%s]", type.getClass(), message));
    }
}
