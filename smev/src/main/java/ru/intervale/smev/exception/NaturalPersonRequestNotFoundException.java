package ru.intervale.smev.exception;

public class NaturalPersonRequestNotFoundException extends RuntimeException{
    public NaturalPersonRequestNotFoundException(String message) {
        super(message);
    }
}
