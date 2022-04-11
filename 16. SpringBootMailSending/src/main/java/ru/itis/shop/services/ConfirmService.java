package ru.itis.shop.services;

public interface ConfirmService {
    boolean confirm(String confirmCode);
    void addConfirmCode(String confirmCode, long timestamp);
}
