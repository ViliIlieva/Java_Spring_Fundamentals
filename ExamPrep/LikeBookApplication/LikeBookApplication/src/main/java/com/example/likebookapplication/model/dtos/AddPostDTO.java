package com.example.likebookapplication.model.dtos;

import com.example.likebookapplication.model.entity.Mood;
import com.example.likebookapplication.model.entity.MoodsEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddPostDTO {
    private Long id;

    @Size(min = 2, max = 150, message = "Content length must be between 2 and 150 characters!")
    @NotBlank
    private String content;

    @NotBlank(message = "You must select a mood!")
    private String mood;

    public AddPostDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public AddPostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getMood() {
        return mood;
    }

    public AddPostDTO setMood(String mood) {
        this.mood = mood;
        return this;
    }
}
