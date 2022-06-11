package ru.itis.headhunter.exceptions;

public class AccountNotFoundException extends NotFoundException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
