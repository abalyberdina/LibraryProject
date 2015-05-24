package com.epam.library.support.exception;

@SuppressWarnings("serial")
public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
