package ru.itis.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "category")
@EqualsAndHashCode(exclude = "category")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}

