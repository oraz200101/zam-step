package com.example.cryptocurrencyservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ElementNotFoundException.class)
    public Map<String, String> handleElementNotFound(ElementNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Exception", exception.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ElementAlreadyExistException.class)
    public Map<String, String> handleElementExist(ElementAlreadyExistException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Exception", exception.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputDataIsNullException.class)
    public Map<String, String> handleDataIsNull(InputDataIsNullException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Exception", exception.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotEnoughGasException.class)
    public Map<String, String> handleNotEnoughGas(NotEnoughGasException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Exception", exception.getMessage());
        return error;
    }

}
