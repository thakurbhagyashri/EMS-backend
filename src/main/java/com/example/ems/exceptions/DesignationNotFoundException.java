package com.example.ems.exceptions;

public class DesignationNotFoundException extends RuntimeException {
    public DesignationNotFoundException(Long id) {
        super("Designation not found with id: " + id);
    }

    public DesignationNotFoundException(String message) {
        super(message);
    }
}
