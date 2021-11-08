package io.github.thedxns.blog.controller;

import io.github.thedxns.blog.logic.PostService;
import io.github.thedxns.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping
    public ResponseEntity<?> getPosts(Pageable page) {
        return ResponseEntity.ok(postService.getAllPosts(page));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Post>> getFeaturedPosts() {
        return ResponseEntity.ok(postService.getAllFeatured());
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<?> getPostById(@PathVariable String keyword) {
        return ResponseEntity.ok(postService.getPostByKeyword(keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable int id) {
        if(!postService.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(postService.getPost(id));
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Post>> getPostsByCreator(@PathVariable String username) {
        return ResponseEntity.ok(postService.getAllByCreator(username));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(postService.getAllByCategory(category));
    }

    @PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody Post post) {
        if(postService.savePost(post)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id) {
        if(!postService.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            if(postService.deletePost(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable int id, @RequestBody @Valid Post post) {
        if(!postService.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            if(postService.updatePost(id, post)) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.internalServerError().build(); 
            }
        }
    }
}
