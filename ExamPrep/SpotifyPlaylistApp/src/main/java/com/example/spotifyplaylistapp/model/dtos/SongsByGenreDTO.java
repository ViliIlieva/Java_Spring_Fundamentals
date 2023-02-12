package com.example.spotifyplaylistapp.model.dtos;

import java.util.Set;

public class SongsByGenreDTO {

    private Set<SongDTO> popSongs;
    private Set<SongDTO> rockSongs;
    private Set<SongDTO> jazzSongs;

    public SongsByGenreDTO() {
    }

    public Set<SongDTO> getPopSongs() {
        return popSongs;
    }

    public void setPopSongs(Set<SongDTO> popSongs) {
        this.popSongs = popSongs;
    }

    public Set<SongDTO> getRockSongs() {
        return rockSongs;
    }

    public void setRockSongs(Set<SongDTO> rockSongs) {
        this.rockSongs = rockSongs;
    }

    public Set<SongDTO> getJazzSongs() {
        return jazzSongs;
    }

    public void setJazzSongs(Set<SongDTO> jazzSongs) {
        this.jazzSongs = jazzSongs;
    }
}
