package com.example.happy_travel.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityClass, String attributeName, String attributeValue){
        super (String.format("%s with %s %s was not found", entityClass, attributeName));
    }
    public EntityNotFoundException(String entityClass, String attributeName, String attributeValue, String containerClass) {
        super(String.format("%s with %s %s was not found in %s", entityClass, attributeName, attributeValue, containerClass));
    }
}
