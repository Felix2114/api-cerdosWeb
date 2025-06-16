package com.felix.cerdos.security.postgresql.cerdos.models;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "likes"})
@Entity
@Table(name = "tweets")
public class Tweet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 140)
  private String tweet;

  

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "posted_by", referencedColumnName = "id")
  private User postedBy;

  @OneToMany(mappedBy = "tweet")
  @JsonIgnore
  private Set<TweetReaction> likes = new HashSet<>();

  // Nuevos campos
  @NotBlank
  @Size(max = 255)
  private String url;

  @Size(max = 500)
  private String description;

  @Size(max = 100)
  private String nombre;

  @Size(max = 100)
  private String raza;

  private Double peso;

  public Tweet() {
  }

  public Tweet(String tweet) {
    this.tweet = tweet;
  }

  // Getters y setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTweet() {
    return tweet;
  }

  public void setTweet(String tweet) {
    this.tweet = tweet;
  }

  public User getPostedBy() {
    return postedBy;
  }

  public void setPostedBy(User postedBy) {
    this.postedBy = postedBy;
  }

  
  public Set<TweetReaction> getLikes() {
    return likes;
  }

  public void setLikes(Set<TweetReaction> likes) {
    this.likes = likes;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getRaza() {
    return raza;
  }

  public void setRaza(String raza) {
    this.raza = raza;
  }

  public Double getPeso() {
    return peso;
  }

  public void setPeso(Double peso) {
    this.peso = peso;
  }
}
