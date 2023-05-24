package com.social.media.platform.controller;

import com.social.media.platform.entity.Post;
import com.social.media.platform.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable int id) {
        return postRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable int id, @RequestBody Post updatedPost) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setUserId(updatedPost.getUserId());
            post.setDescription(updatedPost.getDescription());
            return postRepository.save(post);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) {
        postRepository.deleteById(id);
    }
}
