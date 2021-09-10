package io.github.thedxns.blog.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.thedxns.blog.model.Comment;
import io.github.thedxns.blog.model.repositories.CommentRepository;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getComments(Integer id) {
        return commentRepository.findByPostId(id);
    }

    public List<Comment> getComments(Integer id, Pageable page) {
        return commentRepository.findAllByPostId(id, page);
    }

    public boolean saveComment(Comment comment) {
        commentRepository.save(comment);
        return true;
    }

    public boolean deleteComment(int id) {
        commentRepository.deleteById(id);
        return true;
    }

    public boolean existsById(int id) {
        return commentRepository.existsById(id);
    }

    public boolean updateComment(int id, Comment comment) {
        comment.setId(id);
        comment.updateFrom(comment);
        commentRepository.save(comment);
        return true;
    }
}
