package com.example.happy_travel.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{
        public EntityAlreadyExistsException(String entityClass, String attributeName, String attributeValue) {
            super(String.format("%s with %s %s already exists", entityClass, attributeName, attributeValue));
        }
}
