package pl.edu.wszib.orderinfrastructure.rest;

import lombok.Value;

@Value
public class RestError {
    String message;
    Object error;
}
