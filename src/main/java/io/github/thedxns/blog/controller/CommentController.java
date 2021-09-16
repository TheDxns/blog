package io.github.thedxns.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.thedxns.blog.logic.CommentService;
import io.github.thedxns.blog.logic.PostService;
import io.github.thedxns.blog.model.Comment;
import io.github.thedxns.blog.model.User;
import io.github.thedxns.blog.pojo.CommentContent;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }


    @GetMapping(name= "/{id}", params = {"!sort", "!page", "!size"})
    public ResponseEntity<List<Comment>> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPosts(@PathVariable Integer id, Pageable page) {
        return ResponseEntity.ok(commentService.getComments(id, page));
    }

    @PostMapping
    public ResponseEntity<?> saveComment(@RequestBody CommentContent comment) {
        if (commentService.saveComment(new Comment(comment.getText(), postService.getPost(comment.getPostId())))) {
            return new ResponseEntity<String>("The comment has been published.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("The comment has not been published. Try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable int id) {
        if(commentService.deleteComment(id)) {
            return new ResponseEntity<String>("The comment has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("The comment was not deleted. Try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable int id, @RequestBody @Valid Comment comment) {
        if(!commentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            commentService.updateComment(id, comment);
            return ResponseEntity.noContent().build();
        }
    }
}
