package ru.itis.headhunter;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test-context"})
class ApplicationTests {
    @Test
    void contextLoads() {
    }
}

