package io.github.thedxns.blog.model.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.thedxns.blog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByFeatured(boolean b);
    List<Post> findByCreatorUsername(String username);
    List<Post> findByCategory(String category);
    List<Post> findByTitleContainingIgnoreCase(String keyword);
    List<Post> findByContentContainingIgnoreCase(String keyword);
    void deleteByCreatorUsername(String username);
}
