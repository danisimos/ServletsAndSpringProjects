package ru.itis.blogrestapi.repositories;

public interface JwtTokenBlackListRepository {
    void save(String token);

    boolean exists(String token);
}
