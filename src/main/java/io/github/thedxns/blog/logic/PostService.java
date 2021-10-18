package io.github.thedxns.blog.logic;

import io.github.thedxns.blog.model.Post;
import io.github.thedxns.blog.model.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getAllPosts(Pageable page) {
        return postRepository.findAll(page).getContent();
    }

    public List<Post> getAllFeatured() {
        return postRepository.findByFeatured(true);
    }

    public Post getPost(int id) {
        return postRepository.findById(id).get();
    }

    public boolean savePost(Post post) {
        if (post.getContent().length() > 990) {
            post.setSneakPeak(post.getContent().substring(0, 990) + "...");
        } else {
            post.setSneakPeak(post.getContent());
        }
        postRepository.save(post);
        return true;
    }

    public boolean deletePost(int id) {
        postRepository.deleteById(id);
        return true;
    }

    public boolean existsById(int id) {
        return postRepository.existsById(id);
    }

    public boolean updatePost(int id, Post post) {
        post.setId(id);
        post.updateFrom(post);
        postRepository.save(post);
        return true;
    }
}