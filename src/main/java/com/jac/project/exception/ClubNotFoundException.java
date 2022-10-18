package com.jac.project.exception;

public class ClubNotFoundException extends RuntimeException {
    private String message;

    public ClubNotFoundException(String message){
        super(message);
    }

}
