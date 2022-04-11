package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "profiles")
@EqualsAndHashCode(exclude = "profiles")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;
    String description;

    @OneToMany(mappedBy = "category")
    List<Product> products;

    @ManyToMany(mappedBy = "favouriteCategories")
    List<Profile> profiles;
}

