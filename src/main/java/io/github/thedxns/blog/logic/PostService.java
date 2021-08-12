package io.github.thedxns.blog.logic;

import io.github.thedxns.blog.model.Post;
import io.github.thedxns.blog.model.PostRepository;
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

    public Post getPost(int id) {
        return postRepository.getById(id);
    }

    public boolean savePost(Post post) {
        postRepository.save(post);
        return true;
    }
}
