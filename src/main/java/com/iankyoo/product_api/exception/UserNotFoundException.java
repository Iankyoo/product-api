package com.iankyoo.product_api.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email){
        super("User not found with email: " + email);
    }
}
