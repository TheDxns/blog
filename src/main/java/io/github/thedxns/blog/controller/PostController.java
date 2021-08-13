package io.github.thedxns.blog.controller;

import io.github.thedxns.blog.logic.PostService;
import io.github.thedxns.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    public ResponseEntity<?> getPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping
    public ResponseEntity<?> getPosts(Pageable page) {
        return ResponseEntity.ok(postService.getAllPosts(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable int id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody Post post) {
        if (postService.savePost(post)) {
            return new ResponseEntity<String>("The post has been published.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("The post has not been published. Try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id) {
        if(postService.deletePost(id)) {
            return new ResponseEntity<String>("The post has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("The post was not deleted. Try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
