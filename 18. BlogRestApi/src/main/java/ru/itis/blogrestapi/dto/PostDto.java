package ru.itis.blogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    public enum State {
        DRAFT, DELETED, PUBLISHED
    }
    private Long id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String title;
    private String text;
    private State state;
}
