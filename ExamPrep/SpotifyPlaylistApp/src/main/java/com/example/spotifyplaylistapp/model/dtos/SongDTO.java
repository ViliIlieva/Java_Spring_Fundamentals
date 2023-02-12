package com.example.spotifyplaylistapp.model.dtos;

public class SongDTO {

    private Long id;
    private String performer;
    private String title;
    private Long duration;

    public SongDTO() {
    }

    public String getPerformer() {
        return performer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
