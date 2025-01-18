package com.abhishek.cab.project.exceptions;


public class ResourceNotFoundException  extends RuntimeException{
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
