package ru.itis.blogrestapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.blogrestapi.models.Author;
import ru.itis.blogrestapi.models.Post;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByAuthorId(Long id, Pageable pageable);
    Page<Post> findByInFavoritesContains(Author author, Pageable pageable);
}
