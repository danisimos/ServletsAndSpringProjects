package ru.itis.headhunter.repositories;

public interface JwtTokenBlackListRepository {
    void save(String token);

    boolean exists(String token);
}
