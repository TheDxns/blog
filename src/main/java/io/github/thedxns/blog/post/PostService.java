package io.github.thedxns.blog.post;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    List<Post> getAllPosts(Pageable page) {
        return postRepository.findAll(page).getContent();
    }

    List<Post> getAllFeatured() {
        return postRepository.findByFeatured(true);
    }

    List<Post> getPostByKeyword(String keyword) {
        final List<Post> posts = postRepository.findByTitleContainingIgnoreCase(keyword);
        posts.addAll(postRepository.findByContentContainingIgnoreCase(keyword));
        final Set<Post> set = new LinkedHashSet<>(posts);
        posts.clear();
        posts.addAll(set);
        return posts;
    }
    
    Post getPost(int id) {
        return postRepository.findById(id).get();
    }

    List<Post> getAllByCreator(String username) {
        return postRepository.findByCreatorUsername(username);
    }

    List<Post> getAllByCategory(String category) {
        return postRepository.findByCategory(category);
    }

    boolean savePost(Post post) {
        if (post.getContent().length() > 990) {
            post.setSneakPeak(post.getContent().substring(0, 990) + "...");
        } else {
            post.setSneakPeak(post.getContent());
        }
        postRepository.save(post);
        return true;
    }

    boolean deletePost(int id) {
        postRepository.deleteById(id);
        return true;
    }

    boolean existsById(int id) {
        return postRepository.existsById(id);
    }

    boolean updatePost(int id, Post post) {
        post.setId(id);
        post.updateFrom(post);
        if (post.getContent().length() > 990) {
            post.setSneakPeak(post.getContent().substring(0, 990) + "...");
        } else {
            post.setSneakPeak(post.getContent());
        }
        postRepository.save(post);
        return true;
    }
}