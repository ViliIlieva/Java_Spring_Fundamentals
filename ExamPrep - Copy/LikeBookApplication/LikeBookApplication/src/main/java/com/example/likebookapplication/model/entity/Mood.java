package com.example.likebookapplication.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "moods")
public class Mood extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private MoodsEnum moodName;

    @Column
    private String description;

    @OneToMany(mappedBy = "mood")
    private Set<Post> posts;


    public Mood() {
    }

    public Mood(MoodsEnum moodName) {
        this.moodName = moodName;
    }

    public MoodsEnum getMoodName() {
        return moodName;
    }

    public void setMoodName(MoodsEnum moodName) {
        this.moodName = moodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
