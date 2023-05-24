package com.social.media.platform.controller;

import com.social.media.platform.entity.Likes;
import com.social.media.platform.repo.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeRepository likeRepository;

    @GetMapping
    public List<Likes> getAllLikes() {
        return likeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Likes getLikeById(@PathVariable int id) {
        return likeRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Likes createLike(@RequestBody Likes like) {
        return likeRepository.save(like);
    }

    @PutMapping("/{id}")
    public Likes updateLike(@PathVariable int id, @RequestBody Likes updatedLike) {
        Likes like = likeRepository.findById(id).orElse(null);
        if (like != null) {
            like.setUser(updatedLike.getUser());
            like.setPost(updatedLike.getPost());
            return likeRepository.save(like);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteLike(@PathVariable int id) {
        likeRepository.deleteById(id);
    }
}
