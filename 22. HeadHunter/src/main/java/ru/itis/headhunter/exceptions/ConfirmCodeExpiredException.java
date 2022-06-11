package ru.itis.headhunter.exceptions;

public class ConfirmCodeExpiredException extends RuntimeException {
    public ConfirmCodeExpiredException(String message) {
        super(message);
    }
}
