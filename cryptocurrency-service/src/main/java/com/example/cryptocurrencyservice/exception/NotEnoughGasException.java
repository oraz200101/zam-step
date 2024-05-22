package com.example.cryptocurrencyservice.exception;

public class NotEnoughGasException extends RuntimeException{

  public NotEnoughGasException(String message) {
    super(message);
  }
}
