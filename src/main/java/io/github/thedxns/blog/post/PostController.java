package io.github.thedxns.blog.post;

import io.github.thedxns.blog.email.MailBody;
import io.github.thedxns.blog.email.MailService;
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
    private final MailService mailService;

    @Autowired
    public PostController(PostService postService, MailService mailService) {
        this.postService = postService;
        this.mailService = mailService;
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping
    public ResponseEntity<?> getPosts(final Pageable page) {
        return ResponseEntity.ok(postService.getAllPosts(page));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Post>> getFeaturedPosts() {
        return ResponseEntity.ok(postService.getAllFeatured());
    }

    @GetMapping("search/{keyword}")
    public ResponseEntity<?> getPostById(@PathVariable final String keyword) {
        return ResponseEntity.ok(postService.getPostByKeyword(keyword));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable final int id) {
        if(!postService.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(postService.getPost(id));
        }
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Post>> getPostsByCreator(@PathVariable final String username) {
        return ResponseEntity.ok(postService.getAllByCreator(username));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable final String category) {
        return ResponseEntity.ok(postService.getAllByCategory(category));
    }

    @PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody final Post post) throws Exception {
        if (postService.savePost(post)) {
            final String title = "A new post was published on A blog by TheDxns";
            final String recipient = "denis.lukasczyk@gmail.com";
            final String content = "Hi, we would like you to know that on A blog by TheDxns there was a new post published with the title: " + "'" + post.getTitle()
            + "<br/><br />Visit the blog clicking the link below:<br /><a href=" 
            + "'http:///localhost:3000'" + ">A blog by TheDxns</a>";
            if (mailService.contactSubscribers(title, recipient, content)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }    
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id) {
        if (!postService.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            if (postService.deletePost(id)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable int id, @RequestBody @Valid Post post) {
        if (!postService.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            if (postService.updatePost(id, post)) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.internalServerError().build(); 
            }
        }
    }
}
