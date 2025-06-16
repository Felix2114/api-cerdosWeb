package com.felix.cerdos.security.postgresql.cerdos.controllers;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felix.cerdos.security.postgresql.cerdos.models.Tweet;
import com.felix.cerdos.security.postgresql.cerdos.models.User;
import com.felix.cerdos.security.postgresql.cerdos.repository.UserRepository;
import com.felix.cerdos.security.postgresql.cerdos.repository.TweetRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    
    @GetMapping("/all")
    public Page<Tweet> getAllTweets(Pageable pageable) {
        return tweetRepository.findAll(pageable);
    }

    @PostMapping("/create")
    public Tweet createTweet(@Valid @RequestBody Tweet tweetRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
         System.out.println("userid : " + userId  );

        User user = getValidUser(userId);
         System.out.println("user");
          System.out.println(user);

        // Creamos nuevo tweet y asignamos sus campos
        Tweet myTweet = new Tweet();
        myTweet.setTweet(tweetRequest.getTweet());
        myTweet.setUrl(tweetRequest.getUrl());
        myTweet.setDescription(tweetRequest.getDescription());
        myTweet.setNombre(tweetRequest.getNombre());
        myTweet.setRaza(tweetRequest.getRaza());
        myTweet.setPeso(tweetRequest.getPeso());
        myTweet.setPostedBy(user);

         tweetRepository.save(myTweet);
         return myTweet;
    }

     private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }
}
