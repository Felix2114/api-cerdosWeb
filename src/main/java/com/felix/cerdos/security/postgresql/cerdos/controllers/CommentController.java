package com.felix.cerdos.security.postgresql.cerdos.controllers;
import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felix.cerdos.security.postgresql.cerdos.models.Comment;
import com.felix.cerdos.security.postgresql.cerdos.models.User;
import com.felix.cerdos.security.postgresql.cerdos.payload.request.CommentRequest;
import com.felix.cerdos.security.postgresql.cerdos.models.Tweet;
import com.felix.cerdos.security.postgresql.cerdos.repository.UserRepository;
import com.felix.cerdos.security.postgresql.cerdos.repository.CommentRepository;
import com.felix.cerdos.security.postgresql.cerdos.repository.TweetRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comments")

public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;




     @GetMapping("/all")
    public Page<Comment> getTweet(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    @GetMapping("/tweet/{tweetId}")
public List<Comment> getCommentsByTweetId(@PathVariable Long tweetId) {
    return commentRepository.findByTweetId(tweetId); 
}

    @PostMapping("/create")
    public Comment createComment(@Valid @RequestBody CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
         System.out.println("userid : " + userId  );

        Tweet tweet = tweetRepository.findById(commentRequest.getTweetId())
    .orElseThrow(() -> new RuntimeException("Tweet no encontrado"));
        User user = getValidUser(userId);
         System.out.println("user");
          System.out.println(user);

          Comment comment = new Comment();
          comment.setContent(commentRequest.getContent());
          comment.setUser(user);
         comment.setTweet(tweet);
         commentRepository.save(comment);

          return comment;

    }


    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }
    
}
