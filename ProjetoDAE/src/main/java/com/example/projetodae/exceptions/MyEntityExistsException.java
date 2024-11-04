package com.example.projetodae.exceptions;


public class MyEntityExistsException extends RuntimeException {
    public MyEntityExistsException(String message) {
        super(message);
    }
}