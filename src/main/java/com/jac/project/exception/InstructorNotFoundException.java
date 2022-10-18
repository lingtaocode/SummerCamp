package com.jac.project.exception;

public class InstructorNotFoundException extends RuntimeException {
    private String message;

    public InstructorNotFoundException(String message){
        super(message);
    }

}
