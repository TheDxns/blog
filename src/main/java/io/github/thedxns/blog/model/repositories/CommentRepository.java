package io.github.thedxns.blog.model.repositories;

import org.springframework.stereotype.Repository;

import io.github.thedxns.blog.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
}
