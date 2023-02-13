package com.example.likebookapplication.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name = "posts")
@Entity
public class Post extends BaseEntity{

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private User user;

    @Column
    private int likes;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userLikes;

    @ManyToOne(fetch = FetchType.EAGER)
    private Mood mood;

    public Post() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<User> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }
}
