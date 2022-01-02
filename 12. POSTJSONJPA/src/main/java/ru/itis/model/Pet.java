package ru.itis.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = "owner")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String kind;
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Profile owner;
}
