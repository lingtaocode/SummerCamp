package com.jac.project.exception;

public class DatabaseException extends RuntimeException {
    private String message;

    public DatabaseException(String message){
        super(message);
    }

}
