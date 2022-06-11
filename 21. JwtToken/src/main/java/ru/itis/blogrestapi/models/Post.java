package ru.itis.blogrestapi.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"inFavorites", "author"})
@Entity
public class Post {
    public enum State {
        DRAFT, DELETED, PUBLISHED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(length = 20)
    private String title;

    @Column(length = 1000)
    private String text;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @ManyToMany(mappedBy = "favorites")
    private Set<Author> inFavorites;
}
