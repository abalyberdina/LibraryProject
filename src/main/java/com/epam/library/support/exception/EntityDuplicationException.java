package com.epam.library.support.exception;

@SuppressWarnings("serial")
public class EntityDuplicationException extends RuntimeException{
    public EntityDuplicationException(String string) {
        super(string);
    }
}
