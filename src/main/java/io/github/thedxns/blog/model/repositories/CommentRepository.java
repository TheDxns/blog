package io.github.thedxns.blog.model.repositories;

import org.springframework.stereotype.Repository;
import io.github.thedxns.blog.model.Comment;
import org.springframework.data.domain.Pageable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByPostId(Integer postId);
    List<Comment> findAllByPostId(Integer postId, Pageable pageable);
}
