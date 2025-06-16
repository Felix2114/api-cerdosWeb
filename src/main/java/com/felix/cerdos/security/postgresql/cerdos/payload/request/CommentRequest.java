package com.felix.cerdos.security.postgresql.cerdos.payload.request;

public class CommentRequest {
    private Long tweetId;
    private String content;
    //private Long userId; se obtiene por el token

    public Long getTweetId() {
        return tweetId;
    }
    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    

    
    
}
