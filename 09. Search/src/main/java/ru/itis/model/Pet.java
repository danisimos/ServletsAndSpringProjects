package ru.itis.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class Pet {
    private Integer id;
    private String kind;
    private String name;
}
