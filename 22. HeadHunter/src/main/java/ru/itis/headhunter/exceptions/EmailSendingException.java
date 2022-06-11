package ru.itis.headhunter.exceptions;

public class EmailSendingException extends RuntimeException{
    public EmailSendingException(String message) {
        super(message);
    }
}
