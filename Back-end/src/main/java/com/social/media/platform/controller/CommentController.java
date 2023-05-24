package com.social.media.platform.controller;

import com.social.media.platform.entity.Comment;
import com.social.media.platform.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable int id) {
        return commentRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable int id, @RequestBody Comment updatedComment) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment != null) {
            comment.setUser(updatedComment.getUser());
            comment.setPost(updatedComment.getPost());
            comment.setComment(updatedComment.getComment());
            return commentRepository.save(comment);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable int id) {
        commentRepository.deleteById(id);
    }
}
