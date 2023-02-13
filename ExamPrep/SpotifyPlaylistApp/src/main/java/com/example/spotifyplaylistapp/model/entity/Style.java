package com.example.spotifyplaylistapp.model.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleEnum styleName;
    @Column
    private String description;
    @OneToMany(mappedBy = "style", fetch = FetchType.LAZY)
    private Set<Song> songs;
    public Style() {
    }

    public Style(StyleEnum styleName) {
        this.styleName = styleName;
    }

    public StyleEnum getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleEnum styleName) {
        this.styleName = styleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }
}
