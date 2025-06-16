package com.felix.cerdos.security.postgresql.cerdos.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.felix.cerdos.security.postgresql.cerdos.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
     List<Comment> findByTweetId(Long tweetId); 
}
