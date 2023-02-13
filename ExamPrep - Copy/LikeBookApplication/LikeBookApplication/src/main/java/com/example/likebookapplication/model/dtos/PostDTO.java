package com.example.likebookapplication.model.dtos;

import com.example.likebookapplication.model.entity.MoodsEnum;
import com.example.likebookapplication.model.entity.Post;
import com.example.likebookapplication.model.entity.User;

import java.util.List;

public class PostDTO {
    private long id;
    private String content;
    private MoodsEnum mood;
    private int likes;
    private String username;
    private List<User> userLikes;

    public PostDTO() {
    }

   public PostDTO(Post post) {
        this.id = post.getId ();
        this.content = post.getContent ();
        this.mood = post.getMood ().getMoodName ();
        this.likes = post.getLikes ();
        this.userLikes = post.getUserLikes ();
        this.username = post.getUser ().getUsername ();
    }

    public long getId() {
        return id;
    }

    public PostDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public PostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public MoodsEnum getMood() {
        return mood;
    }

    public PostDTO setMood(MoodsEnum mood) {
        this.mood = mood;
        return this;
    }

    public int getLikes() {
        return likes;
    }

    public PostDTO setLikes(int likes) {
        this.likes = likes;
        return this;
    }

    public List<User> getUserLikes() {
        return userLikes;
    }

    public PostDTO setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public PostDTO setUsername(String username) {
        this.username = username;
        return this;
    }


}
