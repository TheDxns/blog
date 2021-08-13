package io.github.thedxns.blog.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.thedxns.blog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
